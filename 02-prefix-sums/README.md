# 02 — Prefix Sums

A prefix sum stores an aggregate for everything before or through an index.
It turns a repeated range calculation into subtraction, and combining it with a
hash map lets you detect earlier prefixes that make the current range valid.

## How to recognize this pattern

**Keywords / hints:** “range sum,” “many interval queries,” “subarray sum equals k,” “equal counts,” or left/right aggregate contributions.

**Structural test:** Can the aggregate of [l,r] be recovered from prefix states, or can a subarray condition be rewritten as a relation between two prefixes? For sums, P[r+1]-P[l] gives the range total.

**Invariant / mental model:** P[i] describes the first i elements and P[0]=0. In prefix-frequency counting, query earlier prefix states before adding the current one. Product of Array Except Self is the related prefix/suffix aggregate variant, not a subtraction-of-sums problem.

**When not to use it:** A plain prefix array is not efficient for frequent point updates. “Contiguous” alone does not imply prefix sums, and operations such as min cannot generally be undone by subtracting prefixes.

**Prerequisites:** 01 maps for counting repeated prefix states. Revisit the Hard monotonic-deque and ordered-counting hybrids after their supporting topics.

Keywords are clues, not guarantees. Prove the structural condition before choosing a template.

[All-pattern recognition guide](../PATTERN_RECOGNITION.md) · [Full roadmap](../README.md)

**Difficulty mix:** 3 Easy / 5 Medium / 2 Hard

**Java implementations:** [Open the solution index](./java/README.md)

## 1. [Running Sum of 1D Array](https://leetcode.com/problems/running-sum-of-1d-array/) — Easy

**Source:** Supplemental

**Solution:** Traverse left to right and add each value to the accumulated sum.
Store that total at the current index or in a separate result array. This is the
basic recurrence behind every prefix-sum problem: `prefix[i] = prefix[i-1] + nums[i]`.

**Complexity:** `O(n)` time and `O(1)` extra space when updating the input.

**Potential interviewer follow-ups:**

- Preserve the original array while answering many prefix queries.
- How would you extend the idea to a matrix?

## 2. [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) — Easy

**Source:** Supplemental

**Solution:** Compute the total sum, then scan while maintaining the sum strictly
to the left. At index `i`, the right sum is `total - left - nums[i]`. Return the
first index where the two sides match, then add the current value to `left` only
after checking it.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Return every pivot rather than only the leftmost one.
- Does the approach still work with negative values and integer overflow concerns?

## 3. [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) — Easy

**Source:** Supplemental

**Solution:** Build a prefix array with a leading zero, where `prefix[i + 1]`
contains the sum through `nums[i]`. An inclusive query `[left, right]` is then
`prefix[right + 1] - prefix[left]`. The leading zero removes special handling
for ranges beginning at index zero.

**Complexity:** `O(n)` construction, `O(1)` per query, and `O(n)` space.

**Potential interviewer follow-ups:**

- What data structure would you use if point updates were allowed?
- How would you support range updates as well as range queries?

## 4. [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** In a forward pass, place the product of everything to the left of
`i` into the result. In a backward pass, multiply it by a running suffix product
containing everything to the right. This naturally handles zeros and never uses
division.

**Complexity:** `O(n)` time and `O(1)` auxiliary space excluding the output.

**Potential interviewer follow-ups:**

- Explain how the method behaves with one zero or multiple zeros.
- If division were allowed, what edge cases would that alternative need to handle?

## 5. [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) — Medium

**Source:** Supplemental

**Solution:** Maintain the current prefix sum and a frequency map of all earlier
prefix sums, initially `{0: 1}`. A subarray ending here sums to `k` for every
earlier prefix equal to `current - k`, so add that frequency before recording
the current prefix. Frequencies—not mere membership—are required to count all
subarrays.

**Complexity:** `O(n)` expected time and `O(n)` space.

**Potential interviewer follow-ups:**

- Why does a standard shrinking window fail when negative numbers are allowed?
- Return the indices of one matching subarray instead of the total count.

## 6. [Contiguous Array](https://leetcode.com/problems/contiguous-array/) — Medium

**Source:** Supplemental

**Solution:** Treat every `0` as `-1`, so a range containing equal zeros and ones
has sum zero. Store the earliest index at which each running balance occurred.
When the same balance appears again, the values between the two positions cancel;
using the earliest position maximizes the length.

**Complexity:** `O(n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- Why must the map store the first occurrence rather than the latest?
- How would you adapt the idea to equal counts of three different values?

## 7. [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) — Medium

**Source:** Supplemental

**Solution:** Prefix sums with the same remainder modulo `k` differ by a multiple
of `k`. Map each remainder to its earliest index, seeding remainder zero at index
`-1`, and accept a repeat only when the index gap is at least two. Keeping the
earliest occurrence provides the greatest chance of meeting the length rule.

**Complexity:** `O(n)` time and `O(min(n, |k|))` space in the usual nonzero-`k` case.

**Potential interviewer follow-ups:**

- How would you make the implementation robust if `k` could be zero or negative?
- Return the subarray indices rather than a boolean.

## 8. [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) — Medium

**Source:** Supplemental

**Solution:** Build a padded 2D prefix table. The sum of a rectangle is its
bottom-right prefix minus the areas above and left, plus the top-left overlap
that was subtracted twice. Padding the table with row zero and column zero keeps
the inclusion-exclusion formula uniform at boundaries.

**Complexity:** `O(mn)` construction, `O(1)` per query, and `O(mn)` space.

**Potential interviewer follow-ups:**

- Derive the four-term inclusion-exclusion formula on a whiteboard.
- What would you use if individual cells could be updated between queries?

## 9. [Count of Range Sum](https://leetcode.com/problems/count-of-range-sum/) — Hard

**Source:** Supplemental

**Solution:** Form prefix sums, so every range becomes a difference of two
prefixes. During merge sort, for each prefix in the left half, advance two
pointers over the sorted right half to count differences in `[lower, upper]`.
Then merge the halves. This counts valid cross-half pairs without enumerating
all pairs.

**Complexity:** `O(n log n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- Solve it online with coordinate compression and a Fenwick tree.
- Explain why counting must happen before merging the two sorted halves.

## 10. [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) — Hard

**Source:** Supplemental

**Solution:** Compute prefix sums and maintain indices in a deque whose prefix
values are strictly increasing. Pop from the front while the current prefix minus
the oldest prefix reaches `k`, updating the shortest length; pop from the back
while the current prefix is no larger, because the current index dominates that
older candidate. This works even when values are negative.

**Complexity:** `O(n)` time and `O(n)` space; each index enters and leaves the
deque at most once.

**Potential interviewer follow-ups:**

- Why can an ordinary variable-size sliding window not handle this input?
- Prove why a larger, older prefix at the deque's back is permanently dominated.
