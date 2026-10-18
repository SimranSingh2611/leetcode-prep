/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd put the numbers in a set and start a run only when its predecessor is absent.
 * - Each distinct number belongs to one scanned run, which explains why the nested loops still
 *   do linear work.
 * Time complexity: O(n) expected for n input values.
 * Space complexity: O(n) auxiliary set space.
 */

// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
// NeetCode 150: https://neetcode.io/problems/longest-consecutive-sequence/question?list=neetcode150
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Recommended: O(n) expected time and O(n) space.
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> values = new HashSet<>();
        for (int value : nums) {
            values.add(value);
        }

        int longest = 0;
        for (int value : values) {
            if (value == Integer.MIN_VALUE || !values.contains(value - 1)) {
                int length = 1;
                int current = value;
                while (current != Integer.MAX_VALUE && values.contains(current + 1)) {
                    current++;
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}

// Follow-up: O(n log n) time with no hash table; mutates the input.
class SortingSolution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int longest = 1;
        int current = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] == (long) nums[i - 1] + 1) {
                current++;
            } else {
                current = 1;
            }
            longest = Math.max(longest, current);
        }
        return longest;
    }
}
