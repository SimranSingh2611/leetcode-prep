// O(n) time and O(n) output space. The problem guarantees |k| < n.
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            return result;
        }

        int start = k > 0 ? 1 : n + k;
        int end = k > 0 ? k : n - 1;
        int windowSum = 0;
        for (int index = start; index <= end; index++) {
            windowSum += code[index % n];
        }

        for (int i = 0; i < n; i++) {
            result[i] = windowSum;
            windowSum -= code[start % n];
            start++;
            end++;
            windowSum += code[end % n];
        }
        return result;
    }
}

