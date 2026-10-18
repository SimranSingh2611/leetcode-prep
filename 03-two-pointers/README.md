# 03 — Two Pointers

Two-pointer solutions coordinate indices so that one comparison eliminates many
possibilities. Common forms are pointers moving inward, pointers moving in the
same direction, and expansion around a center. Always be able to justify why a
particular pointer movement cannot discard a valid answer.

## How to recognize this pattern

**Keywords / hints:** “sorted,” “pair with target,” “palindrome,” “in place,” “merge two ordered sequences,” “remove duplicates,” or a safe boundary-elimination rule.

**Structural test:** Can one comparison prove that an entire group of candidates can be discarded by moving one pointer without ever moving it back? State the proof separately for inward, read/write, fast/slow, and center-expansion variants.

**Invariant / mental model:** The answer remains among the unprocessed candidates; the processed output region is already correct. Sorted pair search discards an endpoint only when all pairs using it are provably too small or too large.

**When not to use it:** Two indices alone are not an algorithm. Unsorted pair sums lack the sorted-order elimination proof; use hashing or sort when allowed. A contiguous range with maintained aggregate state is more specifically sliding window.

**Prerequisites:** Array indexing and sorted-order reasoning. Fast/slow pointer applications return in 10 linked lists.

Keywords are clues, not guarantees. Prove the structural condition before choosing a template.

[All-pattern recognition guide](../PATTERN_RECOGNITION.md) · [Full roadmap](../README.md)

**Difficulty mix:** 3 Easy / 5 Medium / 2 Hard

**Java implementations:** [Open the solution index](./java/README.md)

## 1. [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/is-palindrome/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd compare characters from both ends, skipping non-alphanumeric characters.
- Each case-insensitive match lets both pointers move inward; any mismatch settles the answer.

**Solution:** Place pointers at both ends. Skip non-alphanumeric characters,
compare the remaining characters case-insensitively, and move both pointers
inward after a match. A mismatch proves the normalized string is not a palindrome.

**Complexity:**

**Time complexity:** `O(n)` for a string of length `n`.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Avoid constructing a cleaned copy of the string.
- How would Unicode normalization or accented characters change the requirements?

## 2. [Reverse String](https://leetcode.com/problems/reverse-string/) — Easy

**Source:** Supplemental

**Talking points:**

- I'd swap the two endpoint characters and move inward.
- The outer positions are final after each swap, so the array can be reversed in place.

**Solution:** Put one pointer at each end of the character array. Swap their
values and move them toward each other until they cross. This is the simplest
example of an in-place converging-pointer invariant.

**Complexity:**

**Time complexity:** `O(n)` for `n` characters.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Reverse the words while preserving the characters within each word.
- How would you reverse a linked list where random indexing is unavailable?

## 3. [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) — Easy

**Source:** Supplemental

**Talking points:**

- The largest remaining square comes from one of the two endpoints.
- I'd compare their magnitudes and fill the result from right to left, avoiding another sort.

**Solution:** The largest remaining square must come from one of the two ends,
because absolute values grow away from zero. Compare the endpoint magnitudes,
write the larger square into the result from right to left, and advance that
endpoint. This avoids sorting the squared values.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary pointer state, plus `O(n)` output.

**Potential interviewer follow-ups:**

- Can the output be produced in nondecreasing order without filling it backward?
- What integer-overflow issues arise when squaring values?

## 4. [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/two-integer-sum-ii/question?list=neetcode150)

**Source:** NeetCode 150

**Talking points:**

- I'd begin with the smallest and largest values in the sorted array.
- A sum below target eliminates the left value; a sum above target eliminates the right value.

**Solution:** Start at the smallest and largest values. If their sum is too
small, incrementing the left pointer is the only move that can increase it; if
too large, decrement the right pointer. Sorted order is the property that makes
discarding an entire row or column of candidate pairs safe.

**Complexity:**

**Time complexity:** `O(n)` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Prove why each pointer movement cannot skip the answer.
- How would the solution change if every unique matching pair were required?

## 5. [3Sum](https://leetcode.com/problems/3sum/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/three-integer-sum/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd sort, fix one value, and solve the remaining two-sum problem with inward pointers.
- Skipping equal fixed values and equal pair endpoints ensures each triple appears once.

**Solution:** Sort the array, fix each first value, and run a two-sum two-pointer
search over the suffix. Skip equal fixed values and skip equal endpoint values
after recording a triple so the result contains no duplicates. Early bounds can
stop iterations whose smallest or largest possible totals cannot reach zero.

**Complexity:**

**Time complexity:** `O(n^2)` for `n` values.

**Space complexity:** `O(1)` pointer state, plus sorting workspace and `O(r)` output for `r`
triples; a conservative Java auxiliary bound including sorting is `O(n)`.

**Potential interviewer follow-ups:**

- Explain precisely where duplicate skipping is safe.
- Generalize the approach to `k`-sum.

## 6. [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/max-water-container/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd measure the container formed by the current endpoints.
- Only moving the shorter wall can improve the limiting height, since every move reduces the
  width.

**Solution:** Evaluate the area between the two endpoints. Width always shrinks,
so only moving the shorter line can possibly find a taller limiting boundary and
improve the area; moving the taller line while retaining the shorter one cannot.
Update the best area before advancing the limiting side.

**Complexity:**

**Time complexity:** `O(n)` for `n` heights.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Give a proof for moving the shorter side.
- What changes if the x-coordinates are not uniformly spaced?

## 7. [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/palindromic-substrings/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Talking points:**

- I'd expand around every character and every gap as a palindrome center.
- Each successful expansion counts one palindrome, covering both odd and even lengths.

**Solution:** Treat each character and each gap between characters as a center.
Expand left and right while the characters match, counting every valid expansion.
Separate odd and even centers, or encode both in one loop over `2n - 1` centers.

**Complexity:**

**Time complexity:** `O(n^2)` worst case for string length `n`.

**Space complexity:** `O(1)` auxiliary space.

**Potential interviewer follow-ups:**

- Return the longest palindromic substring rather than the count.
- Can you improve the worst-case time using Manacher's algorithm?

## 8. [4Sum](https://leetcode.com/problems/4sum/) — Medium

**Source:** Supplemental

**Talking points:**

- I'd sort, fix the first two values, and search the remaining pair with two pointers.
- I'd skip duplicates at every level and use a wider type for four-value sums.

**Solution:** Sort, fix the first two indices, and use inward-moving pointers for
the remaining pair. Skip duplicates at every level and use a wider integer type
for the sum. Lower and upper bounds based on the smallest and largest remaining
values can prune impossible branches.

**Complexity:**

**Time complexity:** `O(n^3)` for `n` values.

**Space complexity:** `O(1)` pointer state, plus sorting workspace and `O(r)` output for `r`
quadruples; a conservative Java auxiliary bound including sorting is `O(n)`.

**Potential interviewer follow-ups:**

- Turn the solution into a reusable recursive `kSum` routine.
- What pruning bounds improve performance without changing the worst case?

## 9. [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/trapping-rain-water/question?list=neetcode150)

**Source:** NeetCode 150

**Talking points:**

- I'd move inward while tracking the maximum wall seen from each side.
- The side with the lower current boundary can be settled immediately because the opposite
  boundary is already high enough.

**Solution:** Track `leftMax` and `rightMax` while moving pointers inward. When
the left height is no greater than the right height, the left side's water is
already determined by `leftMax`; process it and move left. Otherwise process the
right side symmetrically. The opposite boundary is guaranteed to be high enough
for the side being processed.

**Complexity:**

**Time complexity:** `O(n)` for `n` heights.

**Space complexity:** `O(1)` auxiliary space; the boundary-array alternative uses `O(n)`.

**Potential interviewer follow-ups:**

- Derive the prefix-max/suffix-max and monotonic-stack alternatives.
- Why is it safe to finalize one side before knowing every interior height?

## 10. [Minimum Number of Moves to Make Palindrome](https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/) — Hard

**Source:** Supplemental

**Talking points:**

- I'd match the leftmost character with its rightmost available partner and bubble that partner
  to the right edge.
- If no partner exists, that character belongs in the center, so I'd move it inward and retry.

**Solution:** Match the leftmost character by searching backward from the right.
If found, bubble that match to the right boundary using adjacent swaps, count the
swaps, and move both boundaries inward. If no match exists, the left character
must be the unique middle character, so swap it one step toward the center and
retry. This greedy choice fixes an outer pair with the minimum local swaps.

**Complexity:**

**Time complexity:** `O(n^2)` worst case for string length `n`.

**Space complexity:** `O(n)` for Java's mutable character-array copy; the swap logic itself uses
`O(1)` state.

**Potential interviewer follow-ups:**

- Prove that greedily fixing the outermost pair is optimal.
- Can you count the required moves in `O(n log n)` using inversion counting?
