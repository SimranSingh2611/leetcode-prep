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
    Map<Integer, Integer> tm;
    
    public MyCalendar() {
        tm = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        
        tm.put(start, tm.getOrDefault(start,0)+1);
        tm.put(end, tm.getOrDefault(end,0)-1);
        int sum=0,k=0;
        for(int val : tm.values()) {
            sum+=val;
            k = Math.max(k, sum);
            if(k > 1) {
                tm.put(start, tm.getOrDefault(start,0)-1);
                tm.put(end, tm.getOrDefault(end,0)+1);
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
