/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd compare squared distances, since square roots do not change their order.
 * - Sorting by that key gives the first k points; quickselect is a useful follow-up when a full
 *   ordering is unnecessary.
 * Time complexity: O(n log(n + 1)) for the primary sorting solution.
 * Space complexity: O(n) worst-case object-sort workspace, plus O(k) output references.
 */

// 973. K Closest Points to Origin
// https://leetcode.com/problems/k-closest-points-to-origin/
// NeetCode 150: https://neetcode.io/problems/k-closest-points-to-origin/question?list=neetcode150
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// Sorting baseline: O(n log n) time; concise and deterministic.
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> Long.compare(distance(a), distance(b)));
        return Arrays.copyOf(points, k);
    }

    private long distance(int[] point) {
        return (long) point[0] * point[0] + (long) point[1] * point[1];
    }
}

// Follow-up: randomized quickselect gives O(n) expected time and O(1) extra space.
class QuickselectSolution {
    public int[][] kClosest(int[][] points, int k) {
        int left = 0;
        int right = points.length - 1;
        while (left <= right) {
            int pivot = partition(points, left, right);
            if (pivot == k - 1) {
                break;
            }
            if (pivot < k - 1) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] points, int left, int right) {
        int random = ThreadLocalRandom.current().nextInt(left, right + 1);
        swap(points, random, right);
        long pivotDistance = distance(points[right]);
        int boundary = left;
        for (int i = left; i < right; i++) {
            if (distance(points[i]) <= pivotDistance) {
                swap(points, boundary++, i);
            }
        }
        swap(points, boundary, right);
        return boundary;
    }

    private long distance(int[] point) {
        return (long) point[0] * point[0] + (long) point[1] * point[1];
    }

    private void swap(int[][] points, int first, int second) {
        int[] temporary = points[first];
        points[first] = points[second];
        points[second] = temporary;
    }
}

