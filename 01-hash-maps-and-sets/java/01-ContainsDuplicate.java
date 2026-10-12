import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Recommended: O(n) expected time, O(n) space, input preserved.
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int value : nums) {
            if (!seen.add(value)) {
                return true;
            }
        }
        return false;
    }
}

// Follow-up: O(n log n) time and O(log n) sorting-stack space, but no O(n) set.
// This version mutates nums.
class SortingSolution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}

