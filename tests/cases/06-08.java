// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        TimeMap s=new TimeMap();
        Checks.eq("",s.get("missing",7));
        s.set("foo","bar",1);Checks.eq("bar",s.get("foo",1));Checks.eq("bar",s.get("foo",3));
        Checks.eq("",s.get("foo",0));s.set("foo","bar2",4);
        Checks.eq("bar2",s.get("foo",4));Checks.eq("bar2",s.get("foo",100));
        TimeMap random=new TimeMap();Map<String,TreeMap<Integer,String>> reference=new HashMap<>();
        Random rng=new Random(6981);int timestamp=0;
        for(int t=0;t<500;t++){
            timestamp+=1+rng.nextInt(5);String key="k"+rng.nextInt(5),value="value"+t;
            random.set(key,value,timestamp);
            reference.computeIfAbsent(key,k->new TreeMap<>()).put(timestamp,value);
            for(int q=0;q<10;q++){
                String queryKey="k"+rng.nextInt(6);int time=rng.nextInt(timestamp+10);
                TreeMap<Integer,String> values=reference.get(queryKey);
                Map.Entry<Integer,String> e=values==null?null:values.floorEntry(time);
                Checks.eq(e==null?"":e.getValue(),random.get(queryKey,time));
            }
        }
        s.set("max","end",Integer.MAX_VALUE);Checks.eq("end",s.get("max",Integer.MAX_VALUE));
        OutOfOrderTimeMap flexible=new OutOfOrderTimeMap();
        flexible.set("k","late",10);flexible.set("k","early",2);flexible.set("k","middle",6);
        Checks.eq("middle",flexible.get("k",9));Checks.eq("",flexible.get("k",1));
        flexible.set("k","replacement",6);Checks.eq("replacement",flexible.get("k",6));
        return Checks.count;
    }
}
