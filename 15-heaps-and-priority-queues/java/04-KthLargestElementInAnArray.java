/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd retain the largest k values in a min-heap, whose root is the required rank.
 * - Quickselect is a follow-up that partitions around index n - k and avoids maintaining an
 *   ordered candidate set.
 * Time complexity: Primary heap: O(n log(k + 1)); randomized quickselect: expected O(n), worst-
 *   case O(n^2).
 * Space complexity: Primary: O(k) auxiliary heap; iterative quickselect: O(1) auxiliary space
 *   and mutates the input.
 */

// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/
// NeetCode 150: https://neetcode.io/problems/kth-largest-element-in-an-array/question?list=neetcode150
// Solution preserves nums. QuickselectSolution is the expected-time and auxiliary-space
// improvement, with a quadratic worst-case caveat.
import java.util.*;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int x : nums) {
            heap.offer(x);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }
}

class QuickselectSolution {
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k, left = 0, right = nums.length - 1;
        while (left <= right) {
            int pivot =
                    nums[java.util.concurrent.ThreadLocalRandom.current().nextInt(left, right + 1)];
            int lt = left, i = left, gt = right;
            while (i <= gt) {
                if (nums[i] < pivot) {
                    swap(nums, lt++, i++);
                } else if (nums[i] > pivot) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }
            if (target < lt) {
                right = lt - 1;
            } else if (target > gt) {
                left = gt + 1;
            } else {
                return nums[target];
            }
        }
        throw new IllegalStateException("Valid k must identify a rank");
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
