/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd count earlier prefix sums in a map seeded with zero once.
 * - For each current sum, the frequency of current - k counts all matching subarrays ending
 *   here; I'd query before inserting.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(n) auxiliary prefix-frequency space.
 */

// 560. Subarray Sum Equals K
// https://leetcode.com/problems/subarray-sum-equals-k/
import java.util.HashMap;
import java.util.Map;

// O(n) expected time and O(n) space.
class Solution {
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

