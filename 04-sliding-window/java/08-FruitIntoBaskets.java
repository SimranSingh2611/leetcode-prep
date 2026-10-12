import java.util.HashMap;
import java.util.Map;

// O(n) time and O(1) effective space: at most three types appear during shrinking.
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int left = 0;
        int longest = 0;

        for (int right = 0; right < fruits.length; right++) {
            frequency.merge(fruits[right], 1, Integer::sum);
            while (frequency.size() > 2) {
                int leaving = fruits[left++];
                int remaining = frequency.get(leaving) - 1;
                if (remaining == 0) {
                    frequency.remove(leaving);
                } else {
                    frequency.put(leaving, remaining);
                }
            }
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }
}

