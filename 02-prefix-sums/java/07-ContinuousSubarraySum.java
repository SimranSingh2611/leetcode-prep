import java.util.HashMap;
import java.util.Map;

// O(n) time and O(min(n, k)) space for the problem's positive k constraint.
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Long, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0L, -1);

        long prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            long remainder = prefix % k;
            Integer earlier = firstIndex.get(remainder);
            if (earlier != null) {
                if (i - earlier >= 2) {
                    return true;
                }
            } else {
                firstIndex.put(remainder, i);
            }
        }
        return false;
    }
}

