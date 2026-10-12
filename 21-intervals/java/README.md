# Java Solutions — 21 — Intervals

[Pattern guide and follow-ups](../README.md) · [Full roadmap](../../README.md)

Compile/submit each file independently; each declares LeetCode's `Solution` class and uses Java 8-compatible features. All six preserve their inputs. Merge and Insert return independent rows. Sorting solutions clone the outer array and never edit its existing rows; Java object sorting can use O(n) temporary storage.

1. [Insert Interval](01-InsertInterval.java) — Linear prefix/overlap/suffix scan; touching closed ranges merge.
2. [Merge Intervals](02-MergeIntervals.java) — Sort by start and extend the last merged range.
3. [Non-overlapping Intervals](03-NonOverlappingIntervals.java) — Keep earliest-finishing compatible ranges; touching ranges can stay.
4. [Meeting Rooms](04-MeetingRooms.java) — Premium; sort by start and check adjacent conflicts using `canAttendMeetings`.
5. [Meeting Rooms II](05-MeetingRoomsII.java) — Premium; count active meeting ends in a min-heap using `minMeetingRooms`.
6. [Minimum Interval to Include Each Query](06-MinimumIntervalToIncludeEachQuery.java) — Offline query sweep with a length heap and lazy expiration.

Run this section's local checks from the workspace root:

```bash
bash Leetcode/tests/verify.sh 21-intervals
```
