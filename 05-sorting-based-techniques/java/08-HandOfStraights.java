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

