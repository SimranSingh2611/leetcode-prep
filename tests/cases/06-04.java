// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        int[][] sample={{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        Checks.eq(true,s.searchMatrix(sample,3));Checks.eq(false,s.searchMatrix(sample,13));
        Checks.eq(false,s.searchMatrix(new int[0][],5));
        Checks.eq(false,s.searchMatrix(new int[][]{{}},5));
        for(int rows=1;rows<=8;rows++)for(int cols=1;cols<=8;cols++){
            int[][] a=new int[rows][cols];
            for(int i=0;i<rows*cols;i++)a[i/cols][i%cols]=i*3-70;
            for(int target=-75;target<=rows*cols*3-65;target++){
                boolean expected=false;for(int[] row:a)for(int value:row)expected|=value==target;
                Checks.eq(expected,s.searchMatrix(a,target));
            }
        }
        Checks.eq(true,s.searchMatrix(new int[][]{{Integer.MIN_VALUE,Integer.MAX_VALUE}},Integer.MAX_VALUE));
        return Checks.count;
    }
}
