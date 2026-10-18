# Java Solutions — 16 — Greedy and Intervals

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Assign Cookies](01-AssignCookies.java) · [LeetCode](https://leetcode.com/problems/assign-cookies/) — Solution preserves both arrays; sorting them in place would remove the clone storage.
2. [Lemonade Change](02-LemonadeChange.java) · [LeetCode](https://leetcode.com/problems/lemonade-change/) — Solution assumes the allowed bill denominations 5, 10, and 20 and does not mutate input.
3. [Longest Palindrome](03-LongestPalindrome.java) · [LeetCode](https://leetcode.com/problems/longest-palindrome/) — Solution uses a fixed ASCII frequency array; this is not longest palindromic substring or subsequence.
4. [Jump Game](04-JumpGame.java) · [LeetCode](https://leetcode.com/problems/jump-game/) · [NeetCode 150](https://neetcode.io/problems/jump-game/question?list=neetcode150) — Solution leaves nums unchanged; the problem's bounds keep index sums within int.
5. [Jump Game II](05-JumpGameII.java) · [LeetCode](https://leetcode.com/problems/jump-game-ii/) · [NeetCode 150](https://neetcode.io/problems/jump-game-ii/question?list=neetcode150) — Solution assumes LeetCode's guarantee that the last index is reachable.
6. [Gas Station](06-GasStation.java) · [LeetCode](https://leetcode.com/problems/gas-station/) · [NeetCode 150](https://neetcode.io/problems/gas-station/question?list=neetcode150) — Solution uses long balances and leaves gas/cost unchanged.
7. [Partition Labels](07-PartitionLabels.java) · [LeetCode](https://leetcode.com/problems/partition-labels/) · [NeetCode 150](https://neetcode.io/problems/partition-labels/question?list=neetcode150) — Solution preserves s and returns partition lengths.
8. [Insert Interval](08-InsertInterval.java) · [LeetCode](https://leetcode.com/problems/insert-interval/) · [NeetCode 150](https://neetcode.io/problems/insert-new-interval/question?list=neetcode150) — Solution does not mutate either input and copies emitted intervals to avoid output/input aliasing.
9. [Candy](09-Candy.java) · [LeetCode](https://leetcode.com/problems/candy/) — Solution preserves ratings. A constant-space slope-counting variant is a follow-up, not needed for the clear two-pass proof.
10. [Minimum Interval to Include Each Query](10-MinimumIntervalToIncludeEachQuery.java) · [LeetCode](https://leetcode.com/problems/minimum-interval-to-include-each-query/) · [NeetCode 150](https://neetcode.io/problems/minimum-interval-including-query/question?list=neetcode150) — Solution clones the interval outer array, never edits its rows, and preserves query order in the returned answers. Requires heaps from 15.

