// 441. Arranging Coins
// https://leetcode.com/problems/arranging-coins/
/*
 * TYPE: MAXIMIZATION -- most complete rows with k*(k+1)/2 <= n.
 * BOUNDS: lo=0 (feasible), hi=n+1 (infeasible), using long; return lo.
 */
// O(log(n + 1)) time, O(1) auxiliary space.
class Solution {
    public int arrangeCoins(int n) {
        long lo = 0;
        long hi = (long) n + 1;
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * (mid + 1) / 2 <= n) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return (int) lo;
    }
}
