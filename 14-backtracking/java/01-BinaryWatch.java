/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd choose LED positions in increasing index order so each selection appears once.
 * - Only selections producing a valid hour and minute become formatted answers.
 * Time complexity: A fixed bound of O(2^10) search states, so O(1) with ten LEDs.
 * Space complexity: O(10) stack, hence O(1) auxiliary space, excluding the fixed-size output
 *   set.
 */

// 401. Binary Watch
// https://leetcode.com/problems/binary-watch/
// Solution uses choose/recurse without mutable shared path state.
import java.util.*;

class Solution {
    private final int[] weights = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> out = new ArrayList<>();
        search(0, turnedOn, 0, 0, out);
        return out;
    }

    private void search(int start, int left, int hour, int minute, List<String> out) {
        if (hour >= 12 || minute >= 60 || left > 10 - start) {
            return;
        }
        if (left == 0) {
            out.add(hour + ":" + (minute < 10 ? "0" : "") + minute);
            return;
        }
        for (int i = start; i < 10; i++) {
            search(
                    i + 1,
                    left - 1,
                    hour + (i < 4 ? weights[i] : 0),
                    minute + (i >= 4 ? weights[i] : 0),
                    out);
        }
    }
}
