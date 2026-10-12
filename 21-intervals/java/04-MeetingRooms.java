// 252. Meeting Rooms
// https://leetcode.com/problems/meeting-rooms/
// LeetCode Premium. O(n log n) time; O(n) auxiliary copy/sort space. Preserves input.
import java.util.Arrays;

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < sorted.length; i++) {
            // A room is available at the previous meeting's end time.
            if (sorted[i][0] < sorted[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
