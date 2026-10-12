import java.util.HashMap;
import java.util.Map;

// Recommended for the problem's lowercase-English constraint: O(n) time, O(1) space.
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}

// Follow-up: handles arbitrary Unicode code points in O(n) time and O(k) space.
class UnicodeSolution {
    public boolean isAnagram(String s, String t) {
        Map<Integer, Integer> counts = new HashMap<>();
        s.codePoints().forEach(cp -> counts.merge(cp, 1, Integer::sum));
        t.codePoints().forEach(cp -> counts.merge(cp, -1, Integer::sum));
        counts.values().removeIf(count -> count == 0);
        return counts.isEmpty();
    }
}

