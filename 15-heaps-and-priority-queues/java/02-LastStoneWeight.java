/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd repeatedly remove the two heaviest stones from a max-heap.
 * - Equal weights vanish; otherwise their difference returns to the heap for the next round.
 * Time complexity: O(n log(n + 1)) for n stones.
 * Space complexity: O(n) auxiliary heap space.
 */

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
