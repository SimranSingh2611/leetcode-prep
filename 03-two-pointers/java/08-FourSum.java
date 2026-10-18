/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort, fix the first two values, and search the remaining pair with two pointers.
 * - I'd skip duplicates at every level and use a wider type for four-value sums.
 * Time complexity: O(n^3) for n values.
 * Space complexity: O(1) pointer state, plus sorting workspace and O(r) output for r
 *   quadruples; a conservative Java auxiliary bound including sorting is O(n).
 */

// 18. 4Sum
// https://leetcode.com/problems/4sum/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// O(n^3) time and O(1) auxiliary space, excluding sorting internals/output.
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int first = 0; first < n - 3; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                break;
            }
            if ((long) nums[first] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                continue;
            }

            for (int second = first + 1; second < n - 2; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                int left = second + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) nums[first] + nums[second] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[first], nums[second], nums[left], nums[right]));
                        int leftValue = nums[left];
                        int rightValue = nums[right];
                        while (left < right && nums[left] == leftValue) {
                            left++;
                        }
                        while (left < right && nums[right] == rightValue) {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }
}

