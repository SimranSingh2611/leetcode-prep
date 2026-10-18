# Java Solutions — Binary Search

All sixteen implementations use the [folder's adjacent-endpoint convention](../README.md):
`while (lo + 1 < hi)`, endpoint assignments to `mid`, and return `hi` for
first true or `lo` for last true. Read the predicate, sentinel, and final-output
notes before coding. Find Peak Element uses the same updates with a local slope
invariant and returns any peak.

Compile/submit files independently. Most use `Solution`; problem 8 uses
`TimeMap`, and LeetCode supplies `VersionControl` for problem 3.
Implementations preserve their input arrays and use Java 8-compatible features.

| Problem / Java / practice links | Classification for this implementation | What is minimized or maximized? |
| --- | --- | --- |
| [704. Binary Search](./01-BinarySearch.java) · [LeetCode](https://leetcode.com/problems/binary-search/) · [NeetCode 150](https://neetcode.io/problems/binary-search/question?list=neetcode150) | Minimization (first true) | First index with value >= target; then check equality |
| [35. Search Insert Position](./02-SearchInsertPosition.java) · [LeetCode](https://leetcode.com/problems/search-insert-position/) | Minimization (first true) | Earliest insertion index |
| [278. First Bad Version](./03-FirstBadVersion.java) · [LeetCode](https://leetcode.com/problems/first-bad-version/) | Minimization (first true) | First bad version number |
| [74. Search a 2D Matrix](./04-SearchA2DMatrix.java) · [LeetCode](https://leetcode.com/problems/search-a-2d-matrix/) · [NeetCode 150](https://neetcode.io/problems/search-2d-matrix/question?list=neetcode150) | Minimization (first true) | First flat index with value >= target; then check equality |
| [875. Koko Eating Bananas](./05-KokoEatingBananas.java) · [LeetCode](https://leetcode.com/problems/koko-eating-bananas/) · [NeetCode 150](https://neetcode.io/problems/eating-bananas/question?list=neetcode150) | Minimization (first true) | Smallest feasible eating speed |
| [153. Find Minimum in Rotated Sorted Array](./06-FindMinimumInRotatedSortedArray.java) · [LeetCode](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) · [NeetCode 150](https://neetcode.io/problems/find-minimum-in-rotated-sorted-array/question?list=neetcode150) | Minimization (first true) | First index in the low segment; return its value |
| [33. Search in Rotated Sorted Array](./07-SearchInRotatedSortedArray.java) · [LeetCode](https://leetcode.com/problems/search-in-rotated-sorted-array/) · [NeetCode 150](https://neetcode.io/problems/find-target-in-rotated-sorted-array/question?list=neetcode150) | Two minimization searches | Pivot index, then first qualifying index in the selected sorted segment |
| [981. Time Based Key-Value Store](./08-TimeBasedKeyValueStore.java) · [LeetCode](https://leetcode.com/problems/time-based-key-value-store/) · [NeetCode 150](https://neetcode.io/problems/time-based-key-value-store/question?list=neetcode150) | Maximization (last true) | Latest history index whose timestamp <= query |
| [4. Median of Two Sorted Arrays](./09-MedianOfTwoSortedArrays.java) · [LeetCode](https://leetcode.com/problems/median-of-two-sorted-arrays/) · [NeetCode 150](https://neetcode.io/problems/median-of-two-sorted-arrays/question?list=neetcode150) | Minimization (first true) | First cut satisfying B-left <= A-right; then calculate the median |
| [410. Split Array Largest Sum](./10-SplitArrayLargestSum.java) · [LeetCode](https://leetcode.com/problems/split-array-largest-sum/) | Minimization (first true) | Smallest feasible maximum subarray sum |
| [34. Find First and Last Position](./11-FindFirstAndLastPosition.java) · [LeetCode](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | Both | Minimize first occurrence index; maximize last occurrence index |
| [162. Find Peak Element](./12-FindPeakElement.java) · [LeetCode](https://leetcode.com/problems/find-peak-element/) | Any peak (minimization-style updates) | Local peak; no minimum/maximum index objective |
| [441. Arranging Coins](./13-ArrangingCoins.java) · [LeetCode](https://leetcode.com/problems/arranging-coins/) | Maximization (last true) | Most complete rows affordable with n coins |
| [540. Single Element in a Sorted Array](./14-SingleElementInSortedArray.java) · [LeetCode](https://leetcode.com/problems/single-element-in-a-sorted-array/) | Minimization (first true) | First index with broken pair alignment; return its value |
| [744. Find Smallest Letter Greater Than Target](./15-FindSmallestLetterGreaterThanTarget.java) · [LeetCode](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) | Minimization (first true) | First index with letter > target; wrap if absent |
| [1150. Check If a Number Is Majority Element](./16-CheckIfNumberIsMajorityElement.java) · [LeetCode](https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/) | Both | First and last occurrence indices; check count > n/2 |

The classifications describe the variable searched by these implementations.
For minimization, keep lo false and hi true; for maximization, keep lo true and
hi false. These bounds can be real candidates or virtual sentinels. They must
bracket the transition; they need not be absolute minimum/maximum values.
The answer boundary is hi or lo once `lo + 1 == hi`, with any final equality,
range, or output-conversion check shown in the table.
Find Peak Element instead preserves a rising left edge and falling right edge;
its slope predicate can change truth more than once. See the folder's proof.

[Boundary rules and examples](../README.md#choosing-lo-and-hi-correctly).

The TreeMap alternative is intentionally not a binary-search loop: it supports
out-of-order writes at O(log m) per insertion instead of amortized O(1) appends.
To submit it for a changed requirement, rename `OutOfOrderTimeMap` as appropriate.

[Run the focused tests](../README.md#local-verification).
