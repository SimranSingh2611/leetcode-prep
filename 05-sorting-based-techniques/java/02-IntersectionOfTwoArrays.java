/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort both arrays and move two pointers toward matching values.
 * - On a match, I'd emit the value once and skip duplicates so the result stays unique.
 * Time complexity: O(n log(n + 1) + m log(m + 1)) for array lengths n and m.
 * Space complexity: O(min(n, m)) temporary/result capacity, plus sorting workspace; O(n + m) is
 *   a conservative Java total bound.
 */

// 349. Intersection of Two Arrays
// https://leetcode.com/problems/intersection-of-two-arrays/
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Sorting approach: O(n log n + m log m) time; mutates both inputs.
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] temporary = new int[Math.min(nums1.length, nums2.length)];
        int first = 0;
        int second = 0;
        int size = 0;

        while (first < nums1.length && second < nums2.length) {
            if (nums1[first] < nums2[second]) {
                first++;
            } else if (nums1[first] > nums2[second]) {
                second++;
            } else {
                if (size == 0 || temporary[size - 1] != nums1[first]) {
                    temporary[size++] = nums1[first];
                }
                first++;
                second++;
            }
        }
        return Arrays.copyOf(temporary, size);
    }
}

// Follow-up: O(n + m) expected time and O(n + m) space; preserves inputs.
class HashSetSolution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> firstValues = new HashSet<>();
        Set<Integer> common = new HashSet<>();
        for (int value : nums1) {
            firstValues.add(value);
        }
        for (int value : nums2) {
            if (firstValues.contains(value)) {
                common.add(value);
            }
        }
        return common.stream().mapToInt(Integer::intValue).toArray();
    }
}

