/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd count values in arr1 and emit the ones named by arr2 in that order.
 * - Then I'd scan the remaining counts from smallest to largest; the bounded value range makes
 *   sorting unnecessary.
 * Time complexity: O(n + m + R) for array lengths n, m and value range R = 1001.
 * Space complexity: O(R) auxiliary counters; the primary implementation rewrites arr1 in place.
 *   With fixed R, this is O(1).
 */

// O(n + R) time and O(R) space, where R = 1001 under the problem constraints.
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] frequency = new int[1001];
        for (int value : arr1) {
            frequency[value]++;
        }

        int write = 0;
        for (int value : arr2) {
            while (frequency[value] > 0) {
                arr1[write++] = value;
                frequency[value]--;
            }
        }
        for (int value = 0; value < frequency.length; value++) {
            while (frequency[value] > 0) {
                arr1[write++] = value;
                frequency[value]--;
            }
        }
        return arr1;
    }
}
