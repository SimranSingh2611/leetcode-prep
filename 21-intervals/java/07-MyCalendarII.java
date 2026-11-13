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

    Map<Integer, Integer> tm;
    public MyCalendarTwo() {
        tm = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        
        tm.put(start, tm.getOrDefault(start,0)+1);
        tm.put(end, tm.getOrDefault(end, 0)-1);
        
        int max=0, sum=0;
        for(int val : tm.values()) {
            sum += val;
            max = Math.max(max, sum);
            if(max > 2) {
                tm.put(start, tm.getOrDefault(start,0)-1);
                tm.put(end, tm.getOrDefault(end,0)+1);
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
