import java.util.HashMap;
import java.util.Map;

// O(n) time and O(n) space.
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);

        int balance = 0;
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] == 1 ? 1 : -1;
            Integer earlier = firstIndex.get(balance);
            if (earlier != null) {
                longest = Math.max(longest, i - earlier);
            } else {
                firstIndex.put(balance, i);
            }
        }
        return longest;
    }
}

