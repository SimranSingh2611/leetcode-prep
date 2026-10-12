# 07 — Kadane's Algorithm

Kadane's algorithm is a rolling optimization over contiguous ranges. Its core
state is the best result ending at the current position; at each step, either
extend the prior range or start fresh. The later questions generalize that state
to products, circular arrays, ordered pairs, and two dimensions.

## How to recognize this pattern

**Keywords / hints:** “maximum contiguous sum,” “best segment ending here,” or a small running state that decides between extending the previous segment and restarting.

**Structural test:** Can every best segment ending at i be derived from the best compatible segment ending at i-1 plus the new element, or a restart at i? For maximum sum, endHere=max(a[i],endHere+a[i]).

**Invariant / mental model:** endHere is the best nonempty segment ending at the current position; best is the best seen anywhere. Initialize from the first element when empty subarrays are forbidden. Product variants need both minimum and maximum states because negative multiplication flips order.

**When not to use it:** Fixed-length segments, arbitrary target-sum constraints, or noncontiguous subsequences do not use the basic recurrence unchanged. The folder includes explicitly related running-state variants; not every one is textbook maximum-sum Kadane.

**Prerequisites:** Arrays and recurrence reasoning; 02 prefix sums provides alternative range-sum views. Revisit constrained Hard variants after their ordered-search helpers.

Keywords are clues, not guarantees. Prove the structural condition before choosing a template.

[All-pattern recognition guide](../PATTERN_RECOGNITION.md) · [Full roadmap](../README.md)

**Difficulty mix:** 3 Easy / 5 Medium / 2 Hard

**Java implementations:** [Open the solution index](./java/README.md)

## 1. [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) — Easy

**Source:** Blind 75 + NeetCode 150

**Solution:** Scan prices while tracking the smallest earlier price. At each day,
compute the profit from selling today after buying at that minimum and update the
best profit. This is Kadane-like: carry forward only the best state needed by
future positions instead of enumerating every transaction pair.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Return the buy and sell days with a clear tie-breaking rule.
- What changes when unlimited transactions or a cooldown are allowed?

## 2. [Maximum Ascending Subarray Sum](https://leetcode.com/problems/maximum-ascending-subarray-sum/) — Easy

**Source:** Supplemental

**Solution:** Maintain the sum of the strictly ascending run ending at the
current element. Extend it when the current value exceeds the previous value;
otherwise restart with the current value. Update the global maximum after each
step. This makes Kadane's extend-or-restart decision explicit.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Return the boundaries of the best ascending range.
- Change the rule from strictly increasing to nondecreasing.

## 3. [Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements/) — Easy

**Source:** Supplemental

**Solution:** Track the smallest value seen before the current position. If the
current value is larger, use their difference to update the answer; then update
the running minimum. The ordering constraint `i < j` is preserved because the
candidate minimum comes only from earlier positions.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Return the two indices in addition to the difference.
- How does this relate to the one-transaction stock-profit problem?

## 4. [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Let `current` be the maximum sum of a nonempty subarray ending at
the current index. Set it to `max(nums[i], current + nums[i])`, then update the
global best. The recurrence decides whether the earlier suffix helps or whether
the optimal ending-here subarray should restart now.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Return the range boundaries and handle an all-negative array.
- Derive the equivalent solution using the minimum prefix sum seen so far.

## 5. [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Track both the maximum and minimum products ending at each position,
because multiplying by a negative value swaps their roles. For each value, choose
among starting fresh, extending the previous maximum, and extending the previous
minimum. Zero naturally acts as a possible restart.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Explain why retaining only the maximum ending product is insufficient.
- Return the subarray boundaries or discuss overflow behavior.

## 6. [Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/) — Medium

**Source:** Supplemental

**Solution:** The answer is either an ordinary non-wrapping maximum subarray or
the total sum minus the minimum subarray, which represents keeping the two ends.
Run maximum and minimum Kadane states together. If the maximum element is negative,
return the ordinary maximum so the wrapped formula does not select an empty range.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Why does `total - minimumSubarray` represent a wrapping range?
- Return the chosen circular range's indices.

## 7. [Maximum Absolute Sum of Any Subarray](https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/) — Medium

**Source:** Supplemental

**Solution:** Track both the maximum and minimum subarray sums ending at each
position. The largest absolute answer is the greater of the maximum positive sum
and the absolute value of the minimum negative sum. Equivalently, it is the
difference between the largest and smallest prefix sums.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Derive and implement the prefix-extrema formulation.
- Return the indices and define how the empty subarray should be treated.

## 8. [Longest Turbulent Subarray](https://leetcode.com/problems/longest-turbulent-subarray/) — Medium

**Source:** Supplemental

**Solution:** Maintain lengths `up` and `down` for turbulent subarrays ending at
the current index. If the current value is greater than the previous one, set
`up = down + 1` and reset `down`; if smaller, do the symmetric update; equality
resets both to one. This is Kadane-style rolling state over alternating relations.

**Complexity:** `O(n)` time and `O(1)` space.

**Potential interviewer follow-ups:**

- Return the longest turbulent range rather than only its length.
- Reformulate the solution as a sliding window over comparison signs.

## 9. [Max Sum of Rectangle No Larger Than K](https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/) — Hard

**Source:** Supplemental

**Solution:** Fix pairs of boundaries along the smaller matrix dimension and
compress the cells between them into a 1D sum array. For that array, find the
largest subarray sum at most `k` by storing prefix sums in an ordered set and
querying the smallest earlier prefix at least `currentPrefix - k`. Repeat for all
boundary pairs, optionally stopping if the exact value `k` is found.

**Complexity:** `O(min(m,n)^2 * max(m,n) * log(max(m,n)))` time and
`O(max(m,n))` space after orienting the matrix advantageously.

**Potential interviewer follow-ups:**

- Why is ordinary Kadane insufficient when the result may not exceed `k`?
- How do you choose the compression direction for a rectangular matrix?

## 10. [Substring With Largest Variance](https://leetcode.com/problems/substring-with-largest-variance/) — Hard

**Source:** Supplemental

**Solution:** For every ordered pair of distinct characters `(major, minor)`,
scan the string as if `major` contributes `+1`, `minor` contributes `-1`, and all
others are ignored. Use a Kadane-like reset when the running balance is negative
and another `minor` remains, but update the answer only after the substring has
included at least one `minor`. Considering ordered pairs covers both majority
directions.

**Complexity:** `O(A^2 n)` time and `O(A)` space for alphabet size `A`; with
lowercase English letters, this is effectively `O(n)` time.

**Potential interviewer follow-ups:**

- Why must the scan track whether the lower-frequency character has appeared?
- Explain the reset condition and why remaining occurrences of `minor` matter.
