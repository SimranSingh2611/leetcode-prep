# Java Solutions — 15 — Heaps and Priority Queues

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Kth Largest Element in a Stream](01-KthLargestInAStream.java) — KthLargest is the judge's required design class; the input array is unchanged.
2. [Last Stone Weight](02-LastStoneWeight.java) — Solution uses reverseOrder rather than subtraction in its comparator and preserves stones.
3. [Relative Ranks](03-RelativeRanks.java) — Solution leaves scores unchanged. This is a warm-up, not a case where a heap asymptotically beats sorting.
4. [Kth Largest Element in an Array](04-KthLargestElementInAnArray.java) — Solution preserves nums. QuickselectSolution is the expected-time and auxiliary-space improvement, with a quadratic worst-case caveat.
5. [Find K Pairs with Smallest Sums](05-FindKPairsWithSmallestSums.java) — Solution uses long pair sums in comparisons and leaves both arrays unchanged.
6. [Task Scheduler](06-TaskScheduler.java) — Solution demonstrates heap scheduling. FormulaSolution is a simpler counting follow-up, not an asymptotic improvement under a fixed 26-letter alphabet.
7. [Design Twitter](07-DesignTwitter.java) — Twitter is the design class. Self-follow is ignored to avoid duplicate feed entries; historical tweets are retained.
8. [Reorganize String](08-ReorganizeString.java) — Solution returns any valid arrangement or an empty string. Heap entries are mutated only after removal.
9. [Find Median from Data Stream](09-FindMedianFromDataStream.java) — MedianFinder is the required design class. The average promotes to long before addition to prevent int overflow.
10. [Smallest Range Covering Elements from K Lists](10-SmallestRangeCoveringElementsFromKLists.java) — Solution compares widths as long and breaks equal-width ties by smaller left endpoint; lists are unchanged.

