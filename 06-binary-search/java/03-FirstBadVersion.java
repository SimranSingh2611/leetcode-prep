/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - Good versions followed by bad versions form a monotonic predicate.
 * - I'd keep version zero as a virtual good endpoint and n as a known bad endpoint, querying
 *   only interior versions.
 * Time complexity: O(log(n + 1)) API calls; the same time bound assumes each call costs O(1).
 * Space complexity: O(1) auxiliary space.
 */

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
