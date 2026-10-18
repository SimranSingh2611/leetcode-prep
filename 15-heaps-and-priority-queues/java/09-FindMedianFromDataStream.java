/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd split the stream between a max-heap for the lower half and a min-heap for the upper
 *   half.
 * - Rebalancing keeps their sizes equal or leaves one extra lower value, so their roots
 *   determine the median.
 * Time complexity: Each addNum: O(log(n + 1)); findMedian: O(1) for n stored values.
 * Space complexity: O(n) persistent heap storage.
 */

// 295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/
// NeetCode 150: https://neetcode.io/problems/find-median-in-a-data-stream/question?list=neetcode150
// MedianFinder is the required design class. The average promotes to long before addition to
// prevent int overflow.
import java.util.*;

class MedianFinder {
    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upper = new PriorityQueue<>();

    public MedianFinder() {}

    public void addNum(int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
        } else {
            upper.offer(num);
        }
        if (lower.size() > upper.size() + 1) {
            upper.offer(lower.poll());
        } else if (upper.size() > lower.size()) {
            lower.offer(upper.poll());
        }
    }

    public double findMedian() {
        if (lower.size() > upper.size()) {
            return lower.peek();
        }
        return ((long) lower.peek() + upper.peek()) / 2.0;
    }
}
