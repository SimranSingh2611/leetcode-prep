import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// O(n log n) time and O(n) output space.
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(interval.clone());
            } else {
                int[] previous = merged.get(merged.size() - 1);
                previous[1] = Math.max(previous[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}

