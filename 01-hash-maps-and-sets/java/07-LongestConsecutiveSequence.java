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
