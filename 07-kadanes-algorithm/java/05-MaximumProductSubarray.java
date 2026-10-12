/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd carry both the maximum and minimum products ending at each position.
 * - A negative value can turn the minimum into the next maximum, so both states must be
 *   computed from the previous pair.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// Recommended: O(n) time and O(1) space with max/min ending states.
class Solution {
    public int maxProduct(int[] nums) {
        int maximumEnding = nums[0];
        int minimumEnding = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int previousMaximum = maximumEnding;
            maximumEnding = Math.max(value, Math.max(previousMaximum * value, minimumEnding * value));
            minimumEnding = Math.min(value, Math.min(previousMaximum * value, minimumEnding * value));
            answer = Math.max(answer, maximumEnding);
        }
        return answer;
    }
}

// Follow-up: scan products from both directions; zeros reset the product.
class BidirectionalSolution {
    public int maxProduct(int[] nums) {
        int prefixProduct = 1;
        int suffixProduct = 1;
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (prefixProduct == 0) {
                prefixProduct = 1;
            }
            if (suffixProduct == 0) {
                suffixProduct = 1;
            }
            prefixProduct *= nums[i];
            suffixProduct *= nums[nums.length - 1 - i];
            answer = Math.max(answer, Math.max(prefixProduct, suffixProduct));
        }
        return answer;
    }
}

