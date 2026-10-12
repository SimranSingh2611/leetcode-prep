import java.util.HashMap;
import java.util.Map;

// O(n) expected time and O(n) space.
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer otherIndex = indexByValue.get(complement);
            if (otherIndex != null) {
                return new int[] {otherIndex, i};
            }
            indexByValue.put(nums[i], i);
        }
        throw new IllegalArgumentException("No valid pair");
    }
}

