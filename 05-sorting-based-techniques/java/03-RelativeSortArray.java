// O(n + R) time and O(R) space, where R = 1001 under the problem constraints.
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] frequency = new int[1001];
        for (int value : arr1) {
            frequency[value]++;
        }

        int write = 0;
        for (int value : arr2) {
            while (frequency[value] > 0) {
                arr1[write++] = value;
                frequency[value]--;
            }
        }
        for (int value = 0; value < frequency.length; value++) {
            while (frequency[value] > 0) {
                arr1[write++] = value;
                frequency[value]--;
            }
        }
        return arr1;
    }
}
