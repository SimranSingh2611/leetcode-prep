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
// NeetCode 150: https://neetcode.io/problems/meeting-schedule-ii/question?list=neetcode150
// LeetCode Premium. O(n log n) time; O(n) auxiliary space. Preserves input.
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        
        Arrays.sort(intervals, (a, b) -> ( (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]));

        // Create a min Heap to store only the end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(intervals[0][1]);

        for(int i=1; i<intervals.length; i++) {
            int currStart = intervals[i][0];
            // Checking if the current interval is not overlapping with the latest meeting in the heap. Then we can remove that particular meeting from the queue.
            if (currStart >= minHeap.peek())
                minHeap.poll();
            
            // Add the current interval end to the heap
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
}
