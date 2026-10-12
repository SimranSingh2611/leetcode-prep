# 04 — Sliding Window

A sliding window represents a contiguous range and maintains just enough state
to add the new right endpoint and remove the old left endpoint. Start with fixed
windows, then learn variable windows whose validity changes monotonically, and
finally add frequency maps or monotonic deques.

## How to recognize this pattern

**Keywords / hints:** “longest/shortest contiguous substring or subarray,” “at most k,” “no repeats,” “fixed length k,” or a constraint maintained by adding/removing endpoints.

**Structural test:** Can window state be updated incrementally, and can validity be restored by moving the left boundary forward only? For variable windows, prove the relevant monotonicity rather than relying on the word “subarray.”

**Invariant / mental model:** The state describes the current contiguous window. Longest-valid problems usually shrink while invalid, then record; shortest-valid problems usually record and shrink while valid. Fixed-size windows remove the element that leaves.

**When not to use it:** Negative values break the usual sum-based expand/shrink monotonicity, though they do not invalidate every kind of window problem. Use prefix-state techniques for exact sums with negatives. Noncontiguous subsequences are not windows.

**Prerequisites:** 03 two pointers, 01 frequency maps, 02 prefix sums as a contrast. Sliding Window Maximum adds a monotonic deque; revisit after 09.

Keywords are clues, not guarantees. Prove the structural condition before choosing a template.

[All-pattern recognition guide](../PATTERN_RECOGNITION.md) · [Full roadmap](../README.md)

**Difficulty mix:** 3 Easy / 5 Medium / 2 Hard

**Java implementations:** [Open the solution index](./java/README.md)

## 1. [Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd compute the first length-`k` sum and slide by adding the entering value and subtracting
  the leaving one.
- All windows have the same size, so maximizing their sum also maximizes their average.

**Solution:** Sum the first `k` elements. For every later index, add the entering
value and subtract the value leaving at `i - k`, then update the largest sum.
Divide by `k` only once at the end; maximizing the sum and average is equivalent
because every candidate has the same length.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- How would you return the window's starting index as well as its average?
- What changes if the window may have any length of at least `k`?

## 2. [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd keep a set of values from the allowed index-distance window.
- Before checking the next value, I'd expire anything too far behind; a match then satisfies
  both duplication and distance.

**Solution:** Maintain a set containing at most the previous `k` values. Before
adding `nums[right]`, remove the element that has just fallen more than `k`
indices behind; membership now answers whether the same value occurs within the
allowed distance. A last-seen-index map is an equally direct alternative.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` values.

**Space complexity:** `O(min(n, k + 1))` auxiliary space, including the current value during
insertion.

**Potential interviewer follow-ups:**

- Solve it with a map of last-seen indices.
- How would you also enforce that the two values differ by at most `t`?

## 3. [Defuse the Bomb](https://leetcode.com/problems/defuse-the-bomb/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd maintain the sum of the next or previous `abs(k)` elements, depending on the sign.
- Modulo indexing slides that window around the circle; `k = 0` produces zeros.

**Solution:** Treat the array as circular, choose the initial `|k|`-element
window on the correct side of each index, and slide it using modulo indexing.
For positive `k` the window contains following elements; for negative `k` it
contains preceding elements; for zero every answer is zero.

**Complexity:**

**Time complexity:** `O(n)` under the guarantee `abs(k) < n`.

**Space complexity:** `O(1)` auxiliary state, plus `O(n)` output.

**Potential interviewer follow-ups:**

- Eliminate modulo operations by temporarily viewing the input as doubled.
- What if `|k|` can be larger than the array length?

## 4. [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) — Medium

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd store each character's latest index while expanding the right edge.
- A repeat inside the window moves the left edge just beyond its previous occurrence, and the
  left edge never moves backward.

**Solution:** Expand the right edge and track the latest index of every character.
On a repeat inside the current window, jump `left` to one position after that
earlier occurrence, using `max` so `left` never moves backward. Every window
after adjustment contains unique characters.

**Complexity:**

**Time complexity:** `O(n)` for string length `n`.

**Space complexity:** `O(1)` for the primary 128-entry ASCII array; a generalized map uses
`O(A)` for `A` distinct characters.

**Potential interviewer follow-ups:**

- Return the substring itself, with a deterministic tie-breaking rule.
- Find the longest substring in which every character appears at most twice.

## 5. [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) — Medium

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd compare the window length minus its most frequent character count with the replacement
  budget.
- A running maximum frequency supports the longest-length calculation even when that maximum
  becomes stale during shrinking.

**Solution:** Track frequencies in the window and the highest frequency seen.
The window is feasible when `windowLength - maxFrequency <= k`; otherwise move
the left edge and decrement its character. The recorded maximum frequency may
be stale, but it never causes the best answer to shrink and remains sufficient
for finding the maximum length.

**Complexity:**

**Time complexity:** `O(n)` for string length `n`.

**Space complexity:** `O(1)` for 26 uppercase-letter counters.

**Potential interviewer follow-ups:**

- Why is it safe not to decrease `maxFrequency` when the left edge moves?
- Return the replacement character and the actual window.

## 6. [Permutation in String](https://leetcode.com/problems/permutation-in-string/) — Medium

**Source:** NeetCode 150

**Talking points:**

- A permutation must occupy a window exactly as long as the first string.
- I'd slide that fixed window over the second string and compare its 26-letter counts with the
  required counts.

**Solution:** A permutation must occupy a window exactly as long as the pattern.
Maintain character counts for that fixed-size window and compare them with the
pattern counts after each slide. An optimized version tracks how many alphabet
positions currently have equal counts, making each comparison constant time.

**Complexity:**

**Time complexity:** `O(n + m)` for the two string lengths.

**Space complexity:** `O(1)` for fixed lowercase-letter counters.

**Potential interviewer follow-ups:**

- Return every starting index of an anagram rather than a boolean.
- Support a large Unicode alphabet without comparing whole maps each time.

## 7. [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) — Medium

**Source:** Supplemental

**Talking points:**

- Positive values make the window sum monotonic as either boundary moves.
- I'd expand until the target is met, then shrink repeatedly while recording the shortest valid
  window.

**Solution:** Because all values are positive, expanding right can only increase
the sum and removing from the left can only decrease it. Add each right value,
then shrink from the left while the target is still met, recording the shortest
valid length before each removal.

**Complexity:**

**Time complexity:** `O(n)` amortized overall; each pointer advances at most `n` times.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Why does this window fail if negative numbers are permitted?
- Solve the positive-number version with prefix sums and binary search.

## 8. [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd frame this as the longest window with at most two distinct fruit types.
- When a third type appears, I'd shrink and update counts until the window is valid again.

**Solution:** This is the longest subarray containing at most two distinct values.
Expand right while counting fruit types; whenever more than two types are present,
decrement and possibly remove the leftmost type until the window is valid again.
Update the best length for each valid window.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` fruits.

**Space complexity:** `O(1)` auxiliary space; at most three types are tracked during adjustment.

**Potential interviewer follow-ups:**

- Generalize the implementation to at most `k` distinct values.
- How would you compute the number of subarrays with exactly two distinct values?

## 9. [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) — Hard

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd count the required characters, expand until they are all covered, then shrink to the
  smallest valid window.
- When shrinking removes a required occurrence, I'd resume expansion; storing only the best
  endpoints delays output copying.

**Solution:** Count required characters and track how many required character
types currently meet their target. Expand right until all requirements are met,
then repeatedly shrink left while preserving validity, updating the best range.
When removing a needed character drops it below its target, the window becomes
invalid and expansion resumes.

**Complexity:**

**Time complexity:** `O(n + m)` for source length `n` and target length `m`.

**Space complexity:** `O(1)` ASCII counter state for the primary solution, plus up to `O(n)`
returned substring storage; a generalized map uses `O(A)` counters.

**Potential interviewer follow-ups:**

- Return all minimum windows if several have the same length.
- Adapt the requirement from a multiset of characters to a sequence that must appear in order.

## 10. [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) — Hard

**Source:** NeetCode 150

**Talking points:**

- I'd keep candidate indices in a deque with decreasing values.
- Expired indices leave the front and dominated values leave the back, so the front is always
  the current maximum.

**Solution:** Keep indices in a deque with values in decreasing order. Remove
indices that leave the window from the front and remove smaller or equal values
from the back before appending the new index, since they can never become a
future maximum. The front is then the maximum for every complete window.

**Complexity:**

**Time complexity:** `O(n)` amortized overall.

**Space complexity:** `O(k)` auxiliary deque space, plus `O(n - k + 1)` output.

**Potential interviewer follow-ups:**

- Why are smaller values behind a newer, larger value permanently dominated?
- How would you maintain both the minimum and maximum of each window?
