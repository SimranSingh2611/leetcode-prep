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
