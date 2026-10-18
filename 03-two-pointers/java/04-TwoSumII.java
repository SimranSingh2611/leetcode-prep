/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd begin with the smallest and largest values in the sorted array.
 * - A sum below target eliminates the left value; a sum above target eliminates the right
 *   value.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 167. Two Sum II - Input Array Is Sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// NeetCode 150: https://neetcode.io/problems/two-integer-sum-ii/question?list=neetcode150
// O(n) time and O(1) space.
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            long sum = (long) numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        throw new IllegalArgumentException("No valid pair");
    }
}

