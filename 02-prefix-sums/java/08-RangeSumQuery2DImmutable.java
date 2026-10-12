/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd build a padded 2D prefix-sum table.
 * - Each rectangle query subtracts the strips above and left, then adds back their double-
 *   subtracted overlap.
 * Time complexity: Construction: O(RC) for R rows and C columns; each query: O(1).
 * Space complexity: O(RC) persistent table space; O(1) additional query space.
 */

// LeetCode's required class. O(rows * cols) construction and O(1) query.
class NumMatrix {
    private final long[][] prefix;

    public NumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        prefix = new long[rows + 1][columns + 1];

        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                prefix[row][column] = matrix[row - 1][column - 1]
                        + prefix[row - 1][column]
                        + prefix[row][column - 1]
                        - prefix[row - 1][column - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        long sum = prefix[row2 + 1][col2 + 1]
                - prefix[row1][col2 + 1]
                - prefix[row2 + 1][col1]
                + prefix[row1][col1];
        return (int) sum;
    }
}

