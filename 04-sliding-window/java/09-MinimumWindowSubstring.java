// O(|s| + |t|) time and O(1) space for the problem's ASCII character set.
class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int[] needed = new int[128];
        for (int i = 0; i < t.length(); i++) {
            needed[t.charAt(i)]++;
        }

        int missing = t.length();
        int left = 0;
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char entering = s.charAt(right);
            if (needed[entering] > 0) {
                missing--;
            }
            needed[entering]--;

            while (missing == 0) {
                int length = right - left + 1;
                if (length < bestLength) {
                    bestLength = length;
                    bestStart = left;
                }

                char leaving = s.charAt(left++);
                needed[leaving]++;
                if (needed[leaving] > 0) {
                    missing++;
                }
            }
        }

        return bestLength == Integer.MAX_VALUE
                ? ""
                : s.substring(bestStart, bestStart + bestLength);
    }
}

