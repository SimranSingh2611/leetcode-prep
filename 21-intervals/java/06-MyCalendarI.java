/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep accepted bookings in a TreeMap from start to end and check the new booking's
 *   predecessor and successor before inserting it.
 * - Accepted bookings never overlap, so only those neighbors can conflict; touching half-open
 *   endpoints are allowed, and a rejected booking never changes the map.
 * Time complexity: O(log(n + 1)) per book call for n accepted bookings so far.
 * Space complexity: O(n) persistent booking storage; O(1) auxiliary space per call.
 */

// 729. My Calendar I
// https://leetcode.com/problems/my-calendar-i/
// MyCalendar is the required design class. Bookings use [startTime, endTime).
import java.util.Map;
import java.util.TreeMap;

class MyCalendar {
    private final TreeMap<Integer, Integer> bookings = new TreeMap<>();

    public MyCalendar() {}

    public boolean book(int startTime, int endTime) {
        Map.Entry<Integer, Integer> previous = bookings.floorEntry(startTime);
        if (previous != null && previous.getValue() > startTime) {
            return false;
        }

        Map.Entry<Integer, Integer> next = bookings.ceilingEntry(startTime);
        if (next != null && next.getKey() < endTime) {
            return false;
        }

        bookings.put(startTime, endTime);
        return true;
    }
}
