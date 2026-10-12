// 875. Koko Eating Bananas
// https://leetcode.com/problems/koko-eating-bananas/
/*
 * TYPE: MINIMIZATION -- smallest speed finishing within h hours.
 * BOUNDS: lo=0 (infeasible), hi=max(piles) (feasible); return hi.
 */
// O(n * log(M + 1)) time, O(1) auxiliary space. Does not mutate piles.
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 0;
        int hi = 0;
        for (int pile : piles) {
            hi = Math.max(hi, pile);
        }
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (canFinish(piles, h, mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    private boolean canFinish(int[] piles, int h, int speed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + (long) speed - 1) / speed;
            if (hours > h) {
                return false;
            }
        }
        return true;
    }
}
