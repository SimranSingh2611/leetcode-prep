/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - The smallest remaining card must begin a group, so I'd consume consecutive values from it.
 * - A missing required value proves failure; an ordered frequency map keeps the next group
 *   start available.
 * Time complexity: O(n log(n + 1)) for n cards.
 * Space complexity: O(n) auxiliary ordered-map space.
 */

// 846. Hand of Straights
// https://leetcode.com/problems/hand-of-straights/
// NeetCode 150: https://neetcode.io/problems/hand-of-straights/question?list=neetcode150
import java.util.TreeMap;

// O(n log n) time and O(n) space.
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        TreeMap<Integer, Integer> frequency = new TreeMap<>();
        for (int card : hand) {
            frequency.merge(card, 1, Integer::sum);
        }

        while (!frequency.isEmpty()) {
            int start = frequency.firstKey();
            for (int offset = 0; offset < groupSize; offset++) {
                int card = start + offset;
                Integer count = frequency.get(card);
                if (count == null) {
                    return false;
                }
                if (count == 1) {
                    frequency.remove(card);
                } else {
                    frequency.put(card, count - 1);
                }
            }
        }
        return true;
    }
}

