import java.util.HashMap;
import java.util.Map;

// O(n) expected time and O(n) space.
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Long, Integer> prefixFrequency = new HashMap<>();
        prefixFrequency.put(0L, 1);

        long prefix = 0;
        int count = 0;
        for (int value : nums) {
            prefix += value;
            count += prefixFrequency.getOrDefault(prefix - k, 0);
            prefixFrequency.merge(prefix, 1, Integer::sum);
        }
        return count;
    }
}

