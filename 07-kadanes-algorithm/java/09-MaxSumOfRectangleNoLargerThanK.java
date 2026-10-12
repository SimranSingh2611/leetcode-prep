import java.util.TreeSet;

// O(min(m,n)^2 * max(m,n) * log(max(m,n))) time and O(max(m,n)) space.
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean compressRows = rows <= columns;
        int outerLength = compressRows ? rows : columns;
        int innerLength = compressRows ? columns : rows;
        int answer = Integer.MIN_VALUE;

        for (int start = 0; start < outerLength; start++) {
            int[] compressed = new int[innerLength];
            for (int end = start; end < outerLength; end++) {
                for (int inner = 0; inner < innerLength; inner++) {
                    compressed[inner] += compressRows
                            ? matrix[end][inner]
                            : matrix[inner][end];
                }

                answer = Math.max(answer, bestNoLargerThanK(compressed, k));
                if (answer == k) {
                    return k;
                }
            }
        }
        return answer;
    }

    private int bestNoLargerThanK(int[] nums, int k) {
        TreeSet<Long> earlierPrefixes = new TreeSet<>();
        earlierPrefixes.add(0L);
        long prefix = 0;
        int best = Integer.MIN_VALUE;

        for (int value : nums) {
            prefix += value;
            Long candidate = earlierPrefixes.ceiling(prefix - k);
            if (candidate != null) {
                best = Math.max(best, (int) (prefix - candidate));
            }
            earlierPrefixes.add(prefix);
        }
        return best;
    }
}

