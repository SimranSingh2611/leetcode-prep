import java.util.*;
import java.nio.file.*;
import java.net.*;
import java.lang.reflect.*;
import java.io.*;
import javax.tools.*;
import java.util.stream.*;

class Verify {
    public static void main(String[] args) throws Exception {
        Path root=Paths.get(args[0]),build=Paths.get(args[1]);
        JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
        if(compiler==null)throw new IllegalStateException("A JDK, not only a JRE, is required");
        List<Path> folders;
        try(Stream<Path> paths=Files.list(root)){
            folders=paths.filter(Files::isDirectory)
                .filter(p->p.getFileName().toString().matches("(06|0[89]|1[0-7]|18[ab]|19|20|21)-.*"))
                .filter(p->args.length<3 || p.getFileName().toString().equals(args[2]))
                .sorted().collect(Collectors.toList());
        }
        if(folders.isEmpty())throw new IllegalArgumentException("No supported test folder matched");
        int problems=0,checks=0;
        for(Path folder:folders) {
            List<Path> files;
            try(Stream<Path> paths=Files.list(folder.resolve("java"))){
                files=paths.filter(p->p.toString().endsWith(".java")).sorted().collect(Collectors.toList());
            }
            for(Path file:files) {
                String key=folder.getFileName().toString().split("-",2)[0]+"-"+file.getFileName().toString().substring(0,2);
                Path target=build.resolve(key);Files.createDirectories(target);
                Path test=root.resolve("tests/cases/"+key+".java");
                ByteArrayOutputStream errors=new ByteArrayOutputStream();
                int status=compiler.run(null,null,errors,"--release","8","-encoding","UTF-8","-d",target.toString(),
                        file.toString(),root.resolve("tests/Models.java").toString(),test.toString());
                if(status!=0)throw new AssertionError(file+"\n"+errors.toString("UTF-8"));
                try(URLClassLoader loader=new URLClassLoader(new URL[]{target.toUri().toURL()},ClassLoader.getPlatformClassLoader())){
                    Method run=loader.loadClass("Case").getDeclaredMethod("run");run.setAccessible(true);
                    try {checks+=(Integer)run.invoke(null);}
                    catch(InvocationTargetException e){throw new AssertionError("Failed "+file,e.getCause());}
                }
                problems++;
            }
            System.out.println("PASS "+folder.getFileName()+" ("+files.size()+" problems)");
        }
        System.out.println("PASS: "+problems+" isolated problem files compiled; "+checks+" assertions passed.");
    }
}
