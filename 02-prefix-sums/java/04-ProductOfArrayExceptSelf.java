// Recommended: O(n) time and O(1) auxiliary space, excluding the result.
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int prefixProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefixProduct;
            prefixProduct *= nums[i];
        }

        int suffixProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return result;
    }
}

// Teaching version: O(n) time and O(n) auxiliary space with explicit arrays.
class ExplicitPrefixSuffixSolution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];
        prefix[0] = 1;
        suffix[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        return result;
    }
}

