/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd maintain separate regions for zeros, ones, unclassified values, and twos.
 * - After moving a two to the right, I'd inspect the swapped-in value before advancing the
 *   middle pointer.
 * Time complexity: O(n) for n colors.
 * Space complexity: O(1) auxiliary space; mutates the input.
 */

// 75. Sort Colors
// https://leetcode.com/problems/sort-colors/
// Recommended: one pass, O(n) time and O(1) space.
class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int middle = 0;
        int high = nums.length - 1;

        while (middle <= high) {
            if (nums[middle] == 0) {
                swap(nums, low++, middle++);
            } else if (nums[middle] == 1) {
                middle++;
            } else {
                swap(nums, middle, high--);
            }
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }
}

// Follow-up: simpler two-pass counting; still O(n) time and O(1) space.
class CountingSolution {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int value : nums) {
            counts[value]++;
        }

        int write = 0;
        for (int color = 0; color < counts.length; color++) {
            for (int copies = 0; copies < counts[color]; copies++) {
                nums[write++] = color;
            }
        }
    }
}

