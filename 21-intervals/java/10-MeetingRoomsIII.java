/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd process meetings by original start time, using a min-heap of free room IDs and a
 *   second heap of busy rooms ordered by finish time, then room ID.
 * - Free every room finishing by the current start; if none are free, delay the meeting to
 *   the earliest finish while preserving its duration. Use long for accumulated finish times.
 * Time complexity: O(m log(m + 1) + m log(n + 1) + n) for m meetings and n rooms.
 * Space complexity: O(m + n) auxiliary sorted-copy/sort workspace, heaps, and room counts.
 */

// 2402. Meeting Rooms III
// https://leetcode.com/problems/meeting-rooms-iii/
// Preserves the input. Meetings use [start, end); delayed finish times can exceed int.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int[][] sorted = meetings.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        List<Integer> roomIds = new ArrayList<>(n);
        for (int room = 0; room < n; room++) {
            roomIds.add(room);
        }
        PriorityQueue<Integer> available = new PriorityQueue<>(roomIds);
        // Each entry is [finishTime, roomId].
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            int byFinish = Long.compare(a[0], b[0]);
            return byFinish != 0 ? byFinish : Long.compare(a[1], b[1]);
        });
        int[] counts = new int[n];

        for (int[] meeting : sorted) {
            long start = meeting[0];
            long duration = (long) meeting[1] - meeting[0];
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.offer((int) busy.poll()[1]);
            }

            int room;
            if (!available.isEmpty()) {
                room = available.poll();
            } else {
                long[] earliest = busy.poll();
                start = earliest[0];
                room = (int) earliest[1];
            }

            busy.offer(new long[] {start + duration, room});
            counts[room]++;
        }

        int answer = 0;
        for (int room = 1; room < n; room++) {
            if (counts[room] > counts[answer]) {
                answer = room;
            }
        }
        return answer;
    }
}
