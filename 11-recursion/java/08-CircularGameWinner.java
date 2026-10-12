// 1823. Find the Winner of the Circular Game
// https://leetcode.com/problems/find-the-winner-of-the-circular-game/
// Solution recursive; IterativeSolution optimized for stack space.
import java.util.*;

class Solution {
    public int findTheWinner(int n, int k) {
        return survivor(n, k) + 1;
    }

    private int survivor(int n, int k) {
        return n == 1 ? 0 : (survivor(n - 1, k) + k) % n;
    }
}

class IterativeSolution {
    public int findTheWinner(int n, int k) {
        int winner = 0;
        for (int size = 2; size <= n; size++) {
            winner = (winner + k) % size;
        }
        return winner + 1;
    }
}
