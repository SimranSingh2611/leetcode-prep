/*
 * Interview quick notes — primary solution:
 * - Reuse LC 560: every earlier prefix equal to prefixSum - goal is a match.
 * - Binary input changes the available alternatives, not the prefix-map equation.
 * Time complexity: O(n) expected.
 * Space complexity: O(n).
 */

// 930. Binary Subarrays With Sum
// https://leetcode.com/problems/binary-subarrays-with-sum/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return subarraySum(nums, goal);
    }

    public int subarraySum(int[] nums, int k) {
        // Initialize the length of the input array
        int n = nums.length;

        // Create a HashMap to store the frequency of each prefix sum
        Map<Integer, Integer> prefixSumFreqMap = new HashMap<>();

        // Initialize the frequency of prefix sum 0 to 1
        prefixSumFreqMap.put(0, 1);

        // Initialize a counter to keep track of the number of subarrays that sum to k
        int ctr = 0;

        // Initialize the prefix sum variable
        int prefixSum = 0;

        // Loop through the array to calculate prefix sums and update the counter
        for (int i = 0; i < n; i++) {
            // Update the prefix sum
            prefixSum += nums[i];

            // Check if there's a prefix sum such that the subarray sum equals k
            if (prefixSumFreqMap.containsKey(prefixSum - k)) {
                // Increment the counter by the frequency of the matching prefix sum
                ctr += prefixSumFreqMap.get(prefixSum - k);
            }

            // Update the frequency map for the current prefix sum
            prefixSumFreqMap.put(prefixSum, prefixSumFreqMap.getOrDefault(prefixSum, 0) + 1);
        }

        // Return the final count of subarrays that sum to k
        return ctr;
    }
}
