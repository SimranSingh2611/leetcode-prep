// Local checks for LeetCode 85.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(6,new Solution().maximalRectangle(new char[][]{"10100".toCharArray(),"10111".toCharArray(),"11111".toCharArray(),"10010".toCharArray()}));
        Checks.eq(0,new Solution().maximalRectangle(new char[][]{{'0'}}));
        check(new char[0][0]);
        check(new char[2][0]);
        check(new char[][]{"1111".toCharArray(), "1111".toCharArray(), "1111".toCharArray()});
        check(new char[][]{"1111".toCharArray(), "0000".toCharArray(), "1111".toCharArray()});
        check(new char[][]{"10101".toCharArray()});
        Random random = new Random(85);
        for (int t = 0; t < 300; t++) {
            char[][] matrix = new char[random.nextInt(5)][random.nextInt(6)];
            for (char[] row : matrix)
                for (int c = 0; c < row.length; c++) row[c] = random.nextBoolean() ? '1' : '0';
            check(matrix);
        }
        return Checks.count;
    }

    // Independent oracle: enumerate rectangles and inspect every enclosed cell.
    private static void check(char[][] matrix) {
        int rows = matrix.length, cols = rows == 0 ? 0 : matrix[0].length;
        char[][] original = new char[rows][];
        for (int r = 0; r < rows; r++) original[r] = matrix[r].clone();
        int expected = 0;
        for (int top = 0; top < rows; top++)
            for (int bottom = top; bottom < rows; bottom++)
                for (int left = 0; left < cols; left++)
                    for (int right = left; right < cols; right++) {
                        boolean allOnes = true;
                        for (int r = top; r <= bottom; r++)
                            for (int c = left; c <= right; c++)
                                if (matrix[r][c] != '1') allOnes = false;
                        if (allOnes) expected = Math.max(expected, (bottom - top + 1) * (right - left + 1));
                    }
        Checks.eq(expected, new Solution().maximalRectangle(matrix));
        Checks.eq(true, Arrays.deepEquals(original, matrix));
    }
}

