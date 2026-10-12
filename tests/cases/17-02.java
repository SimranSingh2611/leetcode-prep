// Local checks for LeetCode 648.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq("the cat was rat by the bat",s.replaceWords(Arrays.asList("cat","bat","rat"),"the cattle was rattled by the battery"));
        Checks.eq("a a b c",s.replaceWords(Arrays.asList("a","aa","b","c"),"aadsfasf absbs bbab cadsfafs"));
        Checks.eq("dog",s.replaceWords(Arrays.asList("cat"),"dog"));
        return Checks.count;
    }
}

