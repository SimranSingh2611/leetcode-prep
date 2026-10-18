/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store +1 at each booking start and -1 at its end in a TreeMap and sweep the changes
 *   in time order after every booking.
 * - The running prefix sum is current concurrency; its maximum is the answer. Every booking
 *   is accepted, so this version needs no rollback or capacity limit.
 * Time complexity: O(n) per book call, O(n^2) over n calls; map updates cost O(log(n + 1)).
 * Space complexity: O(n) persistent endpoint storage; O(1) auxiliary space per call.
 */

// 732. My Calendar III
// https://leetcode.com/problems/my-calendar-iii/
// MyCalendarThree is the required design class. A sweep is sufficient for the 400-call limit.
import java.util.TreeMap;

class MyCalendarThree {
    private final TreeMap<Integer, Integer> changes = new TreeMap<>();

    public MyCalendarThree() {}

    public int book(int startTime, int endTime) {
        addChange(startTime, 1);
        addChange(endTime, -1);

        int active = 0;
        int maximum = 0;
        for (int change : changes.values()) {
            active += change;
            maximum = Math.max(maximum, active);
        }
        return maximum;
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
