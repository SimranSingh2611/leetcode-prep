# 13 — Binary Search Trees

BSTs are trees with an ordering contract. Use that contract to direct search, prune ranges, and interpret inorder traversal as sorted order. A BST is not automatically balanced.

## How to recognize this pattern

**Keywords / hints:** “binary search tree”, “inorder sorted”, “kth smallest”, “predecessor/successor”, “insert/delete a key”, “validate ordering”.

**Structural test:** Is every left-subtree value smaller and every right-subtree value larger under the problem's duplicate policy? If yes, can a comparison eliminate an entire subtree?

**Invariant / mental model:** Ordering applies to whole ancestor-imposed ranges, not just immediate parent/child pairs. Operations are O(h), which is O(n) on a skewed unbalanced tree.

**When not to use it:** Do not use value-directed pruning on a general binary tree. Do not claim O(log n) unless balance or expected random shape is justified.

**Prerequisites:** 12-binary-trees and binary search. The last problem additionally needs modular combinatorics.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Comparing a target with the node decides which subtree can contain it.

**Solution:** Walk left for smaller targets and right for larger ones until finding the key or null. Return the matching node, which is the root of the requested subtree.

**Complexity:** O(h) time, O(1) auxiliary space.

**Java:** [SearchBST](./java/01-SearchBST.java) — Solution iterative; no mutation.

**Potential interviewer follow-ups:**

- When is O(h) logarithmic? Only for a balanced or suitably bounded-height tree.
- What if duplicate keys are allowed? Define their placement and which occurrence is requested.

## 2. [Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** An inclusive numeric range lets you discard subtrees entirely.

**Solution:** If a node is below low, its left subtree is also too small; visit only right. If above high, visit only left. Otherwise include it and explore both children.

**Complexity:** O(n) worst-case time, O(h) call stack; pruning may visit fewer nodes.

**Java:** [RangeSumBST](./java/02-RangeSumBST.java) — Solution; no mutation.

**Potential interviewer follow-ups:**

- Why not always traverse everything? The BST order can exclude entire ranges.
- For many sum queries with updates? Augment nodes with subtree sums or use a balanced indexed structure.

## 3. [Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Sorted values can be partitioned around a median to guarantee balanced subtree sizes.

**Solution:** Choose the middle value as root and recursively build from the left/right index ranges. Index boundaries avoid copying subarrays. Repeated median choices keep height logarithmic.

**Complexity:** O(n) time, O(log n) call stack, plus O(n) output nodes.

**Java:** [SortedArrayToBST](./java/03-SortedArrayToBST.java) — Solution preserves the input; multiple valid balanced outputs exist.

**Potential interviewer follow-ups:**

- Why not repeatedly insert sorted values? It builds a skewed tree.
- If input is a linked list? Random midpoint access is expensive; use inorder simulation after counting nodes.

## 4. [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Every node must satisfy all ancestor bounds; checking only its children is insufficient.

**Solution:** An inorder traversal of a strict BST is strictly increasing. Iterate with a stack and reject any value<=the previous value. Store the previous value as long to handle Integer.MIN_VALUE without sentinel collision.

**Complexity:** O(n) time, O(h) auxiliary stack.

**Java:** [ValidateBST](./java/04-ValidateBST.java) — Solution iterative; no mutation.

**Potential interviewer follow-ups:**

- What is the recursive alternative? Pass open lower/upper bounds downward.
- Why reject equal values? This problem requires strict ordering, even if another BST convention permits duplicates.

## 5. [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Inorder visits BST values in rank order, so you can stop after k visits.

**Solution:** Perform iterative inorder traversal and decrement k whenever popping a node. Return when it becomes zero. Do not materialize the full sorted traversal when only one rank is needed.

**Complexity:** O(h+k) time, O(h) auxiliary stack.

**Java:** [KthSmallestBST](./java/05-KthSmallestBST.java) — Solution; k is valid; no mutation.

**Potential interviewer follow-ups:**

- For frequent rank queries with updates? Maintain subtree sizes in a balanced BST.
- Why doesn't a plain BST guarantee O(log n) selection? Its height may be linear.

## 6. [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Both target values on one side imply their ancestor must be on that side.

**Solution:** Walk left if both values are smaller and right if both are larger. Otherwise the current node is their split point or one target itself. Unlike general-tree LCA, no traversal of both subtrees is needed.

**Complexity:** O(h) time, O(1) auxiliary space.

**Java:** [LowestCommonAncestorBST](./java/06-LowestCommonAncestorBST.java) — Solution; distinct target nodes are guaranteed present.

**Potential interviewer follow-ups:**

- What if one target is an ancestor of the other? The equality case returns it.
- How does this differ from folder 12's LCA? It uses BST ordering to eliminate a branch.

## 7. [Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Delete a key while preserving whole-subtree ordering.

**Solution:** Search recursively. A node with zero or one child is replaced by that child. For two children, copy the inorder successor's value from the right subtree, then delete that successor from the right subtree. Its position has at most one child.

**Complexity:** O(h) time and O(h) stack.

**Java:** [DeleteNodeBST](./java/07-DeleteNodeBST.java) — Solution mutates values and links.

**Potential interviewer follow-ups:**

- Could you use the inorder predecessor instead? Yes, symmetrically from the left subtree.
- What extra work does a balanced BST need? Restore its balance invariant after structural deletion.

## 8. [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Produce sorted values lazily instead of storing a full traversal.

**Solution:** Keep the left spine of the remaining traversal in a stack. next pops its smallest pending node, then pushes the left spine of that node's right subtree. Every node is pushed and popped once across the entire iteration.

**Complexity:** Constructor O(h); next O(1) amortized, O(h) worst case; hasNext O(1); O(h) space.

**Java:** [BSTIterator](./java/08-BSTIterator.java) — BSTIterator design class; assumes no concurrent tree mutation.

**Potential interviewer follow-ups:**

- Why is next amortized rather than worst-case O(1)? One call may push a long left spine.
- If a previous operation is required? Keep traversal history or use a richer bidirectional iterator design.

## 9. [Maximum Sum BST in Binary Tree](https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Every subtree needs both a validity summary and numeric extrema before its parent can be judged.

**Solution:** Process nodes postorder. A subtree is a BST iff both children are BSTs and leftMax<node<rightMin. Store valid/min/max/sum for each subtree and maximize valid sums. Empty selection is allowed, so initialize best to zero. An explicit traversal avoids deep Java recursion.

**Complexity:** O(n) expected time; O(n) auxiliary storage for summaries and traversal.

**Java:** [MaximumSumBSTSubtree](./java/09-MaximumSumBSTSubtree.java) — Solution iterative postorder; general-tree input, BST-subtree objective.

**Potential interviewer follow-ups:**

- Why is checking child root values insufficient? A deeper descendant may violate an ancestor bound.
- Can space become O(h)? Recursive summaries can do that, but a skewed input risks stack overflow.

## 10. [Number of Ways to Reorder Array to Get Same BST](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Keep left/right insertion orders while freely interleaving the two groups.

**Solution:** The first value fixes the root. Partition remaining values into left and right groups. Their relative internal orders must each produce the same subtree; choose which L of the L+R positions belong to the left, multiplying a binomial coefficient by recursive subtree counts. Subtract the original ordering at the end.

**Complexity:** O(n²) time and O(n²) space for binomial coefficients and worst-case retained partitions; recursion depth O(h).

**Java:** [ReorderToSameBST](./java/10-ReorderToSameBST.java) — Solution; modular combinatorics stretch, input preserved.

**Potential interviewer follow-ups:**

- Why can left and right insertions interleave freely? They never affect each other's subtree structure.
- Why subtract one? The question excludes the original input ordering.

