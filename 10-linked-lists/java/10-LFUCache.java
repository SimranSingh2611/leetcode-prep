/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd map keys to records and frequencies to insertion-ordered key buckets.
 * - Access promotes a key; eviction takes the oldest key in the minimum-frequency bucket,
 *   implementing both tie rules.
 * Time complexity: Expected O(1) per get or put.
 * Space complexity: O(c) persistent storage for capacity c; empty frequency buckets are
 *   removed.
 */

// 460. LFU Cache
// https://leetcode.com/problems/lfu-cache/
// LFUCache design class; linked-order buckets plus hashing.
import java.util.*;

class LFUCache {
    private static class Entry {
        int value, frequency = 1;

        Entry(int v) {
            value = v;
        }
    }

    private final int capacity;
    private int minimum;
    private final Map<Integer, Entry> entries = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> buckets = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    private void touch(int key, Entry e) {
        LinkedHashSet<Integer> old = buckets.get(e.frequency);
        old.remove(key);
        if (old.isEmpty()) {
            buckets.remove(e.frequency);
            if (minimum == e.frequency) {
                minimum++;
            }
        }
        e.frequency++;
        buckets.computeIfAbsent(e.frequency, k -> new LinkedHashSet<>()).add(key);
    }

    public int get(int key) {
        Entry e = entries.get(key);
        if (e == null) {
            return -1;
        }
        touch(key, e);
        return e.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Entry e = entries.get(key);
        if (e != null) {
            e.value = value;
            touch(key, e);
            return;
        }
        if (entries.size() == capacity) {
            LinkedHashSet<Integer> bucket = buckets.get(minimum);
            int victim = bucket.iterator().next();
            bucket.remove(victim);
            if (bucket.isEmpty()) {
                buckets.remove(minimum);
            }
            entries.remove(victim);
        }
        entries.put(key, new Entry(value));
        buckets.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minimum = 1;
    }
}
