/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track the cheapest earlier buying price while scanning selling days.
 * - Each day contributes one candidate profit, and keeping the best profit covers every legal
 *   buy-before-sell pair.
 * Time complexity: O(n) for n prices.
 * Space complexity: O(1) auxiliary space.
 */

// O(n) time and O(1) space.
class Solution {
    public int maxProfit(int[] prices) {
        int minimumPrice = Integer.MAX_VALUE;
        int maximumProfit = 0;

        for (int price : prices) {
            minimumPrice = Math.min(minimumPrice, price);
            maximumProfit = Math.max(maximumProfit, price - minimumPrice);
        }
        return maximumProfit;
    }
}

