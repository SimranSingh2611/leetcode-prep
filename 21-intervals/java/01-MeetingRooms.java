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
// NeetCode 150: https://neetcode.io/problems/meeting-schedule/question?list=neetcode150
// LeetCode Premium. O(n log n) time; O(n) auxiliary copy/sort space. Preserves input.
import java.util.Arrays;

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null)
            return false;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for(int i=1; i<intervals.length; i++) {
            int currStart = intervals[i][0];
            int prevEnd = intervals[i-1][1];

            if (currStart < prevEnd)
                return false;
        }

        return true;
    }
}
