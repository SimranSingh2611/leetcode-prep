/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort meetings by start and heap the end times of active meetings.
 * - Removing every meeting ending by the next start makes heap size equal current overlap; its
 *   maximum is the minimum room count.
 * Time complexity: O(n log(n + 1)) for n meetings.
 * Space complexity: O(n) auxiliary sorted-copy/heap storage.
 */

// 253. Meeting Rooms II
// https://leetcode.com/problems/meeting-rooms-ii/
// LeetCode Premium. O(n log n) time; O(n) auxiliary space. Preserves input.
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> activeEnds = new PriorityQueue<>();
        int rooms = 0;
        for (int[] meeting : sorted) {
            while (!activeEnds.isEmpty() && activeEnds.peek() <= meeting[0]) {
                activeEnds.poll();
            }
            activeEnds.offer(meeting[1]);
            rooms = Math.max(rooms, activeEnds.size());
        }
        return rooms;
    }
}
