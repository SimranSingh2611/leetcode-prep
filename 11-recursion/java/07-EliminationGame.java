/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - After the first pass, I'd scale the surviving even numbers down to a smaller game.
 * - Reversing the direction gives a recurrence on half as many values, so each call removes a
 *   level of work.
 * Time complexity: O(log(n + 1)) for n values, for both versions.
 * Space complexity: Primary recursive: O(log(n + 1)) stack; iterative: O(1).
 */

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
