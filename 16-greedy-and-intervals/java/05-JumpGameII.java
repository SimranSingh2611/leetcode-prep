/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd group reachable positions into layers representing the current jump count.
 * - Scanning a whole layer finds the next frontier, and reaching its boundary commits one
 *   additional jump.
 * Time complexity: O(n) for n positions.
 * Space complexity: O(1) auxiliary frontier state.
 */

// 45. Jump Game II
// https://leetcode.com/problems/jump-game-ii/
// NeetCode 150: https://neetcode.io/problems/jump-game-ii/question?list=neetcode150
// Solution assumes LeetCode's guarantee that the last index is reachable.
import java.util.*;

class Solution {
    public int jump(int[] nums) {
        int jumps = 0, end = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
