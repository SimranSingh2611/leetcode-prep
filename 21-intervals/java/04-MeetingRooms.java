/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort meetings by start and check every adjacent pair for a conflict.
 * - A start strictly before the preceding end fails; meetings that touch at endpoints can both
 *   be attended.
 * Time complexity: O(n log(n + 1)) for n meetings.
 * Space complexity: O(n) auxiliary cloned-array/object-sort storage.
 */

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
