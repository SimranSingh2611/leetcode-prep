import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(3, s.getFood(new char[][]{"XXXXXX".toCharArray(),"X*OOOX".toCharArray(),"XOO#OX".toCharArray(),"XXXXXX".toCharArray()}));
        Checks.eq(-1, s.getFood(new char[][]{"*X#".toCharArray()}));
        Checks.eq(1, s.getFood(new char[][]{"*#".toCharArray()}));
        return Checks.count;
    }
}
