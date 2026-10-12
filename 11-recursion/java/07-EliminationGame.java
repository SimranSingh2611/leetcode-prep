// 390. Elimination Game
// https://leetcode.com/problems/elimination-game/
// Solution recursive; IterativeSolution state-compressed alternative.
import java.util.*;

class Solution {
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }
}

class IterativeSolution {
    public int lastRemaining(int n) {
        int head = 1, step = 1;
        boolean left = true;
        while (n > 1) {
            if (left || n % 2 == 1) {
                head += step;
            }
            n /= 2;
            step *= 2;
            left = !left;
        }
        return head;
    }
}
