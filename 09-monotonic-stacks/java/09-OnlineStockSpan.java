// 901. Online Stock Span
// https://leetcode.com/problems/online-stock-span/
// Guide template 2, previous strictly greater: pop <=; strictly decreasing prices.
// Streaming adaptation: each entry holds {price, day index}, since no array exists.
// O(1) amortized time per next (O(n) worst case); O(n) total auxiliary space.
import java.util.*;

class StockSpanner {
    // Pair: price, day index
    Stack<Pair<Integer, Integer>> s;
    int index;

    public StockSpanner() {
        s = new Stack<>();
        index = 0;
    }

    public int next(int price) {
        index++; // First day is 1.

        // Discard candidates that are not strictly greater.
        while (!s.isEmpty() && s.peek().getKey() <= price) {
            s.pop();
        }

        // After popping, top is the previous strictly greater day.
        int left = s.isEmpty() ? 0 : s.peek().getValue();

        int span = index - left;

        s.push(new Pair<>(price, index));
        return span;
    }
}