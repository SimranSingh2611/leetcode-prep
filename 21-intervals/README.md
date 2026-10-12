# 21 — Intervals

The six questions from your Intervals screenshot are collected here in the same order: **1 Easy / 4 Medium / 1 Hard**. Learn to distinguish merging ranges, selecting compatible ranges, counting simultaneous meetings, and answering coverage queries.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## How to recognize this pattern

**Keywords / hints:** “overlap,” “insert a range,” “merge intervals,” “meeting rooms,” “minimum removals,” or “smallest interval covering a query.”

**Structural test:** Does sorting by start or end make overlaps local, allow an earliest-finish choice, or let a sweep maintain the relevant intervals as time/query values increase?

**Invariant / mental model:** Track the end of the current merged range, the end of the last selected range, or a heap of meeting ends/eligible query candidates. Choose the state according to what the answer measures.

**When not to use it:** Do not merge ranges when the answer needs the number of concurrent meetings; merging loses that multiplicity. Earliest-finish selection assumes equal interval value. Weighted scheduling generally needs dynamic programming.

**Prerequisites:** [05 sorting](../05-sorting-based-techniques/README.md), [15 heaps](../15-heaps-and-priority-queues/README.md), and [16 greedy reasoning](../16-greedy-and-intervals/README.md). Minimum Interval is the heap/sweep stretch exercise.

## Endpoint rules

| Problem | When endpoints are equal |
| --- | --- |
| Insert Interval / Merge Intervals | Closed ranges touch and **must merge**: next start `<=` current end. |
| Non-overlapping Intervals | Touching ranges are compatible: next start `>=` last selected end. |
| Meeting Rooms / Meeting Rooms II | A meeting ending at time `t` frees its room for one starting at `t`. |
| Minimum Interval to Include Each Query | Both endpoints count; length is `end - start + 1`. Expire only when `end < query`. |

## Existing coverage

Insert Interval and Minimum Interval also appear in [16](../16-greedy-and-intervals/README.md); Merge Intervals and Non-overlapping Intervals also appear in [05](../05-sorting-based-techniques/README.md). These four repeats deliberately make this section self-contained. Meeting Rooms and Meeting Rooms II are new to the roadmap. If you already solved an overlapping question, use this section to review its endpoint rule and follow-ups.

The selection and ordering follow your screenshot of the [NeetCode 150](https://neetcode.io/practice/practice/neetcode150) Intervals category. On **2026-10-09**, the four free problem statements and both meeting-room Premium gates were checked at the linked LeetCode pages. The meeting-room difficulty labels follow the screenshot; their statements are subscription-gated. Existing Blind 75 labels are retained for the earlier selections.

## 1. [Insert Interval](https://leetcode.com/problems/insert-interval/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** One new closed interval is inserted into ranges already sorted by start and non-overlapping. Its overlaps form a contiguous block.

**Solution:** Copy the prefix whose ends are strictly before the new start. Expand the new interval while the next start is at most its evolving end, emit the merged range, then copy the suffix. The sorted/disjoint guarantee makes each completed prefix range final; no sorting is needed.

**Complexity:** O(n) time and O(n) total space for the result/buffer; O(1) scanning state beyond that storage.

**Java:** [InsertInterval](./java/01-InsertInterval.java) — Preserves both inputs and returns independent interval rows.

**Potential interviewer follow-ups:**

- What if the existing intervals are unsorted or overlapping? Append the new range, sort by start, and run Merge Intervals; the one-pass insertion guarantee disappears.
- Does finding the insertion point with binary search make the whole operation O(log n)? No; producing a new array still takes O(n) in the worst case.

## 2. [Merge Intervals](https://leetcode.com/problems/merge-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** Unordered ranges must become a disjoint union; preserve coverage rather than the original range identities.

**Solution:** Sort by start. If the next start is no greater than the last merged end, extend that end; otherwise start a new output range. Once a gap appears, sorted future starts cannot fill it, so the previous merged range is final. Nested ranges require taking the maximum end, not simply assigning the next end.

**Complexity:** O(n log n) time, O(n) auxiliary space for the outer-array copy and object sorting, plus O(n) output storage.

**Java:** [MergeIntervals](./java/02-MergeIntervals.java) — Sorts a cloned outer array and copies output rows, preserving the input and avoiding aliases.

**Potential interviewer follow-ups:**

- Should `[1,2]` and `[2,3]` merge? Yes, under this problem's closed-endpoint convention. Half-open ranges need a different equality rule.
- How would repeated online insertions change the design? Maintain disjoint ranges in an ordered map and merge neighboring ranges around each insertion.

## 3. [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** Minimize removals, equivalently maximize the number of compatible intervals retained. Touching endpoints are allowed.

**Solution:** Sort by end and keep each range whose start is at least the last retained end. Otherwise count a removal. Replacing any optimal solution's first interval with the earliest-finishing one leaves at least as much room for its remaining choices; repeat that exchange argument on the suffix.

**Complexity:** O(n log n) time and O(n) auxiliary space for the cloned outer array and object sorting; O(1) scan state.

**Java:** [NonOverlappingIntervals](./java/03-NonOverlappingIntervals.java) — Preserves input; safe comparisons also handle negative endpoints.

**Potential interviewer follow-ups:**

- Why not keep the earliest-starting interval? A long early interval can block many short compatible ones; finishing time controls the remaining opportunity.
- What if intervals have different values or removal costs? Earliest finish alone no longer optimizes value; consider weighted interval scheduling with dynamic programming and predecessor searches.

## 4. [Meeting Rooms](https://leetcode.com/problems/meeting-rooms/) — Easy

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Access:** LeetCode Premium

**Recognize it:** Decide whether one person can attend every meeting, rather than removing meetings or counting rooms.

**Solution:** Sort by start and reject when a meeting starts strictly before the preceding meeting ends. If every adjacent pair is compatible, the entire ordered schedule is compatible. Meetings ending exactly when the next begins cause no conflict.

**Complexity:** O(n log n) time and O(n) auxiliary space for the cloned outer array and object sorting.

**Java:** [MeetingRooms](./java/04-MeetingRooms.java) — LeetCode signature `canAttendMeetings(int[][] intervals)`; preserves input. Empty/single-meeting schedules return true.

**Potential interviewer follow-ups:**

- Can you return a conflicting pair instead of a boolean? Retain original indices while sorting and return the first adjacent conflict.
- What if travel time is needed between meetings? Include the transition time in the compatibility check; use long arithmetic if endpoint sums could overflow.

## 5. [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Access:** LeetCode Premium

**Recognize it:** Every meeting must take place; find the maximum number occurring simultaneously, which is the minimum number of rooms.

**Solution:** Sort by start and maintain a min-heap of active meeting end times. Before adding a meeting, remove every end at or before its start. The heap then contains exactly the meetings overlapping the new one. Record its maximum size after each insertion. That size is both a necessary room count and sufficient because any freed room can be reused.

**Complexity:** O(n log n) time and O(n) auxiliary space for sorting/copying and the heap.

**Java:** [MeetingRoomsII](./java/05-MeetingRoomsII.java) — LeetCode signature `minMeetingRooms(int[][] intervals)`; preserves input and returns 0 for no meetings.

**Potential interviewer follow-ups:**

- Can you solve it without a heap? Sort starts and ends separately and sweep with two pointers; process ends before starts at equal times.
- How would you assign an actual room to every meeting? Store `(end, roomId)` in an occupied-room heap and recycle freed IDs, retaining each meeting's original index.

## 6. [Minimum Interval to Include Each Query](https://leetcode.com/problems/minimum-interval-to-include-each-query/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** Many point queries ask for the smallest covering interval. Queries may be processed offline, then restored to their original order.

**Solution:** Sort intervals by start and query indices by query value. Add intervals whose start is at most the current query to a heap ordered by inclusive length. Remove expired entries from the top until it is valid or the heap is empty. The top is then the smallest covering interval; expired entries deeper in the heap can wait until they reach the top. Increasing queries ensure expired intervals never become valid again.

**Complexity:** O(n log n + q log q) time: each interval enters and leaves the heap at most once, and each query peeks once. O(n + q) auxiliary space, excluding the q returned answers.

**Java:** [MinimumIntervalToIncludeEachQuery](./java/06-MinimumIntervalToIncludeEachQuery.java) — Preserves intervals and queries; writes answers by original query index. Inclusive lengths fit int under the problem's endpoint bounds.

**Potential interviewer follow-ups:**

- Why is lazy removal safe when the heap is ordered by length rather than end? Only the top can determine the answer. If it expires, remove it and repeat; a valid top is no longer than any candidate below it.
- What if queries arrive online and cannot be reordered? The monotonic sweep no longer works directly; consider preprocessing range-minimum updates over compressed coordinates or a different interval-query structure.
