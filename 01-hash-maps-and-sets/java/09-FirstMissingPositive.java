/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - The missing positive must be in [1, n + 1], so I'd use the input as an index map.
 * - I'd place each valid value x at x - 1; the first mismatched position gives the answer, and
 *   a duplicate check prevents endless swaps.
 * Time complexity: O(n); each successful swap places a value in its final slot.
 * Space complexity: O(1) auxiliary space; mutates the input.
 */

// 41. First Missing Positive
// https://leetcode.com/problems/first-missing-positive/
import java.util.HashSet;
import java.util.Set;

// Recommended: O(n) time and O(1) extra space; mutates nums.
class Solution {
    public int firstMissingPositive(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            int targetIndex = nums[index] - 1;
            if (nums[index] > 0
                    && nums[index] <= nums.length
                    && nums[index] != nums[targetIndex]) {
                int temporary = nums[index];
                nums[index] = nums[targetIndex];
                nums[targetIndex] = temporary;
            } else {
                index++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}

// Simpler follow-up: preserves input but uses O(n) extra space.
class SetSolution {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> positives = new HashSet<>();
        for (int value : nums) {
            if (value > 0) {
                positives.add(value);
            }
        }
        for (int candidate = 1; candidate <= nums.length; candidate++) {
            if (!positives.contains(candidate)) {
                return candidate;
            }
        }
        return nums.length + 1;
    }
}

