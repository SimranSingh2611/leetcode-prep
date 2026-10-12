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

