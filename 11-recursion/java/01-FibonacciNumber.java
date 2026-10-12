// 509. Fibonacci Number
// https://leetcode.com/problems/fibonacci-number/
// Solution memoized; IterativeSolution optimized; NaiveSolution is for small-input tracing only.
import java.util.*;

class NaiveSolution {
    public int fib(int n) {
        return n < 2 ? n : fib(n - 1) + fib(n - 2);
    }
}

class Solution {
    public int fib(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return compute(n, memo);
    }

    private int compute(int n, int[] memo) {
        if (n < 2) {
            return n;
        }
        if (memo[n] < 0) {
            memo[n] = compute(n - 1, memo) + compute(n - 2, memo);
        }
        return memo[n];
    }
}

class IterativeSolution {
    public int fib(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        return a;
    }
}
