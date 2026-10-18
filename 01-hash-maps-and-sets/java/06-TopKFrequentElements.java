/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd count frequencies, then put values into buckets indexed by their counts.
 * - Scanning buckets from highest frequency gives the top k without sorting all values.
 * Time complexity: O(n) expected for bucket sorting; the heap alternative takes O(n log(k +
 *   1)).
 * Space complexity: O(n) auxiliary bucket/map space, plus O(k) output.
 */

// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/
// NeetCode 150: https://neetcode.io/problems/top-k-elements-in-list/question?list=neetcode150
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// Recommended: bucket sort gives O(n) time and O(n) space.
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int value : nums) {
            frequency.merge(value, 1, Integer::sum);
        }

        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int count = entry.getValue();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(entry.getKey());
        }

        int[] result = new int[k];
        int write = 0;
        for (int count = buckets.length - 1; count >= 0 && write < k; count--) {
            if (buckets[count] == null) {
                continue;
            }
            for (int value : buckets[count]) {
                result[write++] = value;
                if (write == k) {
                    break;
                }
            }
        }
        return result;
    }
}

// Follow-up: O(n log k) time and O(u + k) space; useful when k is small.
class HeapSolution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int value : nums) {
            frequency.merge(value, 1, Integer::sum);
        }

        PriorityQueue<Integer> heap =
                new PriorityQueue<>((a, b) -> Integer.compare(frequency.get(a), frequency.get(b)));
        for (int value : frequency.keySet()) {
            heap.offer(value);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll();
        }
        return result;
    }
}

