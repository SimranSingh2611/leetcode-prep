# 21 — Intervals

Ten problems: **1 Easy / 6 Medium / 3 Hard**, numbered from Easy to Medium to Hard. Within each difficulty band, the order is a study recommendation: basic scans and merging come before greedy selection, heaps, and calendar booking. Learn to distinguish merging ranges, selecting compatible ranges, counting simultaneous meetings, answering coverage queries, maintaining a calendar as bookings arrive, and assigning rooms to delayed meetings.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## How to recognize this pattern

**Keywords / hints:** “overlap,” “insert a range,” “merge intervals,” “meeting rooms,” “minimum removals,” “smallest interval covering a query,” “double/triple booking,” or “delay until a room is free.”

**Structural test:** Does sorting by start or end make overlaps local, allow an earliest-finish choice, or let a sweep maintain the relevant intervals as time/query values increase? If bookings arrive one at a time, can an ordered map retain the bookings or their endpoint changes between calls?

**Invariant / mental model:** Track the end of the current merged range, the end of the last selected range, a heap of meeting ends/eligible query candidates, or an ordered map of booking endpoints. An endpoint-delta prefix sum measures current concurrency. For room assignment, retain both free room IDs and busy room finishing times. Choose the state according to what the answer measures.

**When not to use it:** Do not merge ranges when the answer needs the number of concurrent meetings; merging loses that multiplicity. Earliest-finish selection assumes equal interval value. Weighted scheduling generally needs dynamic programming. Scanning all endpoints after every booking takes quadratic total time; larger workloads may need an augmented tree or dynamic segment tree. A concurrency counter alone cannot simulate Meeting Rooms III's room choices and delays.

**Prerequisites:** [05 sorting](../05-sorting-based-techniques/README.md), [15 heaps](../15-heaps-and-priority-queues/README.md), and [16 greedy reasoning](../16-greedy-and-intervals/README.md). The calendar series also uses [02 prefix sums](../02-prefix-sums/README.md) and Java's ordered `TreeMap`; segment trees are an optional follow-up. Minimum Interval is the heap/sweep stretch exercise.

## Endpoint rules

| Problem | When endpoints are equal |
| --- | --- |
| Insert Interval / Merge Intervals | Closed ranges touch and **must merge**: next start `<=` current end. |
| Non-overlapping Intervals | Touching ranges are compatible: next start `>=` last selected end. |
| Meeting Rooms / Meeting Rooms II / Meeting Rooms III | A meeting ending at time `t` frees its room for one starting at `t`. In III, free every room ending by the original start before choosing the lowest available room ID. |
| Minimum Interval to Include Each Query | Both endpoints count; length is `end - start + 1`. Expire only when `end < query`. |
| My Calendar I / II / III | Bookings use `[start, end)`; a booking ending at `t` does not overlap one starting at `t`. Combine changes at the same time before measuring concurrency. |

## Existing coverage

Insert Interval and Minimum Interval also appear in [16](../16-greedy-and-intervals/README.md); Merge Intervals and Non-overlapping Intervals also appear in [05](../05-sorting-based-techniques/README.md). These four repeats deliberately make this section self-contained. Meeting Rooms and Meeting Rooms II are new to the roadmap. If you already solved an overlapping question, use this section to review its endpoint rule and follow-ups.

Six core selections come from your screenshot of the [NeetCode 150](https://neetcode.io/practice/practice/neetcode150) Intervals category; the numbered exercises now follow difficulty order. On **2026-10-09**, the four free problem statements and both meeting-room Premium gates were checked at the linked LeetCode pages. The meeting-room difficulty labels follow the screenshot; their statements are subscription-gated. Existing Blind 75 labels are retained for the earlier selections. The three calendar problems are user-requested supplemental practice; their linked free statements, difficulty, class names, and booking rules were checked on **2026-10-17**.

Meeting Rooms III is also user-requested supplemental practice. Its linked free statement, Hard difficulty, scheduling rules, and `mostBooked` signature were checked on **2026-10-17**. It follows the other Hard exercises as a two-heap simulation follow-up to Meeting Rooms II.

## Calendar progression: ordered maps and sweep line

Start the calendar series after Meeting Rooms II. These are **online** design problems: each `book` call must return before the next booking is known. My Calendar I uses an ordered map of disjoint accepted bookings, so only the neighboring intervals need checking. My Calendar II and III allow overlaps and instead retain endpoint changes:

```text
changes[start] += 1
changes[end]   -= 1
Sweep times in order: active += changes[time]
The maximum active count is maximum concurrency.
```

This is a prefix sum over ordered endpoint changes, rather than over every possible timestamp. A `TreeMap` handles sparse coordinates up to `10^9` without a huge array. Combining changes at equal timestamps implements the half-open endpoint rule.

For Calendar II, tentatively add the booking and undo both changes if the maximum exceeds two. For Calendar III, accept every booking and return the maximum. Remove zero-valued changes so they do not occupy map entries. The same sweep could implement Calendar I with a limit of one, but checking disjoint neighbors gives a faster `O(log(n + 1))` booking operation there.

## 1. [Meeting Rooms](https://leetcode.com/problems/meeting-rooms/) — Easy

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Access:** LeetCode Premium

**Recognize it:** Decide whether one person can attend every meeting, rather than removing meetings or counting rooms.

**Talking points:**

- I'd sort meetings by start and check every adjacent pair for a conflict.
- A start strictly before the preceding end fails; meetings that touch at endpoints can both be
  attended.

**Solution:** Sort by start and reject when a meeting starts strictly before the preceding meeting ends. If every adjacent pair is compatible, the entire ordered schedule is compatible. Meetings ending exactly when the next begins cause no conflict.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` meetings.

**Space complexity:** `O(n)` auxiliary cloned-array/object-sort storage.

**Java:** [MeetingRooms](./java/01-MeetingRooms.java) — LeetCode signature `canAttendMeetings(int[][] intervals)`; preserves input. Empty/single-meeting schedules return true.

**Potential interviewer follow-ups:**

- Can you return a conflicting pair instead of a boolean? Retain original indices while sorting and return the first adjacent conflict.
- What if travel time is needed between meetings? Include the transition time in the compatibility check; use long arithmetic if endpoint sums could overflow.

## 2. [Merge Intervals](https://leetcode.com/problems/merge-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** Unordered ranges must become a disjoint union; preserve coverage rather than the original range identities.

**Talking points:**

- I'd sort by start and merge into the last output range while the next range overlaps.
- A gap finalizes the previous range; taking the maximum end also handles nested intervals.

**Solution:** Sort by start. If the next start is no greater than the last merged end, extend that end; otherwise start a new output range. Once a gap appears, sorted future starts cannot fill it, so the previous merged range is final. Nested ranges require taking the maximum end, not simply assigning the next end.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` intervals.

**Space complexity:** `O(n)` auxiliary outer-array copy/object-sort workspace, plus `O(n)`
output.

**Java:** [MergeIntervals](./java/02-MergeIntervals.java) — Sorts a cloned outer array and copies output rows, preserving the input and avoiding aliases.

**Potential interviewer follow-ups:**

- Should `[1,2]` and `[2,3]` merge? Yes, under this problem's closed-endpoint convention. Half-open ranges need a different equality rule.
- How would repeated online insertions change the design? Maintain disjoint ranges in an ordered map and merge neighboring ranges around each insertion.

## 3. [Insert Interval](https://leetcode.com/problems/insert-interval/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** One new closed interval is inserted into ranges already sorted by start and non-overlapping. Its overlaps form a contiguous block.

**Talking points:**

- I'd copy the prefix before the new interval, merge its overlap block, then copy the suffix.
- The existing sorted disjoint ranges make this one pass; touching closed endpoints must merge.

**Solution:** Copy the prefix whose ends are strictly before the new start. Expand the new interval while the next start is at most its evolving end, emit the merged range, then copy the suffix. The sorted/disjoint guarantee makes each completed prefix range final; no sorting is needed.

**Complexity:**

**Time complexity:** `O(n)` for `n` existing intervals.

**Space complexity:** `O(n)` result/buffer storage, with `O(1)` scan state beyond it.

**Java:** [InsertInterval](./java/03-InsertInterval.java) — Preserves both inputs and returns independent interval rows.

**Potential interviewer follow-ups:**

- What if the existing intervals are unsorted or overlapping? Append the new range, sort by start, and run Merge Intervals; the one-pass insertion guarantee disappears.
- Does finding the insertion point with binary search make the whole operation O(log n)? No; producing a new array still takes O(n) in the worst case.

## 4. [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** Minimize removals, equivalently maximize the number of compatible intervals retained. Touching endpoints are allowed.

**Talking points:**

- I'd sort by end and keep each interval compatible with the last one retained.
- Earliest finish leaves the most room for later choices; touching endpoints are compatible for
  this question.

**Solution:** Sort by end and keep each range whose start is at least the last retained end. Otherwise count a removal. Replacing any optimal solution's first interval with the earliest-finishing one leaves at least as much room for its remaining choices; repeat that exchange argument on the suffix.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` intervals.

**Space complexity:** `O(n)` auxiliary cloned-array/object-sort storage; the scan uses `O(1)`
state.

**Java:** [NonOverlappingIntervals](./java/04-NonOverlappingIntervals.java) — Preserves input; safe comparisons also handle negative endpoints.

**Potential interviewer follow-ups:**

- Why not keep the earliest-starting interval? A long early interval can block many short compatible ones; finishing time controls the remaining opportunity.
- What if intervals have different values or removal costs? Earliest finish alone no longer optimizes value; consider weighted interval scheduling with dynamic programming and predecessor searches.

## 5. [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Access:** LeetCode Premium

**Recognize it:** Every meeting must take place; find the maximum number occurring simultaneously, which is the minimum number of rooms.

**Talking points:**

- I'd sort meetings by start and heap the end times of active meetings.
- Removing every meeting ending by the next start makes heap size equal current overlap; its
  maximum is the minimum room count.

**Solution:** Sort by start and maintain a min-heap of active meeting end times. Before adding a meeting, remove every end at or before its start. The heap then contains exactly the meetings overlapping the new one. Record its maximum size after each insertion. That size is both a necessary room count and sufficient because any freed room can be reused.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` meetings.

**Space complexity:** `O(n)` auxiliary sorted-copy/heap storage.

**Java:** [MeetingRoomsII](./java/05-MeetingRoomsII.java) — LeetCode signature `minMeetingRooms(int[][] intervals)`; preserves input and returns 0 for no meetings.

**Potential interviewer follow-ups:**

- Can you solve it without a heap? Sort starts and ends separately and sweep with two pointers; process ends before starts at equal times.
- How would you assign an actual room to every meeting? Store `(end, roomId)` in an occupied-room heap and recycle freed IDs, retaining each meeting's original index.

## 6. [My Calendar I](https://leetcode.com/problems/my-calendar-i/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Access:** Free problem

**Recognize it:** Bookings arrive one at a time; accept a half-open interval only if it does not overlap any accepted booking. Implement the stateful `MyCalendar` class.

**Talking points:**

- I'd store accepted bookings in a `TreeMap` from start to end and check the new booking's predecessor and successor.
- Accepted bookings remain disjoint, so those two neighbors are sufficient; a rejected booking never changes the map.

**Solution:** Look up `floorEntry(startTime)` and reject if its end is greater than the new start. Look up `ceilingEntry(startTime)` and reject if its start is less than the new end. Otherwise insert the booking. Every earlier booking ends no later than the predecessor, and every later booking starts no earlier than the successor, because accepted bookings are disjoint. Equal endpoints are compatible; duplicate or contained bookings conflict.

**Complexity:**

**Time complexity:** `O(log(n + 1))` per `book` call for `n` accepted bookings so far.

**Space complexity:** `O(n)` persistent booking storage; `O(1)` auxiliary space per call.

**Java:** [MyCalendarI](./java/06-MyCalendarI.java) — Judge class `MyCalendar`; constructor `MyCalendar()` and method `boolean book(int startTime, int endTime)`.

**Potential interviewer follow-ups:**

- Could the same sweep as Calendar II solve this? Yes: tentatively add endpoint deltas, reject and undo if any prefix exceeds one. It takes `O(n)` per call instead of neighbor checking's logarithmic bound.
- Why do only two neighbors suffice here but not when bookings may overlap? Disjoint accepted intervals have ends ordered consistently with their starts; with overlapping intervals, an earlier long interval can extend past a nearer predecessor.

## 7. [My Calendar II](https://leetcode.com/problems/my-calendar-ii/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Access:** Free problem

**Recognize it:** Double bookings are allowed, but reject any booking that would make three events overlap at the same time. Implement `MyCalendarTwo`; rejected bookings must leave the calendar unchanged.

**Talking points:**

- I'd store `+1` at every booking start and `-1` at its end in a `TreeMap`, then sweep the changes to count active bookings.
- A prefix sum above two means a triple booking; undo both tentative changes and remove zero-valued entries before rejecting it.

**Solution:** Add the new booking's endpoint changes and scan the map's values in time order. Accumulate the current count and its maximum. If the maximum is above two, apply the inverse changes and return `false`; otherwise keep them and return `true`. At each timestamp the prefix sum equals the number of accepted bookings active on the following segment, including the tentative booking during validation. Aggregating equal-time changes allows touching intervals. The inverse updates restore all previous counts, even when endpoints coincide or previously canceled to zero.

**Complexity:**

**Time complexity:** `O(n)` per `book` call and `O(n^2)` over `n` calls. The two map updates and any rollback cost `O(log(n + 1))` each; scanning at most `2n` endpoints dominates. This fits the statement's 1,000-call limit.

**Space complexity:** `O(n)` persistent endpoint storage; `O(1)` auxiliary space per call. Removing zero changes prevents rejected bookings from accumulating unused timestamps.

**Java:** [MyCalendarII](./java/07-MyCalendarII.java) — Judge class `MyCalendarTwo`; constructor `MyCalendarTwo()` and method `boolean book(int startTime, int endTime)`.

**Potential interviewer follow-ups:**

- Can you avoid sweeping endpoint changes? Keep accepted bookings and their pairwise overlap regions. Reject if the new booking intersects a double-booked region; otherwise record its new overlaps and accept it.
- What changes for capacity `k` or much larger call counts? Replace the rejection threshold with `k`. For faster updates, use an augmented ordered tree storing subtree sums/maximum prefixes, or a dynamic segment tree with range increments and a maximum.

## 8. [Minimum Interval to Include Each Query](https://leetcode.com/problems/minimum-interval-to-include-each-query/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Access:** Free problem

**Recognize it:** Many point queries ask for the smallest covering interval. Queries may be processed offline, then restored to their original order.

**Talking points:**

- I'd sweep sorted queries while adding intervals that have started to a min-heap ordered by
  inclusive length.
- After discarding expired tops, the root is the shortest covering interval; saved query indices
  restore the original answer order.

**Solution:** Sort intervals by start and query indices by query value. Add intervals whose start is at most the current query to a heap ordered by inclusive length. Remove expired entries from the top until it is valid or the heap is empty. The top is then the smallest covering interval; expired entries deeper in the heap can wait until they reach the top. Increasing queries ensure expired intervals never become valid again.

**Complexity:**

**Time complexity:** `O(n log(n + 1) + q log(q + 1))` for `n` intervals and `q` queries; each
interval enters/leaves the heap at most once.

**Space complexity:** `O(n + q)` auxiliary copied-array/query-index/heap storage, plus `O(q)`
output.

**Java:** [MinimumIntervalToIncludeEachQuery](./java/08-MinimumIntervalToIncludeEachQuery.java) — Preserves intervals and queries; writes answers by original query index. Inclusive lengths fit int under the problem's endpoint bounds.

**Potential interviewer follow-ups:**

- Why is lazy removal safe when the heap is ordered by length rather than end? Only the top can determine the answer. If it expires, remove it and repeat; a valid top is no longer than any candidate below it.
- What if queries arrive online and cannot be reordered? The monotonic sweep no longer works directly; consider preprocessing range-minimum updates over compressed coordinates or a different interval-query structure.

## 9. [My Calendar III](https://leetcode.com/problems/my-calendar-iii/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Access:** Free problem

**Recognize it:** Accept every booking and return the largest number of simultaneously active events after each call. Implement the stateful `MyCalendarThree` class.

**Talking points:**

- I'd retain ordered endpoint changes and sweep them after each booking, just as in Calendar II.
- The maximum prefix sum is the answer; every booking is accepted, so no capacity check or rollback is needed.

**Solution:** Add `+1` at the start and `-1` at the end, removing entries whose net change becomes zero. Sweep the full map to compute the maximum running sum and return it. The map represents all bookings so far, so the answer includes earlier peaks even if the latest booking occurs elsewhere. Equal-time deltas are combined to honor `[start, end)`. The 400-call limit makes this straightforward sweep sufficient without requiring a segment tree.

**Complexity:**

**Time complexity:** `O(n)` per `book` call and `O(n^2)` over `n` calls; each map update costs `O(log(n + 1))`, and the sweep visits at most `2n` endpoints.

**Space complexity:** `O(n)` persistent endpoint storage; `O(1)` auxiliary space per call.

**Java:** [MyCalendarIII](./java/09-MyCalendarIII.java) — Judge class `MyCalendarThree`; constructor `MyCalendarThree()` and method `int book(int startTime, int endTime)`.

**Potential interviewer follow-ups:**

- How would a dynamic segment tree improve booking time? Increment coverage over `[start, end)` and retain the global maximum with lazy propagation. Creating nodes only along visited paths supports sparse coordinates with `O(log U)` update time for coordinate universe size `U`.
- Can endpoints be compressed once before processing? Yes if all bookings are known in advance. In the online API future endpoints are unknown; an ordered map or a dynamic tree accommodates new coordinates as calls arrive.

## 10. [Meeting Rooms III](https://leetcode.com/problems/meeting-rooms-iii/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Access:** Free problem

**Recognize it:** A fixed set of numbered rooms must host every meeting. Prefer the lowest available room ID; if every room is busy, delay the meeting without changing its duration. Return the room hosting the most meetings, breaking usage ties by lowest ID.

**Talking points:**

- I'd process meetings by original start time, using one min-heap of free room IDs and another of busy rooms ordered by finish time, then room ID.
- Free all rooms finishing by the current start. If none are free, use the earliest-finishing room and delay the meeting; accumulated finishing times need `long`.

**Solution:** Sort a cloned outer meeting array by start. Heapify all room IDs into the available-room heap and initialize a usage counter per room. Before assigning each meeting, move every busy room with finish time at most the original start into the available heap. If a room is free, take the lowest ID and start on time. Otherwise pop the busy room with earliest finish, breaking ties by ID, and begin the meeting at that finish time. Add the original duration to the chosen start, push the updated busy entry, and increment that room's count. Processing original starts in order preserves the priority of earlier waiting meetings; a busy entry reserves its room through all meetings already assigned to it. Finally scan IDs in ascending order, replacing the answer only for a strictly larger usage count.

**Complexity:**

**Time complexity:** `O(m log(m + 1) + m log(n + 1) + n)` for `m` meetings and `n` rooms. Sorting costs `O(m log(m + 1))`; room-ID initialization/heapification and the final count scan take `O(n)`. Each meeting enters and leaves the busy heap at most once, and each heap has at most `n` rooms.

**Space complexity:** `O(m + n)` auxiliary storage for the outer-array copy/object-sort workspace, room IDs, heaps, and usage counts. The scalar answer adds `O(1)` output storage. Neither the input order nor its rows are modified.

**Java:** [MeetingRoomsIII](./java/10-MeetingRoomsIII.java) — Judge class `Solution`; method `int mostBooked(int n, int[][] meetings)`. Uses `long` finishing times because repeated delays can exceed the original timestamp bounds and the `int` range.

**Potential interviewer follow-ups:**

- Why are there two heaps? Free rooms are selected by ID, while busy rooms are selected by finish time and then ID. All rooms finishing by a meeting's original start must be freed before choosing among available IDs.
- Could a direct scan replace the heaps? Yes: retain each room's next free time, scan IDs for a room free by the original start, or choose the earliest free time with an ID tie-break. This takes `O(mn)` scheduling time plus sorting and is useful as a reference implementation.
