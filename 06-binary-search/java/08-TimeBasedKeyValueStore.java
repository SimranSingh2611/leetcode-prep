// 981. Time Based Key-Value Store
// https://leetcode.com/problems/time-based-key-value-store/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

// LeetCode's required class. Expected amortized O(1) set, O(log(m + 1)) get.
/*
 * TYPE (get): MAXIMIZATION -- latest history index with timestamp <= query.
 * BOUNDS: lo=-1 (virtual true), hi=m (virtual false); return value at lo or "".
 */
class TimeMap {
    private static class Entry {
        final int timestamp;
        final String value;

        Entry(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    private final Map<String, List<Entry>> history = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        history.computeIfAbsent(key, ignored -> new ArrayList<>())
                .add(new Entry(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Entry> entries = history.get(key);
        if (entries == null) {
            return "";
        }

        int lo = -1; // Virtual true endpoint: no qualifying write yet.
        int hi = entries.size(); // Virtual false endpoint, never read.
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (entries.get(mid).timestamp <= timestamp) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo == -1 ? "" : entries.get(lo).value;
    }
}

// Separate data-structure follow-up, not a binary-search template implementation.
// Out-of-order writes: O(log(m + 1)) set and get. Trade faster appends for flexible ordering.
class OutOfOrderTimeMap {
    private final Map<String, NavigableMap<Integer, String>> history = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        history.computeIfAbsent(key, ignored -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        NavigableMap<Integer, String> values = history.get(key);
        if (values == null) {
            return "";
        }
        Map.Entry<Integer, String> entry = values.floorEntry(timestamp);
        return entry == null ? "" : entry.getValue();
    }
}
