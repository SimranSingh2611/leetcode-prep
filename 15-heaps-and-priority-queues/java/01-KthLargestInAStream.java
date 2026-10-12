/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep only the largest k values seen so far in a min-heap.
 * - After an insertion, removing any excess smallest value leaves the kth largest at the root.
 * Time complexity: Construction: O(n log(k + 1)) for n initial values; each add: O(log(k + 1)).
 * Space complexity: O(k) persistent heap storage.
 */

// 703. Kth Largest Element in a Stream
// https://leetcode.com/problems/kth-largest-element-in-a-stream/
// KthLargest is the judge's required design class; the input array is unchanged.
import java.util.*;

class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> heap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    }
}
