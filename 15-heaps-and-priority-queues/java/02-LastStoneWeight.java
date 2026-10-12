// 1046. Last Stone Weight
// https://leetcode.com/problems/last-stone-weight/
// Solution uses reverseOrder rather than subtraction in its comparator and preserves stones.
import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : stones) {
            heap.offer(x);
        }
        while (heap.size() > 1) {
            int a = heap.poll(), b = heap.poll();
            if (a != b) {
                heap.offer(a - b);
            }
        }
        return heap.isEmpty() ? 0 : heap.peek();
    }
}
