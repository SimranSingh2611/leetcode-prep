# Java Solutions — 12 — Binary Trees

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Invert Binary Tree](01-InvertBinaryTree.java) — Solution mutates the tree.
2. [Maximum Depth of Binary Tree](02-MaximumDepthBinaryTree.java) — Solution recursive; BreadthFirstSolution avoids stack-depth risk, not always less memory.
3. [Same Tree](03-SameTree.java) — Solution; input preserved.
4. [Binary Tree Level Order Traversal](04-BinaryTreeLevelOrder.java) — Solution; input preserved.
5. [Binary Tree Right Side View](05-BinaryTreeRightSideView.java) — Solution; input preserved.
6. [Construct Binary Tree from Preorder and Inorder Traversal](06-BuildTreePreorderInorder.java) — Solution; inputs preserved.
7. [Lowest Common Ancestor of a Binary Tree](07-LowestCommonAncestorBinaryTree.java) — Solution; compare node references, not values.
8. [Count Good Nodes in Binary Tree](08-CountGoodNodes.java) — Solution; input preserved.
9. [Binary Tree Maximum Path Sum](09-BinaryTreeMaximumPathSum.java) — Solution resets state on every call; tree/Kadane-style hybrid.
10. [Serialize and Deserialize Binary Tree](10-SerializeDeserializeBinaryTree.java) — Codec design class; supports empty trees and negative values.
11. [Diameter of Binary Tree](11-DiameterBinaryTree.java) — Solution preserves input and resets state on every call.
12. [Balanced Binary Tree](12-BalancedBinaryTree.java) — Solution; input preserved.
13. [Subtree of Another Tree](13-SubtreeOfAnotherTree.java) — Solution; both inputs preserved.
14. [Symmetric Tree](14-SymmetricTree.java) — Solution; input preserved.
15. [Path Sum](15-PathSum.java) — Solution; only root-to-leaf paths qualify; input preserved.
16. [Binary Tree Zigzag Level Order Traversal](16-BinaryTreeZigzagLevelOrder.java) — Solution; input preserved; alternating output placement.
17. [Path Sum II](17-PathSumII.java) — Solution; input preserved; copies each matching path.
18. [Path Sum III](18-PathSumIII.java) — Solution; long prefix sums and ancestor-only frequencies; input preserved.
19. [Flatten Binary Tree to Linked List](19-FlattenBinaryTree.java) — Solution rewires original nodes in place with O(1) auxiliary space.
20. [Maximum Width of Binary Tree](20-MaximumWidthBinaryTree.java) — Solution; normalized long indices; input preserved.

[NeetCode 150 coverage and practice order](../README.md#neetcode-150-coverage-first)
