import java.util.ArrayList;
import java.util.List;

// O(n log n) time and O(n) space using index-aware merge sort.
class Solution {
    private int[] values;
    private int[] indexes;
    private int[] buffer;
    private int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        values = nums;
        indexes = new int[nums.length];
        buffer = new int[nums.length];
        counts = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }

        mergeSort(0, nums.length);
        List<Integer> result = new ArrayList<>(nums.length);
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private void mergeSort(int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort(left, middle);
        mergeSort(middle, right);

        int first = left;
        int second = middle;
        int write = left;
        int movedFromRight = 0;
        while (first < middle && second < right) {
            if (values[indexes[second]] < values[indexes[first]]) {
                buffer[write++] = indexes[second++];
                movedFromRight++;
            } else {
                counts[indexes[first]] += movedFromRight;
                buffer[write++] = indexes[first++];
            }
        }
        while (first < middle) {
            counts[indexes[first]] += movedFromRight;
            buffer[write++] = indexes[first++];
        }
        while (second < right) {
            buffer[write++] = indexes[second++];
        }
        System.arraycopy(buffer, left, indexes, left, right - left);
    }
}

