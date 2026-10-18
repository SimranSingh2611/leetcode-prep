/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd binary-search the partition in the smaller array so both left halves contain half the
 *   values.
 * - The valid boundary makes every left value no greater than every right value, letting the
 *   median come from the partition edges.
 * Time complexity: O(1 + log(min(m, n) + 1)) for array lengths m and n.
 * Space complexity: O(1) auxiliary space, including at most one argument-swap call.
 */

// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// NeetCode 150: https://neetcode.io/problems/median-of-two-sorted-arrays/question?list=neetcode150
/*
 * TYPE: MINIMIZATION -- first cut with B-left <= A-right.
 * BOUNDS: lo=-1 (virtual false), hi=m (true); use hi to calculate the median.
 */
// O(log(min(m,n) + 1) + 1) time, O(1) auxiliary space. Does not mutate either array.
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1); // At most one swap call.
        }
        int m = nums1.length;
        int n = nums2.length;
        if (m + n == 0) {
            throw new IllegalArgumentException("At least one array must be nonempty");
        }
        int leftSize = (m + n + 1) / 2;
        int lo = -1;
        int hi = m; // Taking all of A leaves A-right empty, hence known true.
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            int j = leftSize - mid;
            // Do NOT test both partition inequalities here: that is not monotonic.
            if (j == 0 || nums2[j - 1] <= nums1[mid]) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        int i = hi;
        int j = leftSize - i;
        int aLeft = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int aRight = i == m ? Integer.MAX_VALUE : nums1[i];
        int bLeft = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int bRight = j == n ? Integer.MAX_VALUE : nums2[j];

        // Minimality of i also guarantees A-left <= B-right (see the README proof).
        int leftMaximum = Math.max(aLeft, bLeft);
        if ((m + n) % 2 == 1) {
            return leftMaximum;
        }
        int rightMinimum = Math.min(aRight, bRight);
        return ((long) leftMaximum + rightMinimum) / 2.0;
    }
}
