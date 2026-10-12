import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// O(n^2) time and O(1) auxiliary space, excluding sorting internals/output.
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int first = 0; first < nums.length - 2; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            if (nums[first] > 0) {
                break;
            }

            int left = first + 1;
            int right = nums.length - 1;
            while (left < right) {
                long sum = (long) nums[first] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[first], nums[left], nums[right]));
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
        return result;
    }
}

