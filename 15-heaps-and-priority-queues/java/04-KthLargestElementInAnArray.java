// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/
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
