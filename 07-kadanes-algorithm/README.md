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

**Talking points:**

- I'd track the cheapest earlier buying price while scanning selling days.
- Each day contributes one candidate profit, and keeping the best profit covers every legal buy-
  before-sell pair.

**Solution:** Scan prices while tracking the smallest earlier price. At each day,
compute the profit from selling today after buying at that minimum and update the
best profit. This is Kadane-like: carry forward only the best state needed by
future positions instead of enumerating every transaction pair.

**Complexity:**

**Time complexity:** `O(n)` for `n` prices.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return the buy and sell days with a clear tie-breaking rule.
- What changes when unlimited transactions or a cooldown are allowed?

## 2. [Maximum Ascending Subarray Sum](https://leetcode.com/problems/maximum-ascending-subarray-sum/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd maintain the sum of the strictly ascending run ending at the current position.
- A larger next value extends that run; otherwise I'd restart and update the global maximum.

**Solution:** Maintain the sum of the strictly ascending run ending at the
current element. Extend it when the current value exceeds the previous value;
otherwise restart with the current value. Update the global maximum after each
step. This makes Kadane's extend-or-restart decision explicit.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return the boundaries of the best ascending range.
- Change the rule from strictly increasing to nondecreasing.

## 3. [Maximum Difference Between Increasing Elements](https://leetcode.com/problems/maximum-difference-between-increasing-elements/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd keep the smallest earlier value as a potential first element.
- I'd update the difference only when the current value is strictly larger, preserving the
  required index and value ordering.

**Solution:** Track the smallest value seen before the current position. If the
current value is larger, use their difference to update the answer; then update
the running minimum. The ordering constraint `i < j` is preserved because the
candidate minimum comes only from earlier positions.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return the two indices in addition to the difference.
- How does this relate to the one-transaction stock-profit problem?

## 4. [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) — Medium

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd track the best nonempty subarray sum ending at the current index.
- At each value I'd choose between extending that sum and restarting, then update the best sum
  seen anywhere.

**Solution:** Let `current` be the maximum sum of a nonempty subarray ending at
the current index. Set it to `max(nums[i], current + nums[i])`, then update the
global best. The recurrence decides whether the earlier suffix helps or whether
the optimal ending-here subarray should restart now.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return the range boundaries and handle an all-negative array.
- Derive the equivalent solution using the minimum prefix sum seen so far.

## 5. [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) — Medium

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd carry both the maximum and minimum products ending at each position.
- A negative value can turn the minimum into the next maximum, so both states must be computed
  from the previous pair.

**Solution:** Track both the maximum and minimum products ending at each position,
because multiplying by a negative value swaps their roles. For each value, choose
among starting fresh, extending the previous maximum, and extending the previous
minimum. Zero naturally acts as a possible restart.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Explain why retaining only the maximum ending product is insufficient.
- Return the subarray boundaries or discuss overflow behavior.

## 6. [Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/) — Medium

**Source:** Supplemental

**Talking points:**

- The answer either stays inside the array or wraps around by excluding a minimum-sum middle
  segment.
- I'd run maximum and minimum Kadane states together, with an all-negative check to avoid
  choosing an empty wrapped range.

**Solution:** The answer is either an ordinary non-wrapping maximum subarray or
the total sum minus the minimum subarray, which represents keeping the two ends.
Run maximum and minimum Kadane states together. If the maximum element is negative,
return the ordinary maximum so the wrapped formula does not select an empty range.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Why does `total - minimumSubarray` represent a wrapping range?
- Return the chosen circular range's indices.

## 7. [Maximum Absolute Sum of Any Subarray](https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/) — Medium

**Source:** Supplemental

**Talking points:**

- Every subarray sum is a difference between two prefix sums.
- I'd track the smallest and largest prefixes; their difference is the greatest absolute
  subarray sum, regardless of which appears first.

**Solution:** The primary implementation tracks the current prefix sum and
the smallest and largest prefix sums seen, including the initial zero. Their
difference is the largest absolute subarray sum: either ordering of those two
prefixes gives that magnitude. Equivalently, run maximum and minimum Kadane states
and compare the best positive sum with the magnitude of the best negative sum.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Derive and implement the prefix-extrema formulation.
- Return the indices and define how the empty subarray should be treated.

## 8. [Longest Turbulent Subarray](https://leetcode.com/problems/longest-turbulent-subarray/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd keep the lengths of alternating runs ending with an upward or downward comparison.
- Each strict comparison extends the opposite state, while equal neighbors reset both lengths.

**Solution:** Maintain lengths `up` and `down` for turbulent subarrays ending at
the current index. If the current value is greater than the previous one, set
`up = down + 1` and reset `down`; if smaller, do the symmetric update; equality
resets both to one. This is Kadane-style rolling state over alternating relations.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return the longest turbulent range rather than only its length.
- Reformulate the solution as a sliding window over comparison signs.

## 9. [Max Sum of Rectangle No Larger Than K](https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/) — Hard

**Source:** Supplemental

**Talking points:**

- I'd fix pairs of boundaries along the smaller dimension and compress each band into a 1D
  array.
- An ordered set of prefix sums finds the best compressed subarray at most `k`; ordinary Kadane
  alone cannot enforce that limit.

**Solution:** Fix pairs of boundaries along the smaller matrix dimension and
compress the cells between them into a 1D sum array. For that array, find the
largest subarray sum at most `k` by storing prefix sums in an ordered set and
querying the smallest earlier prefix at least `currentPrefix - k`. Repeat for all
boundary pairs, optionally stopping if the exact value `k` is found.

**Complexity:**

**Time complexity:** `O(a^2 b log(b + 1))` for `a = min(R, C)` and `b = max(R, C)`.

**Space complexity:** `O(b)` auxiliary compressed-array and ordered-set space.

**Potential interviewer follow-ups:**

- Why is ordinary Kadane insufficient when the result may not exceed `k`?
- How do you choose the compression direction for a rectangular matrix?

## 10. [Substring With Largest Variance](https://leetcode.com/problems/substring-with-largest-variance/) — Hard

**Source:** Supplemental

**Talking points:**

- I'd scan once for each ordered pair of major and minor characters, treating them as plus one
  and minus one.
- The candidate must contain a minor character, and a negative balance can restart only when
  another minor remains.

**Solution:** For every ordered pair of distinct characters `(major, minor)`,
scan the string as if `major` contributes `+1`, `minor` contributes `-1`, and all
others are ignored. Use a Kadane-like reset when the running balance is negative
and another `minor` remains, but update the answer only after the substring has
included at least one `minor`. Considering ordered pairs covers both majority
directions.

**Complexity:**

**Time complexity:** `O(A^2 n)` for string length `n` and alphabet size `A`; `O(n)` with fixed
`A = 26`.

**Space complexity:** `O(A)` frequency storage; `O(1)` for lowercase English letters.

**Potential interviewer follow-ups:**

- Why must the scan track whether the lower-frequency character has appeared?
- Explain the reset condition and why remaining occurrences of `minor` matter.
