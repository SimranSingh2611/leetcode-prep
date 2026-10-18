# Java Solutions — 13 — Binary Search Trees

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Search in a Binary Search Tree](01-SearchBST.java) · [LeetCode](https://leetcode.com/problems/search-in-a-binary-search-tree/) — Solution iterative; no mutation.
2. [Range Sum of BST](02-RangeSumBST.java) · [LeetCode](https://leetcode.com/problems/range-sum-of-bst/) — Solution; no mutation.
3. [Convert Sorted Array to Binary Search Tree](03-SortedArrayToBST.java) · [LeetCode](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/) — Solution preserves the input; multiple valid balanced outputs exist.
4. [Validate Binary Search Tree](04-ValidateBST.java) · [LeetCode](https://leetcode.com/problems/validate-binary-search-tree/) · [NeetCode 150](https://neetcode.io/problems/valid-binary-search-tree/question?list=neetcode150) — Solution iterative; no mutation.
5. [Kth Smallest Element in a BST](05-KthSmallestBST.java) · [LeetCode](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) · [NeetCode 150](https://neetcode.io/problems/kth-smallest-integer-in-bst/question?list=neetcode150) — Solution; k is valid; no mutation.
6. [Lowest Common Ancestor of a Binary Search Tree](06-LowestCommonAncestorBST.java) · [LeetCode](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) · [NeetCode 150](https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree/question?list=neetcode150) — Solution; distinct target nodes are guaranteed present.
7. [Delete Node in a BST](07-DeleteNodeBST.java) · [LeetCode](https://leetcode.com/problems/delete-node-in-a-bst/) — Solution mutates values and links.
8. [Binary Search Tree Iterator](08-BSTIterator.java) · [LeetCode](https://leetcode.com/problems/binary-search-tree-iterator/) — BSTIterator design class; assumes no concurrent tree mutation.
9. [Maximum Sum BST in Binary Tree](09-MaximumSumBSTSubtree.java) · [LeetCode](https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/) — Solution iterative postorder; general-tree input, BST-subtree objective.
10. [Number of Ways to Reorder Array to Get Same BST](10-ReorderToSameBST.java) · [LeetCode](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/) — Solution; modular combinatorics stretch, input preserved.

