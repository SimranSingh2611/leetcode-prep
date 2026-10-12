/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd turn each range sum into a difference of prefix sums.
 * - While merging sorted halves, two monotonic pointers count the right-half prefixes whose
 *   differences lie within the requested bounds.
 * Time complexity: O(n log(n + 1)) for merge-sort counting.
 * Space complexity: O(n) auxiliary prefix/merge arrays, including an O(log(n + 1)) recursion
 *   stack.
 */

// O(n log n) time and O(n) space using prefix sums plus merge-sort counting.
class Solution {
    private long[] buffer;

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        buffer = new long[prefix.length];
        return (int) sortAndCount(prefix, 0, prefix.length, lower, upper);
    }

    private long sortAndCount(long[] sums, int left, int right, int lower, int upper) {
        if (right - left <= 1) {
            return 0;
        }

        int middle = left + (right - left) / 2;
        long count = sortAndCount(sums, left, middle, lower, upper)
                + sortAndCount(sums, middle, right, lower, upper);

        int firstValid = middle;
        int afterLastValid = middle;
        for (int i = left; i < middle; i++) {
            while (firstValid < right && sums[firstValid] - sums[i] < lower) {
                firstValid++;
            }
            while (afterLastValid < right && sums[afterLastValid] - sums[i] <= upper) {
                afterLastValid++;
            }
            count += afterLastValid - firstValid;
        }

        int first = left;
        int second = middle;
        int write = left;
        while (first < middle && second < right) {
            buffer[write++] = sums[first] <= sums[second] ? sums[first++] : sums[second++];
        }
        while (first < middle) {
            buffer[write++] = sums[first++];
        }
        while (second < right) {
            buffer[write++] = sums[second++];
        }
        System.arraycopy(buffer, left, sums, left, right - left);
        return count;
    }
}

