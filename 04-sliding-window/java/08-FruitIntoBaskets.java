/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd frame this as the longest window with at most two distinct fruit types.
 * - When a third type appears, I'd shrink and update counts until the window is valid again.
 * Time complexity: O(n) expected for n fruits.
 * Space complexity: O(1) auxiliary space; at most three types are tracked during adjustment.
 */

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

