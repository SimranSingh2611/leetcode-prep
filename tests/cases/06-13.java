import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(2, s.arrangeCoins(5));
        Checks.eq(3, s.arrangeCoins(8));
        Checks.eq(0, s.arrangeCoins(0));
        Checks.eq(1, s.arrangeCoins(1));
        Checks.eq(65535, s.arrangeCoins(Integer.MAX_VALUE));
        for (int rows = 1; rows <= 1000; rows++) {
            int coins = rows * (rows + 1) / 2;
            Checks.eq(rows-1, s.arrangeCoins(coins-1));
            Checks.eq(rows, s.arrangeCoins(coins));
            Checks.eq(rows, s.arrangeCoins(coins+rows));
        }
        Checks.eq(65534, s.arrangeCoins(2147450879));
        Checks.eq(65535, s.arrangeCoins(2147450880));
        Random rng = new Random(6441);
        for (int t = 0; t < 250; t++) {
            int n = rng.nextInt(Integer.MAX_VALUE);
            long remaining = n;
            int rows = 0;
            while (remaining >= rows + 1) {
                rows++;
                remaining -= rows;
            }
            Checks.eq(rows, s.arrangeCoins(n));
        }
        return Checks.count;
    }
}
