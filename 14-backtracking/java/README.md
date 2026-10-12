# Java Solutions — 14 — Backtracking

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Binary Watch](01-BinaryWatch.java) — Solution uses choose/recurse without mutable shared path state.
2. [Sum of All Subset XOR Totals](02-SumOfAllSubsetXORTotals.java) — Solution teaches backtracking; BitContributionSolution is the faster follow-up. Neither mutates nums.
3. [Binary Tree Paths](03-BinaryTreePaths.java) — Solution restores its path buffer and does not mutate the tree.
4. [Subsets](04-Subsets.java) — Solution leaves nums unchanged and saves independent list copies.
5. [Permutations](05-Permutations.java) — Solution uses a used-index array and does not mutate nums.
6. [Combination Sum](06-CombinationSum.java) — Solution sorts a clone and leaves the caller's candidates unchanged.
7. [Palindrome Partitioning](07-PalindromePartitioning.java) — Solution uses a precomputed palindrome table; substring objects are copied into saved partitions.
8. [Word Search](08-WordSearch.java) — Solution temporarily mutates board but restores every cell before returning.
9. [N-Queens](09-NQueens.java) — Solution stores column choices and materializes boards only at leaves.
10. [Sudoku Solver](10-SudokuSolver.java) — Solution fills board in place; assumes the problem's valid puzzle with a unique solution.

