# Java Solutions — 21 — Intervals

[Pattern guide and follow-ups](../README.md) · [Full roadmap](../../README.md)

Compile/submit each file independently using Java 8-compatible features. The seven stateless solutions declare LeetCode's `Solution` class and preserve their inputs. The three calendars use the required stateful classes: `MyCalendar`, `MyCalendarTwo`, and `MyCalendarThree`. Merge and Insert return independent rows. Sorting solutions clone the outer array and never edit its existing rows; Java object sorting can use O(n) temporary storage.

Files are ordered **Easy → Medium → Hard**. Within a difficulty band, this is a recommended study progression.

1. [Meeting Rooms](01-MeetingRooms.java) — **Easy**, Premium; sort by start and check adjacent conflicts using `canAttendMeetings`.
2. [Merge Intervals](02-MergeIntervals.java) — **Medium**; sort by start and extend the last merged range.
3. [Insert Interval](03-InsertInterval.java) — **Medium**; linear prefix/overlap/suffix scan; touching closed ranges merge.
4. [Non-overlapping Intervals](04-NonOverlappingIntervals.java) — **Medium**; keep earliest-finishing compatible ranges; touching ranges can stay.
5. [Meeting Rooms II](05-MeetingRoomsII.java) — **Medium**, Premium; count active meeting ends in a min-heap using `minMeetingRooms`.
6. [My Calendar I](06-MyCalendarI.java) — **Medium**; `MyCalendar`; a `TreeMap` of disjoint bookings supports predecessor/successor checks in `O(log(n + 1))` time per call.
7. [My Calendar II](07-MyCalendarII.java) — **Medium**; `MyCalendarTwo`; sweep endpoint deltas and roll back any booking that creates triple coverage. `O(n)` time per call.
8. [Minimum Interval to Include Each Query](08-MinimumIntervalToIncludeEachQuery.java) — **Hard**; offline query sweep with a length heap and lazy expiration.
9. [My Calendar III](09-MyCalendarIII.java) — **Hard**; `MyCalendarThree`; accept every booking and return the maximum prefix sum of endpoint deltas. `O(n)` time per call.
10. [Meeting Rooms III](10-MeetingRoomsIII.java) — **Hard**; sort meetings and simulate room assignment with free-ID and busy-finish heaps. Uses `long` for delayed finishing times.

Study the calendar series after Meeting Rooms II. Calendar II/III connect sweep line to prefix sums over sparse, ordered timestamps; a dynamic segment tree is an optional optimization discussed in the guide. Meeting Rooms III extends room counting into room assignment and delayed scheduling with two heaps.

Run this section's local checks from the workspace root:

```bash
bash leetcode-prep/tests/verify.sh 21-intervals
```
