// O(A^2 * n) time and O(A) space; A = 26 for lowercase English letters.
class Solution {
    public int largestVariance(String s) {
        int[] totalFrequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            totalFrequency[s.charAt(i) - 'a']++;
        }

        int answer = 0;
        for (int major = 0; major < 26; major++) {
            for (int minor = 0; minor < 26; minor++) {
                if (major == minor || totalFrequency[major] == 0 || totalFrequency[minor] == 0) {
                    continue;
                }

                int majorCount = 0;
                int minorCount = 0;
                int remainingMinor = totalFrequency[minor];

                for (int i = 0; i < s.length(); i++) {
                    int character = s.charAt(i) - 'a';
                    if (character == major) {
                        majorCount++;
                    } else if (character == minor) {
                        minorCount++;
                        remainingMinor--;
                    } else {
                        continue;
                    }

                    if (minorCount > 0) {
                        answer = Math.max(answer, majorCount - minorCount);
                    }
                    if (majorCount < minorCount && remainingMinor > 0) {
                        majorCount = 0;
                        minorCount = 0;
                    }
                }
            }
        }
        return answer;
    }
}

