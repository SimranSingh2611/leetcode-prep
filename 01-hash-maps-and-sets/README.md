# 01 — Hash Maps and Sets

Use hashing when the question needs fast membership checks, complement lookup,
frequency counting, grouping, or deduplication. The central trade-off is usually
extra `O(n)` space in exchange for avoiding a nested scan.

## How to recognize this pattern

**Keywords / hints:** “duplicate,” “frequency,” “seen before,” “complement,” “group by,” or repeated exact-key membership queries.

**Structural test:** Can a useful property of each earlier element be stored as a key so the current element can find its partner or group without rescanning the prefix?

**Invariant / mental model:** The set/map summarizes exactly the elements already processed. In pair problems, check the complement before inserting the current element if an element cannot pair with itself.

**When not to use it:** Hashing does not maintain sorted order. For a small fixed alphabet, an array can be simpler; for sorted inputs, two pointers may avoid extra storage.

**Prerequisites:** Arrays, strings, iteration, and the difference between a key and a value.

Keywords are clues, not guarantees. Prove the structural condition before choosing a template.

[All-pattern recognition guide](../PATTERN_RECOGNITION.md) · [Full roadmap](../README.md)

**Difficulty mix:** 3 Easy / 5 Medium / 2 Hard

**Java implementations:** [Open the solution index](./java/README.md)

## 1. [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) — Easy

**Source:** Blind 75 + NeetCode 150

**Solution:** Scan the array while maintaining a set of previously seen values.
If the current value is already present, return `true`; otherwise insert it.
The set makes each membership test `O(1)` on average, so no pairwise comparison
is needed.

**Complexity:** `O(n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- How would you return the first pair of duplicate indices instead of a boolean?
- What changes if memory is constrained but modifying or sorting the input is allowed?

## 2. [Valid Anagram](https://leetcode.com/problems/valid-anagram/) — Easy

**Source:** Blind 75 + NeetCode 150

**Solution:** Count each character in the first string and subtract the counts
while reading the second. The strings are anagrams exactly when their lengths
match and every final count is zero. A fixed array is ideal for a small known
alphabet; a hash map handles arbitrary characters.

**Complexity:** `O(n)` time and `O(1)` space for a fixed alphabet, otherwise
`O(k)` space for `k` distinct characters.

**Potential interviewer follow-ups:**

- How would you support Unicode rather than lowercase English letters?
- Compare the counting solution with sorting both strings.

## 3. [Two Sum](https://leetcode.com/problems/two-sum/) — Easy

**Source:** Blind 75 + NeetCode 150

**Solution:** For each value `x`, check whether `target - x` is already in a map
from value to index. Check before inserting `x` so the same element cannot be
used twice. This converts the brute-force search over every pair into one pass.

**Complexity:** `O(n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- If the input were sorted, could you solve it with `O(1)` extra space?
- How would you return every unique pair or count the number of valid pairs?

## 4. [Group Anagrams](https://leetcode.com/problems/group-anagrams/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Map each word to a canonical signature. A sorted word is the
simplest signature; for lowercase letters, a tuple of 26 character counts avoids
sorting. Words with the same signature are appended to the same output bucket.

**Complexity:** With count signatures, `O(nk)` time and `O(nk)` output/storage
space for `n` words of maximum length `k`.

**Potential interviewer follow-ups:**

- When is a sorted-string key preferable to a frequency-vector key?
- How would the signature change for a large or unknown character set?

## 5. [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) — Medium

**Source:** NeetCode 150

**Solution:** Maintain sets for every row, column, and 3-by-3 box. For each
filled cell, derive its box as `(row / 3, col / 3)` and reject the board if the
digit already occurs in any of its three sets; otherwise add it to all three.
This validates only the current entries—it does not solve the puzzle.

**Complexity:** `O(n^2)` time and `O(n^2)` space for a generalized `n`-by-`n`
board; both are constant for the standard 9-by-9 board.

**Potential interviewer follow-ups:**

- Generalize the box calculation to an `n^2`-by-`n^2` Sudoku.
- Can you encode the three sets with bit masks instead?

## 6. [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** First build a value-to-frequency map. Place each value into a
bucket indexed by its frequency, then scan the buckets from high to low until
`k` values have been collected. Since no frequency exceeds `n`, bucket sorting
avoids the `O(n log n)` cost of sorting all entries.

**Complexity:** `O(n)` time and `O(n)` space.

**Potential interviewer follow-ups:**

- Solve it with a size-`k` min-heap and compare the complexity.
- How would you maintain the answer for a stream of updates?

## 7. [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Put all values in a set. Only begin counting from a value whose
predecessor is absent, because that value is the start of a sequence. Although
the inner loop looks nested, every number is traversed as part of at most one
sequence, keeping the total work linear.

**Complexity:** `O(n)` expected time and `O(n)` space.

**Potential interviewer follow-ups:**

- Why is the algorithm still `O(n)` when it contains a loop inside a loop?
- Could you solve the problem with sorting, or dynamically with Union-Find?

## 8. [Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings/) — Medium

**Source:** Blind 75 + NeetCode 150

**Solution:** Prefix each string with its character length and a separator, such
as `5#hello`. To decode, read digits until the separator, parse the length, then
consume exactly that many characters as the payload. The decoder never confuses
separator characters inside a string with structural separators because payload
boundaries are determined by length.

**Complexity:** `O(C)` time for both operations and `O(C)` output space, where
`C` is the total number of encoded characters.

**Potential interviewer follow-ups:**

- Why does simply joining strings with a delimiter fail, and how could escaping fix it?
- How would you adapt the format to bytes, streaming input, malformed data, or very large strings?

## 9. [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) — Hard

**Source:** Supplemental

**Solution:** Values outside `[1, n]` cannot affect the answer. Repeatedly swap
each in-range value `x` into index `x - 1`, stopping when it is already placed
or a duplicate blocks the swap. The first index `i` that does not contain
`i + 1` reveals the answer; if all positions match, the answer is `n + 1`.

**Complexity:** `O(n)` time and `O(1)` extra space because every successful
swap permanently places at least one value.

**Potential interviewer follow-ups:**

- Prove why the answer must lie in `[1, n + 1]`.
- If input mutation were forbidden, what set- or bitset-based solution would you use?

## 10. [Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line/) — Hard

**Source:** Supplemental

**Solution:** Treat each point as an anchor and count all slopes from it in a
hash map. Represent a slope as the reduced integer pair `(dy/gcd, dx/gcd)` with
a consistent sign and special handling for vertical and horizontal lines; avoid
floating-point keys. The largest count for an anchor plus the anchor itself is a
candidate answer.

**Complexity:** `O(n^2)` time and `O(n)` space per anchor.

**Potential interviewer follow-ups:**

- How would you handle duplicate points if the input allowed them?
- Why can floating-point slopes produce incorrect groups, and how do you normalize signs?
