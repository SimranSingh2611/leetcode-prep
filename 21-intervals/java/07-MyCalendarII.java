/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store +1 at each booking start and -1 at its end in a TreeMap, then sweep the endpoint
 *   changes to count active bookings.
 * - A prefix sum above two means a triple booking; undo both changes before rejecting it.
 *   Removing zero changes also prevents rejected bookings from leaving unused endpoints.
 * Time complexity: O(n) per book call, O(n^2) over n calls; map updates cost O(log(n + 1)).
 * Space complexity: O(n) persistent endpoint storage; O(1) auxiliary space per call.
 */

// 731. My Calendar II
// https://leetcode.com/problems/my-calendar-ii/
// MyCalendarTwo is the required design class. Equal-time changes are combined for [start, end).
import java.util.TreeMap;

class MyCalendarTwo {
    private final TreeMap<Integer, Integer> changes = new TreeMap<>();

    public MyCalendarTwo() {}

    public boolean book(int startTime, int endTime) {
        addChange(startTime, 1);
        addChange(endTime, -1);

        int active = 0;
        int maximum = 0;
        for (int change : changes.values()) {
            active += change;
            maximum = Math.max(maximum, active);
        }

        if (maximum > 2) {
            addChange(startTime, -1);
            addChange(endTime, 1);
            return false;
        }
        return true;
    }

    private void addChange(int time, int change) {
        int updated = changes.getOrDefault(time, 0) + change;
        if (updated == 0) {
            changes.remove(time);
        } else {
            changes.put(time, updated);
        }
    }
}
