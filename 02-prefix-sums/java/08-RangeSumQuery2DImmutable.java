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

