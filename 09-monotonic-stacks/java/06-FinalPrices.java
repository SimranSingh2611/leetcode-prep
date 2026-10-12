// 1475. Final Prices With a Special Discount in a Shop
// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
// Guide template 4, next smaller-or-equal variant: pop >=; strictly increasing prices.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {
        // next smaller

        int n = prices.length;
        int[] out = prices.clone();

        Stack<Integer> s = new Stack<>();
        for(int i=0; i<n; i++) {
            // standard is < sign; if price =, then discount = 0
            while (!s.isEmpty() && prices[i] <= prices[s.peek()]) {
                int ans = s.pop();
                int profit = prices[ans] - prices[i];
                out[ans] = profit;
            }
            s.push(i);
        }
        
        return out;
    }
}
