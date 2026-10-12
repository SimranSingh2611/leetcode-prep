// 295. Find Median from Data Stream
// https://leetcode.com/problems/find-median-from-data-stream/
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
