// 278. First Bad Version
// https://leetcode.com/problems/first-bad-version/
/* LeetCode supplies VersionControl and boolean isBadVersion(int version). */
/*
 * TYPE: MINIMIZATION -- first bad version.
 * BOUNDS: lo=0 (virtual good), hi=n (known bad); return hi.
 */
// O(log(n + 1)) API calls, O(1) auxiliary space. Never queries version 0 or n+1.
class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lo = 0;
        int hi = n;
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (isBadVersion(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
