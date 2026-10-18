/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd first find the rotation pivot using the fixed last value.
 * - The target's relation to that last value selects a sorted segment, where the same lower-
 *   bound loop can check for a match.
 * Time complexity: O(log(n + 1)); two sequential logarithmic searches keep the same bound.
 * Space complexity: O(1) auxiliary space.
 */

// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/
// NeetCode 150: https://neetcode.io/problems/find-target-in-rotated-sorted-array/question?list=neetcode150
/*
 * TYPE: MINIMIZATION twice -- pivot, then lower bound in its sorted segment.
 * PIVOT: nums[i] <= last; lo=-1, hi=n-1.
 * TARGET: nums[i] >= target; lo=start-1, hi=endExclusive.
 * RESULT: matching hi, otherwise -1; virtual bounds stay unread.
 */
// O(log(n + 1)) time overall, O(1) auxiliary space. Requires distinct values; no mutation.
class Solution {
    public int search(int[] nums, int target) {
     // Idea - find index of the minimum element. So separate binary searches on both the sides of the min element.
        int minElementIndex = findMinIndex(nums);
        int n = nums.length;
       
        int leftHalf = binarySearch(nums, -1, minElementIndex, target);
        if (leftHalf != -1)
            return leftHalf;
        int rightHalf = binarySearch(nums, minElementIndex-1, n-1, target);
        return rightHalf;   
    }

    // minimization problem - we need to find first element that is lesser than last element. 
    public int findMinIndex(int[] nums) {

        int left = -1, right = nums.length-1;
        int lastElem = nums[right];

        while (left + 1 < right) {
            int mid = left + (right-left)/2;

            if (nums[mid] >= lastElem) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return right];
    }

    private int binarySearch(int[] nums, int start, int end, int target) {

        while (start + 1 < end) {
            int mid = start + (end - start)/2;

            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= target)
                end = mid;
            else
                start = mid;
        }
        return nums[end] == target ? end : -1;
    }
}