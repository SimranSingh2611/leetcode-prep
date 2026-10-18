# Java Solutions — 21 — Intervals

[Pattern guide and follow-ups](../README.md) · [Full roadmap](../../README.md)

Compile/submit each file independently using Java 8-compatible features. The seven stateless solutions declare LeetCode's `Solution` class and preserve their inputs. The three calendars use the required stateful classes: `MyCalendar`, `MyCalendarTwo`, and `MyCalendarThree`. Merge and Insert return independent rows. Sorting solutions clone the outer array and never edit its existing rows; Java object sorting can use O(n) temporary storage.

Files are ordered **Easy → Medium → Hard**. Within a difficulty band, this is a recommended study progression.

1. [Meeting Rooms](01-MeetingRooms.java) · [LeetCode](https://leetcode.com/problems/meeting-rooms/) · [NeetCode 150](https://neetcode.io/problems/meeting-schedule/question?list=neetcode150) — **Easy**, Premium; sort by start and check adjacent conflicts using `canAttendMeetings`.
2. [Merge Intervals](02-MergeIntervals.java) · [LeetCode](https://leetcode.com/problems/merge-intervals/) · [NeetCode 150](https://neetcode.io/problems/merge-intervals/question?list=neetcode150) — **Medium**; sort by start and extend the last merged range.
3. [Insert Interval](03-InsertInterval.java) · [LeetCode](https://leetcode.com/problems/insert-interval/) · [NeetCode 150](https://neetcode.io/problems/insert-new-interval/question?list=neetcode150) — **Medium**; linear prefix/overlap/suffix scan; touching closed ranges merge.
4. [Non-overlapping Intervals](04-NonOverlappingIntervals.java) · [LeetCode](https://leetcode.com/problems/non-overlapping-intervals/) · [NeetCode 150](https://neetcode.io/problems/non-overlapping-intervals/question?list=neetcode150) — **Medium**; keep earliest-finishing compatible ranges; touching ranges can stay.
5. [Meeting Rooms II](05-MeetingRoomsII.java) · [LeetCode](https://leetcode.com/problems/meeting-rooms-ii/) · [NeetCode 150](https://neetcode.io/problems/meeting-schedule-ii/question?list=neetcode150) — **Medium**, Premium; count active meeting ends in a min-heap using `minMeetingRooms`.
6. [My Calendar I](06-MyCalendarI.java) · [LeetCode](https://leetcode.com/problems/my-calendar-i/) — **Medium**; `MyCalendar`; a `TreeMap` of disjoint bookings supports predecessor/successor checks in `O(log(n + 1))` time per call.
7. [My Calendar II](07-MyCalendarII.java) · [LeetCode](https://leetcode.com/problems/my-calendar-ii/) — **Medium**; `MyCalendarTwo`; sweep endpoint deltas and roll back any booking that creates triple coverage. `O(n)` time per call.
8. [Minimum Interval to Include Each Query](08-MinimumIntervalToIncludeEachQuery.java) · [LeetCode](https://leetcode.com/problems/minimum-interval-to-include-each-query/) · [NeetCode 150](https://neetcode.io/problems/minimum-interval-including-query/question?list=neetcode150) — **Hard**; offline query sweep with a length heap and lazy expiration.
9. [My Calendar III](09-MyCalendarIII.java) · [LeetCode](https://leetcode.com/problems/my-calendar-iii/) — **Hard**; `MyCalendarThree`; accept every booking and return the maximum prefix sum of endpoint deltas. `O(n)` time per call.
10. [Meeting Rooms III](10-MeetingRoomsIII.java) · [LeetCode](https://leetcode.com/problems/meeting-rooms-iii/) — **Hard**; sort meetings and simulate room assignment with free-ID and busy-finish heaps. Uses `long` for delayed finishing times.

Study the calendar series after Meeting Rooms II. Calendar II/III connect sweep line to prefix sums over sparse, ordered timestamps; a dynamic segment tree is an optional optimization discussed in the guide. Meeting Rooms III extends room counting into room assignment and delayed scheduling with two heaps.

Run this section's local checks from the workspace root:

```bash
bash leetcode-prep/tests/verify.sh 21-intervals
```
