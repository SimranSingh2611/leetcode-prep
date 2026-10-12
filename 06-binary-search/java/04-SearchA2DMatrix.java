// 74. Search a 2D Matrix
// https://leetcode.com/problems/search-a-2d-matrix/
/*
 * TYPE: MINIMIZATION -- first flat index with cell value >= target.
 * BOUNDS: lo=-1 (false), hi=rows*cols (virtual true), using long.
 * RESULT: true only when hi is in range and its cell equals target.
 */
// O(log(rows * cols + 1)) time, O(1) auxiliary space. No flattening allocation.
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int cols = matrix[0].length;
        long total = (long) matrix.length * cols;
        long lo = -1;
        long hi = total;
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            int value = matrix[(int) (mid / cols)][(int) (mid % cols)];
            if (value >= target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi < total && matrix[(int) (hi / cols)][(int) (hi % cols)] == target;
    }
}
