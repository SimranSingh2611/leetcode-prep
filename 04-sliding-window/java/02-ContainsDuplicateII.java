/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep a set of values from the allowed index-distance window.
 * - Before checking the next value, I'd expire anything too far behind; a match then satisfies
 *   both duplication and distance.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(min(n, k + 1)) auxiliary space, including the current value during
 *   insertion.
 */

// 219. Contains Duplicate II
// https://leetcode.com/problems/contains-duplicate-ii/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Recommended: O(n) expected time and O(min(n, k)) space.
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        for (int right = 0; right < nums.length; right++) {
            if (right > k) {
                window.remove(nums[right - k - 1]);
            }
            if (!window.add(nums[right])) {
                return true;
            }
        }
        return false;
    }
}

// Follow-up: O(n) space but directly exposes the matching index distance.
class LastIndexSolution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer previous = lastIndex.put(nums[i], i);
            if (previous != null && i - previous <= k) {
                return true;
            }
        }
        return false;
    }
}

