// 50. Pow(x, n)
// https://leetcode.com/problems/powx-n/
// Solution recursive; IterativeSolution optimized for stack space.
import java.util.*;

class Solution {
    public double myPow(double x, int n) {
        long e = n;
        if (e < 0) {
            x = 1 / x;
            e = -e;
        }
        return power(x, e);
    }

    private double power(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double half = power(x, n / 2);
        return half * half * (n % 2 == 0 ? 1 : x);
    }
}

class IterativeSolution {
    public double myPow(double x, int n) {
        long e = n;
        if (e < 0) {
            x = 1 / x;
            e = -e;
        }
        double result = 1;
        while (e > 0) {
            if ((e & 1) != 0) {
                result *= x;
            }
            x *= x;
            e >>= 1;
        }
        return result;
    }
}
