/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd fix pairs of boundaries along the smaller dimension and compress each band into a 1D
 *   array.
 * - An ordered set of prefix sums finds the best compressed subarray at most k; ordinary Kadane
 *   alone cannot enforce that limit.
 * Time complexity: O(a^2 b log(b + 1)) for a = min(R, C) and b = max(R, C).
 * Space complexity: O(b) auxiliary compressed-array and ordered-set space.
 */

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

