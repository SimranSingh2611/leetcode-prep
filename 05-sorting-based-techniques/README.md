# 05 — Sorting-Based Techniques

For all six questions from the Intervals category together, see [21 — Intervals](../21-intervals/README.md).

Sorting is often the preprocessing step that exposes order, adjacency, or a
greedy choice. Learn direct merging and custom ordering first, then interval
scans, partitioning, multiway merge, and merge-sort counting.

## How to recognize this pattern

**Keywords / hints:** “overlap,” “adjacent after ordering,” “group equal values,” “smallest/largest,” “merge sorted,” or an ordering that simplifies matching.

**Structural test:** Does sorting make the needed relationship local or monotonic, or enable an exchange proof? Check whether original indices/order must be preserved before sorting.

**Invariant / mental model:** Once sorted, earlier keys cannot exceed later keys under the chosen comparator. A scan maintains a correctly merged prefix, a partition boundary, or the best feasible selections so far.

**When not to use it:** Sorting can destroy meaningful original order and costs O(n log n) for comparison sorting. A fixed key range, hash lookup, single-rank selection, or streaming heap may be more appropriate.

**Prerequisites:** 03 two pointers. Some exercises introduce intervals, heaps, or divide-and-conquer; revisit them after 15/16 if needed.

Keywords are clues, not guarantees. Prove the structural condition before choosing a template.

[All-pattern recognition guide](../PATTERN_RECOGNITION.md) · [Full roadmap](../README.md)

**Difficulty mix:** 3 Easy / 5 Medium / 2 Hard

**Java implementations:** [Open the solution index](./java/README.md)

## 1. [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd merge from the back of `nums1`, choosing the larger remaining value.
- That direction uses the reserved space without overwriting values that still need comparison.

**Solution:** Fill `nums1` from the back. Compare the largest unmerged values in
`nums1` and `nums2`, write the larger into the final open position, and move the
corresponding pointer. Working backward prevents unread values in `nums1` from
being overwritten.

**Complexity:**

**Time complexity:** `O(m + n)` for the two populated lengths.

**Space complexity:** `O(1)` auxiliary space; writes into `nums1`.

**Potential interviewer follow-ups:**

- Why is a forward in-place merge unsafe here?
- Generalize the idea to merge `k` sorted arrays.

## 2. [Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd sort both arrays and move two pointers toward matching values.
- On a match, I'd emit the value once and skip duplicates so the result stays unique.

**Solution:** Sort both arrays and advance two pointers. Move the pointer holding
the smaller value; when values match, add the value once and advance both,
skipping duplicates. This is a useful sorting alternative to the more direct
hash-set solution.

**Complexity:**

**Time complexity:** `O(n log(n + 1) + m log(m + 1))` for array lengths `n` and `m`.

**Space complexity:** `O(min(n, m))` temporary/result capacity, plus sorting workspace; `O(n +
m)` is a conservative Java total bound.

**Potential interviewer follow-ups:**

- Compare this with a hash-set solution when one array is much smaller.
- What if duplicate occurrences must be preserved in the result?

## 3. [Relative Sort Array](https://leetcode.com/problems/relative-sort-array/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd count values in `arr1` and emit the ones named by `arr2` in that order.
- Then I'd scan the remaining counts from smallest to largest; the bounded value range makes
  sorting unnecessary.

**Solution:** Count the values in `arr1`. Emit each value in the order specified
by `arr2`, repeating it according to its count, then emit all remaining values in
ascending order. A counting array is best for a small value range; otherwise use
a frequency map plus sorting or a custom comparator.

**Complexity:**

**Time complexity:** `O(n + m + R)` for array lengths `n`, `m` and value range `R = 1001`.

**Space complexity:** `O(R)` auxiliary counters; the primary implementation rewrites `arr1` in
place. With fixed `R`, this is `O(1)`.

**Potential interviewer follow-ups:**

- Implement it using a custom comparator rather than counting.
- How should values absent from `arr2` be ordered under a different tie-breaking rule?

## 4. [Sort Colors](https://leetcode.com/problems/sort-colors/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd maintain separate regions for zeros, ones, unclassified values, and twos.
- After moving a two to the right, I'd inspect the swapped-in value before advancing the middle
  pointer.

**Solution:** Use the Dutch National Flag partition: `[0, low)` contains zeros,
`[low, mid)` ones, and `(high, n)` twos. Swap a zero toward `low`, advance past a
one, and swap a two toward `high` without advancing `mid`, because the incoming
value has not yet been classified.

**Complexity:**

**Time complexity:** `O(n)` for `n` colors.

**Space complexity:** `O(1)` auxiliary space; mutates the input.

**Potential interviewer follow-ups:**

- State and maintain the three-region loop invariant.
- Generalize to many colors or explain why counting sort may then be simpler.

## 5. [Merge Intervals](https://leetcode.com/problems/merge-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd sort by start and extend the last merged interval whenever the next range overlaps.
- A gap finalizes the previous range; touching closed endpoints merge as well.

**Solution:** Sort intervals by start. Keep the last merged interval; if the next
start is no greater than its end, extend the end, otherwise append a new interval.
Sorting ensures that once a gap appears, no later interval can overlap the
current merged interval from the left.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` intervals.

**Space complexity:** `O(n)` Java object-sort workspace and up to `O(n)` output.

**Potential interviewer follow-ups:**

- Should touching intervals such as `[1,2]` and `[2,3]` merge?
- How would you maintain merged intervals under online insertions?

## 6. [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd keep compatible intervals in earliest-finish order.
- An earlier finish leaves at least as much room for future intervals, so counting rejected
  ranges minimizes removals.

**Solution:** Sort by end time and greedily keep the interval that finishes
earliest. Each early finish leaves the most room for later intervals. Count every
interval whose start is before the last kept end as a removal, which is equivalent
to maximizing the number retained.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` intervals.

**Space complexity:** `O(n)` worst-case Java object-sort workspace; the greedy scan uses `O(1)`
state.

**Potential interviewer follow-ups:**

- Prove the earliest-finish greedy choice with an exchange argument.
- What changes if intervals carry different removal costs or values?

## 7. [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) — Medium

**Source:** NeetCode 150

**Talking points:**

- I'd compare squared distances, since square roots do not change their order.
- Sorting by that key gives the first `k` points; quickselect is a useful follow-up when a full
  ordering is unnecessary.

**Solution:** Compute squared distance `x^2 + y^2`; square roots are unnecessary
because they preserve ordering. Sort points by that key and return the first `k`.
This is the clean baseline; a size-`k` heap or quickselect is preferable when
`k` is much smaller than `n`.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for the primary sorting solution.

**Space complexity:** `O(n)` worst-case object-sort workspace, plus `O(k)` output references.

**Potential interviewer follow-ups:**

- Compare full sorting, a size-`k` max-heap, and quickselect.
- How would you process an unbounded stream of points?

## 8. [Hand of Straights](https://leetcode.com/problems/hand-of-straights/) — Medium

**Source:** NeetCode 150

**Talking points:**

- The smallest remaining card must begin a group, so I'd consume consecutive values from it.
- A missing required value proves failure; an ordered frequency map keeps the next group start
  available.

**Solution:** Count every card, process values from smallest upward, and whenever
the smallest remaining value has count `c`, consume `c` copies of each of the
next `groupSize` values. Starting anywhere later would strand the minimum card,
which makes the greedy choice forced. A sorted map can avoid repeatedly scanning
dead keys.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` cards.

**Space complexity:** `O(n)` auxiliary ordered-map space.

**Potential interviewer follow-ups:**

- Why must every group containing the current minimum start at that minimum?
- Implement it with a min-heap and discuss stale entries.

## 9. [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) — Hard

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd recursively divide the list array and merge the two sorted results at each level.
- Each node participates in at most one merge per level, so the list-count logarithm controls
  the work.

**Solution:** Recursively divide the list array into two index ranges, then
merge their returned sorted lists by rewiring existing nodes. Each node
participates in at most one merge per level and there are `log k` levels. The
primary implementation uses an `O(log(k + 1))` divide-and-conquer call stack;
iterative merging rounds and a min-heap of current list heads are alternatives.

**Complexity:**

**Time complexity:** `O(k + N log(k + 1))` for `k` lists and `N` total nodes, including empty-
list traversal.

**Space complexity:** `O(log(k + 1))` recursive stack for the primary solution, reusing input
nodes; the heap alternative uses `O(k)`.

**Potential interviewer follow-ups:**

- Compare divide-and-conquer merging with a `k`-entry min-heap.
- How would you merge input iterators whose total lengths are not known in advance?

## 10. [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) — Hard

**Source:** Supplemental

**Talking points:**

- I'd merge-sort indices while counting right-half values that move ahead of each left-half
  value.
- Original indices let me write every count back in input order; equal values must not count as
  smaller.

**Solution:** Merge-sort `(value, originalIndex)` pairs. During a merge, count
how many right-half elements have moved ahead of each left-half element; add that
number to the left element's answer. Preserve original indices so the counts can
be written back in input order.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` values.

**Space complexity:** `O(n)` auxiliary index/buffer/count storage, plus `O(n)` output.

**Potential interviewer follow-ups:**

- Solve it with coordinate compression and a Fenwick tree.
- How would you change the comparison to count smaller-or-equal values correctly?
