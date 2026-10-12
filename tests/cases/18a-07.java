import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution(); int inf = Integer.MAX_VALUE;
        int[][] rooms = {{inf,-1,0,inf},{inf,inf,inf,-1},{inf,-1,inf,-1},{0,-1,inf,inf}};
        s.wallsAndGates(rooms);
        Checks.eq(new int[][]{{3,-1,0,1},{2,2,1,-1},{1,-1,2,-1},{0,-1,3,4}}, rooms);
        int[][] blocked = {{inf,-1,0}}; s.wallsAndGates(blocked); Checks.eq(inf, blocked[0][0]);
        return Checks.count;
    }
}
