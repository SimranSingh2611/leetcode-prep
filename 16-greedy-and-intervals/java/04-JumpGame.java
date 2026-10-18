/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track the furthest reachable index while scanning.
 * - An index beyond that frontier proves failure; a reachable index can extend the frontier
 *   with its jump length.
 * Time complexity: O(n) for n positions.
 * Space complexity: O(1) auxiliary space.
 */

// 55. Jump Game
// https://leetcode.com/problems/jump-game/
// NeetCode 150: https://neetcode.io/problems/jump-game/question?list=neetcode150
// Solution leaves nums unchanged; the problem's bounds keep index sums within int.
import java.util.*;

class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                return false;
            }
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }
}
