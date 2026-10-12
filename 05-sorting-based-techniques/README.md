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

**Solution:** Fill `nums1` from the back. Compare the largest unmerged values in
`nums1` and `nums2`, write the larger into the final open position, and move the
corresponding pointer. Working backward prevents unread values in `nums1` from
being overwritten.

**Complexity:** `O(m + n)` time and `O(1)` extra space.

**Potential interviewer follow-ups:**

- Why is a forward in-place merge unsafe here?
- Generalize the idea to merge `k` sorted arrays.

## 2. [Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) — Easy

**Source:** Supplemental

**Solution:** Sort both arrays and advance two pointers. Move the pointer holding
the smaller value; when values match, add the value once and advance both,
skipping duplicates. This is a useful sorting alternative to the more direct
hash-set solution.

**Complexity:** `O(n log n + m log m)` time and sorting-dependent auxiliary space.

**Potential interviewer follow-ups:**

- Compare this with a hash-set solution when one array is much smaller.
- What if duplicate occurrences must be preserved in the result?

## 3. [Relative Sort Array](https://leetcode.com/problems/relative-sort-array/) — Easy

**Source:** Supplemental

**Solution:** Count the values in `arr1`. Emit each value in the order specified
by `arr2`, repeating it according to its count, then emit all remaining values in
ascending order. A counting array is best for a small value range; otherwise use
a frequency map plus sorting or a custom comparator.

**Complexity:** `O(n + R)` with counting over range `R`, or `O(n log n)` with
comparison sorting; `O(R)` or `O(k)` space.

**Potential interviewer follow-ups:**

- Implement it using a custom comparator rather than counting.
- How should values absent from `arr2` be ordered under a different tie-breaking rule?

## 4. [Sort Colors](https://leetcode.com/problems/sort-colors/) — Medium

**Source:** Supplemental

**Solution:** Use the Dutch National Flag partition: `[0, low)` contains zeros,
`[low, mid)` ones, and `(high, n)` twos. Swap a zero toward `low`, advance past a
one, and swap a two toward `high` without advancing `mid`, because the incoming
value has not yet been classified.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- State and maintain the three-region loop invariant.
- Generalize to many colors or explain why counting sort may then be simpler.

## 5. [Merge Intervals](https://leetcode.com/problems/merge-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Sort intervals by start. Keep the last merged interval; if the next
start is no greater than its end, extend the end, otherwise append a new interval.
Sorting ensures that once a gap appears, no later interval can overlap the
current merged interval from the left.

**Complexity:** `O(n log n)` time and `O(n)` output space.

**Potential interviewer follow-ups:**

- Should touching intervals such as `[1,2]` and `[2,3]` merge?
- How would you maintain merged intervals under online insertions?

## 6. [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Sort by end time and greedily keep the interval that finishes
earliest. Each early finish leaves the most room for later intervals. Count every
interval whose start is before the last kept end as a removal, which is equivalent
to maximizing the number retained.

**Complexity:** `O(n log n)` time and sorting-dependent auxiliary space.

**Potential interviewer follow-ups:**

- Prove the earliest-finish greedy choice with an exchange argument.
- What changes if intervals carry different removal costs or values?

## 7. [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) — Medium

**Source:** NeetCode 150

**Solution:** Compute squared distance `x^2 + y^2`; square roots are unnecessary
because they preserve ordering. Sort points by that key and return the first `k`.
This is the clean baseline; a size-`k` heap or quickselect is preferable when
`k` is much smaller than `n`.

**Complexity:** `O(n log n)` time and sorting-dependent auxiliary space.

**Potential interviewer follow-ups:**

- Compare full sorting, a size-`k` max-heap, and quickselect.
- How would you process an unbounded stream of points?

## 8. [Hand of Straights](https://leetcode.com/problems/hand-of-straights/) — Medium

**Source:** NeetCode 150

**Solution:** Count every card, process values from smallest upward, and whenever
the smallest remaining value has count `c`, consume `c` copies of each of the
next `groupSize` values. Starting anywhere later would strand the minimum card,
which makes the greedy choice forced. A sorted map can avoid repeatedly scanning
dead keys.

**Complexity:** `O(n log n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- Why must every group containing the current minimum start at that minimum?
- Implement it with a min-heap and discuss stale entries.

## 9. [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) — Hard

**Source:** Blind 75 + NeetCode 150

**Solution:** Perform pairwise divide-and-conquer merging: merge adjacent list
pairs, then merge the results in rounds until one list remains. Each node
participates in one merge per level and there are `log k` levels. A min-heap of
the current list heads is the standard online alternative.

**Complexity:** `O(N log k)` time for `N` total nodes, plus `O(k)` temporary list
references for iterative rounds (or `O(log k)` call-stack space for recursive
divide and conquer), excluding the returned links.

**Potential interviewer follow-ups:**

- Compare divide-and-conquer merging with a `k`-entry min-heap.
- How would you merge input iterators whose total lengths are not known in advance?

## 10. [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) — Hard

**Source:** Supplemental

**Solution:** Merge-sort `(value, originalIndex)` pairs. During a merge, count
how many right-half elements have moved ahead of each left-half element; add that
number to the left element's answer. Preserve original indices so the counts can
be written back in input order.

**Complexity:** `O(n log n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- Solve it with coordinate compression and a Fenwick tree.
- How would you change the comparison to count smaller-or-equal values correctly?
