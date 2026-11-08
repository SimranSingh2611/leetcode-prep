/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep indices whose first qualifying discount has not arrived yet.
 * - An incoming price resolves all previous prices at least as large; comparisons use the
 *   original array while discounts go into a copy.
 * Time complexity: O(n) amortized overall for n prices.
 * Space complexity: O(n) auxiliary stack, plus O(n) output copy.
 */

// 1475. Final Prices With a Special Discount in a Shop
// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
// Next Smaller-or-Equal adaptation: forward scan, pop >=, subtract the resolving price.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] out = prices.clone();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // Equal prices qualify: current i is waiting j's first discount.
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int j = stack.pop();
                out[j] = prices[j] - prices[i];
            }
            stack.push(i);
        }

        return out;
    }
}
