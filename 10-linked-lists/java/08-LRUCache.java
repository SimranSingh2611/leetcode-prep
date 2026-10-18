/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd combine a hash map for lookup with a doubly linked list ordered by recent use.
 * - Access moves an entry to the front, and eviction removes the tail entry from both
 *   structures.
 * Time complexity: Expected O(1) per get or put.
 * Space complexity: O(c) persistent storage for cache capacity c.
 */

// 146. LRU Cache
// https://leetcode.com/problems/lru-cache/
// NeetCode 150: https://neetcode.io/problems/lru-cache/question?list=neetcode150
// LRUCache design class; explicit doubly linked list.
import java.util.*;

class LRUCache {
    private static class Entry {
        int key, value;
        Entry prev, next;

        Entry(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private final Map<Integer, Entry> entries = new HashMap<>();
    private final Entry head = new Entry(0, 0), tail = new Entry(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void remove(Entry e) {
        e.prev.next = e.next;
        e.next.prev = e.prev;
    }

    private void front(Entry e) {
        e.next = head.next;
        e.prev = head;
        head.next.prev = e;
        head.next = e;
    }

    public int get(int key) {
        Entry e = entries.get(key);
        if (e == null) {
            return -1;
        }
        remove(e);
        front(e);
        return e.value;
    }

    public void put(int key, int value) {
        Entry e = entries.get(key);
        if (e != null) {
            e.value = value;
            remove(e);
            front(e);
            return;
        }
        if (capacity == 0) {
            return;
        }
        if (entries.size() == capacity) {
            Entry old = tail.prev;
            remove(old);
            entries.remove(old.key);
        }
        e = new Entry(key, value);
        entries.put(key, e);
        front(e);
    }
}
