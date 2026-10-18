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

**Talking points:**

- I'd carry a running total from left to right.
- Writing it back into each position builds the prefix sums in place.

**Solution:** Traverse left to right and add each value to the accumulated sum.
Store that total at the current index or in a separate result array. This is the
basic recurrence behind every prefix-sum problem: `prefix[i] = prefix[i-1] + nums[i]`.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space; the input becomes the result.

**Potential interviewer follow-ups:**

- Preserve the original array while answering many prefix queries.
- How would you extend the idea to a matrix?

## 2. [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd compute the total once, then track the sum strictly to the left.
- At each index the right sum is `total - left - current`; I'd check before adding the current
  value to the left sum.

**Solution:** Compute the total sum, then scan while maintaining the sum strictly
to the left. At index `i`, the right sum is `total - left - nums[i]`. Return the
first index where the two sides match, then add the current value to `left` only
after checking it.

**Complexity:**

**Time complexity:** `O(n)` for two scans.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return every pivot rather than only the leftmost one.
- Does the approach still work with negative values and integer overflow concerns?

## 3. [Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd build a prefix array with a leading zero.
- Then an inclusive range sum is `prefix[right + 1] - prefix[left]`, including ranges starting
  at zero.

**Solution:** Build a prefix array with a leading zero, where `prefix[i + 1]`
contains the sum through `nums[i]`. An inclusive query `[left, right]` is then
`prefix[right + 1] - prefix[left]`. The leading zero removes special handling
for ranges beginning at index zero.

**Complexity:**

**Time complexity:** Construction: `O(n)`; each `sumRange`: `O(1)`.

**Space complexity:** `O(n)` persistent prefix storage; `O(1)` additional space per query.

**Potential interviewer follow-ups:**

- What data structure would you use if point updates were allowed?
- How would you support range updates as well as range queries?

## 4. [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/products-of-array-discluding-self/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd store the product to the left of each position in the result.
- A backward pass multiplies in the suffix product, handling zeros without division.

**Solution:** In a forward pass, place the product of everything to the left of
`i` into the result. In a backward pass, multiply it by a running suffix product
containing everything to the right. This naturally handles zeros and never uses
division.

**Complexity:**

**Time complexity:** `O(n)` for two passes.

**Space complexity:** `O(1)` auxiliary space, plus `O(n)` output.

**Potential interviewer follow-ups:**

- Explain how the method behaves with one zero or multiple zeros.
- If division were allowed, what edge cases would that alternative need to handle?

## 5. [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd count earlier prefix sums in a map seeded with zero once.
- For each current sum, the frequency of `current - k` counts all matching subarrays ending
  here; I'd query before inserting.

**Solution:** Maintain the current prefix sum and a frequency map of all earlier
prefix sums, initially `{0: 1}`. A subarray ending here sums to `k` for every
earlier prefix equal to `current - k`, so add that frequency before recording
the current prefix. Frequencies—not mere membership—are required to count all
subarrays.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` values.

**Space complexity:** `O(n)` auxiliary prefix-frequency space.

**Potential interviewer follow-ups:**

- Why does a standard shrinking window fail when negative numbers are allowed?
- Return the indices of one matching subarray instead of the total count.

## 6. [Contiguous Array](https://leetcode.com/problems/contiguous-array/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd treat zeros as minus one so equal zeros and ones become a zero-sum range.
- Repeated running balances identify valid ranges; keeping the earliest index gives the longest
  one.

**Solution:** Treat every `0` as `-1`, so a range containing equal zeros and ones
has sum zero. Store the earliest index at which each running balance occurred.
When the same balance appears again, the values between the two positions cancel;
using the earliest position maximizes the length.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` values.

**Space complexity:** `O(n)` auxiliary earliest-index map space.

**Potential interviewer follow-ups:**

- Why must the map store the first occurrence rather than the latest?
- How would you adapt the idea to equal counts of three different values?

## 7. [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd track the earliest index of each prefix remainder modulo `k`.
- Equal remainders identify a multiple of `k`; I'd also require the index gap to be at least
  two.

**Solution:** Prefix sums with the same remainder modulo `k` differ by a multiple
of `k`. Map each remainder to its earliest index, seeding remainder zero at index
`-1`, and accept a repeat only when the index gap is at least two. Keeping the
earliest occurrence provides the greatest chance of meeting the length rule.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` values.

**Space complexity:** `O(min(n + 1, k))` auxiliary space for positive `k`; `O(n)` if extending
the implementation to `k = 0`.

**Potential interviewer follow-ups:**

- How would you make the implementation robust if `k` could be zero or negative?
- Return the subarray indices rather than a boolean.

## 8. [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd build a padded 2D prefix-sum table.
- Each rectangle query subtracts the strips above and left, then adds back their double-
  subtracted overlap.

**Solution:** Build a padded 2D prefix table. The sum of a rectangle is its
bottom-right prefix minus the areas above and left, plus the top-left overlap
that was subtracted twice. Padding the table with row zero and column zero keeps
the inclusion-exclusion formula uniform at boundaries.

**Complexity:**

**Time complexity:** Construction: `O(RC)` for `R` rows and `C` columns; each query: `O(1)`.

**Space complexity:** `O(RC)` persistent table space; `O(1)` additional query space.

**Potential interviewer follow-ups:**

- Derive the four-term inclusion-exclusion formula on a whiteboard.
- What would you use if individual cells could be updated between queries?

## 9. [Count of Range Sum](https://leetcode.com/problems/count-of-range-sum/) — Hard

**Source:** Supplemental

**Talking points:**

- I'd turn each range sum into a difference of prefix sums.
- While merging sorted halves, two monotonic pointers count the right-half prefixes whose
  differences lie within the requested bounds.

**Solution:** Form prefix sums, so every range becomes a difference of two
prefixes. During merge sort, for each prefix in the left half, advance two
pointers over the sorted right half to count differences in `[lower, upper]`.
Then merge the halves. This counts valid cross-half pairs without enumerating
all pairs.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for merge-sort counting.

**Space complexity:** `O(n)` auxiliary prefix/merge arrays, including an `O(log(n + 1))`
recursion stack.

**Potential interviewer follow-ups:**

- Solve it online with coordinate compression and a Fenwick tree.
- Explain why counting must happen before merging the two sorted halves.

## 10. [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) — Hard

**Source:** Supplemental

**Talking points:**

- I'd keep candidate prefix indices in increasing prefix-sum order in a deque.
- The front produces valid shortest ranges, while a newer smaller prefix dominates an older
  larger one; this also handles negative values.

**Solution:** Compute prefix sums and maintain indices in a deque whose prefix
values are strictly increasing. Pop from the front while the current prefix minus
the oldest prefix reaches `k`, updating the shortest length; pop from the back
while the current prefix is no larger, because the current index dominates that
older candidate. This works even when values are negative.

**Complexity:**

**Time complexity:** `O(n)` amortized overall; each index enters and leaves the deque at most
once.

**Space complexity:** `O(n)` auxiliary prefix-array and deque space.

**Potential interviewer follow-ups:**

- Why can an ordinary variable-size sliding window not handle this input?
- Prove why a larger, older prefix at the deque's back is permanently dominated.
