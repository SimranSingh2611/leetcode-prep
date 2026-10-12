# 12 — Binary Trees

Treat a tree as linked subproblems, without assuming its values are ordered. DFS returns subtree information; BFS processes a frontier one level at a time. Queue basics are introduced here.

## How to recognize this pattern

**Keywords / hints:** “root/left/right”, “height”, “depth”, “root-to-leaf”, “subtree”, “level order”, “ancestor”, “path through a node”.

**Structural test:** Can a node's answer be computed from its children, or does the task need all nodes at the same distance from the root together?

**Invariant / mental model:** A recursive helper must define what it returns to its parent. A path returned upward may use only one child even when the best complete path uses both. BFS snapshots the current level size.

**When not to use it:** Do not prune by comparing values unless a BST invariant is guaranteed. General graphs may have cycles and need visited-state tracking.

**Prerequisites:** Recursion and pointer identity; stacks and queue FIFO operations. BST-specific problems follow in 13.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

20 problems: 8 Easy / 10 Medium / 2 Hard. The original 1–10 keep their numbering; 11–13 complete the general-tree NeetCode 150 coverage, and 14–20 add further practice. For a core-first pass, follow the coverage table below, then solve 7 and 14–20. Study Diameter (11) before Maximum Path Sum (9). Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## NeetCode 150 coverage first

The [NeetCode 150 Trees category](https://neetcode.io/practice/practice/neetcode150) has 15 problems: 12 general-tree problems covered here and 3 BST-specific problems already covered in folder 13. Follow all 15 rows for complete category coverage. BST solutions stay in their existing folder, with direct links here.

| NeetCode 150 problem | Study location |
| --- | --- |
| Invert Binary Tree | [1 — Java](./java/01-InvertBinaryTree.java) |
| Maximum Depth of Binary Tree | [2 — Java](./java/02-MaximumDepthBinaryTree.java) |
| Diameter of Binary Tree | [11 — Java](./java/11-DiameterBinaryTree.java) |
| Balanced Binary Tree | [12 — Java](./java/12-BalancedBinaryTree.java) |
| Same Tree | [3 — Java](./java/03-SameTree.java) |
| Subtree of Another Tree | [13 — Java](./java/13-SubtreeOfAnotherTree.java) |
| Lowest Common Ancestor of a Binary Search Tree | [13-BSTs, problem 6](../13-binary-search-trees/java/06-LowestCommonAncestorBST.java) |
| Binary Tree Level Order Traversal | [4 — Java](./java/04-BinaryTreeLevelOrder.java) |
| Binary Tree Right Side View | [5 — Java](./java/05-BinaryTreeRightSideView.java) |
| Count Good Nodes in Binary Tree | [8 — Java](./java/08-CountGoodNodes.java) |
| Validate Binary Search Tree | [13-BSTs, problem 4](../13-binary-search-trees/java/04-ValidateBST.java) |
| Kth Smallest Element in a BST | [13-BSTs, problem 5](../13-binary-search-trees/java/05-KthSmallestBST.java) |
| Construct Binary Tree from Preorder and Inorder Traversal | [6 — Java](./java/06-BuildTreePreorderInorder.java) |
| Binary Tree Maximum Path Sum | [9 — Java](./java/09-BinaryTreeMaximumPathSum.java) |
| Serialize and Deserialize Binary Tree | [10 — Java](./java/10-SerializeDeserializeBinaryTree.java) |

The additions' LeetCode IDs, titles, and difficulties were checked against their linked problem statements on 2026-09-12. Supplemental selections extend paired recursion, BFS output order, root-to-leaf paths, prefix sums with backtracking, pointer rewiring, and positional indexing. Path Sum II builds on Path Sum; Path Sum III also uses the prefix-sum technique from folder 02.

## 1. [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Apply the same left/right transformation to every subtree.

**Solution:** Recursively invert both children, then swap their returned roots. Null is the base case. Each original node is visited once.

**Complexity:** O(n) time; O(h) call stack for height h.

**Java:** [InvertBinaryTree](./java/01-InvertBinaryTree.java) — Solution mutates the tree.

**Potential interviewer follow-ups:**

- Can this be iterative? Process nodes with a stack or queue and swap children.
- How would you preserve input? Allocate a mirrored copy rather than rewiring original nodes.

## 2. [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A node's depth contribution depends on the deeper child subtree.

**Solution:** Return zero for null, otherwise one plus the greater child depth. The iterative alternative counts BFS levels and avoids recursion, but may store a wide frontier.

**Complexity:** O(n) time for both; DFS O(h) stack, BFS O(w) queue for maximum width w.

**Java:** [MaximumDepthBinaryTree](./java/02-MaximumDepthBinaryTree.java) — Solution recursive; BreadthFirstSolution avoids stack-depth risk, not always less memory.

**Potential interviewer follow-ups:**

- Why is a null subtree depth zero? It makes a leaf return one naturally.
- Which uses less memory on a chain versus a complete tree? BFS favors chains; DFS favors wide balanced trees.

## 3. [Same Tree](https://leetcode.com/problems/same-tree/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Both structure and corresponding values must match.

**Solution:** Two null nodes match; exactly one null does not. Otherwise require equal values and recursively matching left and right children. A value traversal alone can lose structure.

**Complexity:** O(n) worst-case time over compared nodes; O(h) stack.

**Java:** [SameTree](./java/03-SameTree.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why are equal inorder sequences insufficient? Different tree shapes can have the same inorder values.
- How would you compare iteratively? Queue pairs of corresponding nodes, preserving null-position checks.

## 4. [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Output must group nodes by distance from the root.

**Solution:** Use a queue. At each level, snapshot its current size and remove exactly that many nodes, appending their children for the next level. Without the snapshot, newly added children leak into the current level.

**Complexity:** O(n) time; O(w) auxiliary queue plus O(n) output.

**Java:** [BinaryTreeLevelOrder](./java/04-BinaryTreeLevelOrder.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- How would you zigzag levels? Reverse placement order within alternating output levels.
- For bottom-up output? Reverse the resulting level list, without changing traversal.

## 5. [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Only the rightmost visible node at each depth is needed.

**Solution:** Visit right before left in DFS. The first node encountered at a depth is the visible one; append only when depth equals the current output size. Later left-side visits fill only depths not already seen.

**Complexity:** O(n) time; O(h) stack and O(h) output.

**Java:** [BinaryTreeRightSideView](./java/05-BinaryTreeRightSideView.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Could BFS solve it? Keep the final node from each left-to-right level.
- What changes for the left view? Reverse the child visitation priority.

## 6. [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** One traversal identifies each subtree root; another identifies its left/right partition.

**Solution:** Preorder's first element is the root. Its inorder position divides the subtree into left/right sizes, which identify the corresponding preorder ranges. Map values to inorder indices to avoid repeated scans; values are unique.

**Complexity:** O(n) expected time; O(n) index map plus O(h) stack, excluding the output tree.

**Java:** [BuildTreePreorderInorder](./java/06-BuildTreePreorderInorder.java) — Solution; inputs preserved.

**Potential interviewer follow-ups:**

- Why don't preorder and postorder always determine one tree? Single-child orientation can be ambiguous.
- What if values repeat? A simple value-to-index map no longer identifies the partition uniquely.

## 7. [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Locate the lowest subtree containing both target node identities, without ordered values.

**Solution:** Return immediately at null or either target. Recurse into both children. If both return nonnull, the current node is the split point; otherwise propagate the nonnull result. This contract relies on both targets being present, as guaranteed.

**Complexity:** O(n) time; O(h) stack.

**Java:** [LowestCommonAncestorBinaryTree](./java/07-LowestCommonAncestorBinaryTree.java) — Solution; compare node references, not values.

**Potential interviewer follow-ups:**

- If one target might be absent? Return a found-count along with the candidate ancestor.
- Why can't you use the BST value-directed method? A general binary tree has no ordering guarantee.

## 8. [Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Each node is compared with the maximum along its own root-to-node path.

**Solution:** Pass a path maximum downward. Count the node if its value is at least that maximum, update it for the children, and sum their counts. Sibling branches receive independent primitive copies of the path state.

**Complexity:** O(n) time; O(h) stack.

**Java:** [CountGoodNodes](./java/08-CountGoodNodes.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why not one mutable global maximum? A large value in one branch must not affect its sibling.
- If you needed every good path? Maintain and copy/backtrack a path list when emitting results.

## 9. [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) — Hard

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A best complete path may join two branches, but a path extended to the parent cannot branch.

**Solution:** Return the best nonnegative one-sided gain from each child. Update a global best with node+leftGain+rightGain, then return node+max(leftGain,rightGain). Initialize the global best to the minimum integer so an all-negative tree still chooses a nonempty path.

**Complexity:** O(n) time; O(h) stack.

**Java:** [BinaryTreeMaximumPathSum](./java/09-BinaryTreeMaximumPathSum.java) — Solution resets state on every call; tree/Kadane-style hybrid.

**Potential interviewer follow-ups:**

- Why not return both gains to the parent? That would create a branching shape rather than one path.
- How would you recover the actual path? Retain chosen branch information when updating the winning split.

## 10. [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) — Hard

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A reversible representation must preserve null child positions, not just values.

**Solution:** Encode in BFS order, emitting each node's left/right values or # for missing children. Decode using a queue of parents awaiting children. Explicit null markers make the structure unambiguous. Iterative processing avoids call-stack limits on long chains.

**Complexity:** O(n) time and O(n) serialized/token storage; queue O(w), excluding reconstructed tree.

**Java:** [SerializeDeserializeBinaryTree](./java/10-SerializeDeserializeBinaryTree.java) — Codec design class; supports empty trees and negative values.

**Potential interviewer follow-ups:**

- Why aren't values alone enough? They do not identify missing children.
- Could preorder work? Yes, with null markers; choose iterative traversal if depth may be large.

## 11. [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) — Easy

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Find the longest path between any two nodes, measured in edges; it need not pass through the root.

**Solution:** In postorder, return each subtree's height in nodes. At every node, leftHeight + rightHeight is the longest path through that node in edges. Update the overall diameter, then return only 1 + max(leftHeight, rightHeight) to the parent. This separates a complete path from an extendable branch.

**Complexity:** O(n) time; O(h) call stack for height h.

**Java:** [DiameterBinaryTree](./java/11-DiameterBinaryTree.java) — Solution preserves input and resets its diameter on every call.

**Potential interviewer follow-ups:**

- Why check every node rather than just the root? The longest path may lie entirely inside a child subtree.
- How does this lead to Maximum Path Sum? Replace heights with value gains, discard negative child gains, and keep the one-branch return contract.

## 12. [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) — Easy

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** The two child heights must differ by at most one at every node.

**Solution:** Return a nonnegative height for a balanced subtree and -1 for an unbalanced one. Propagate -1 immediately. Otherwise compare the two child heights before returning the current height. Computing height and checking balance in the same postorder traversal avoids repeated subtree scans.

**Complexity:** O(n) time; O(h) call stack.

**Java:** [BalancedBinaryTree](./java/12-BalancedBinaryTree.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why is checking only the root insufficient? Equally tall children can hide an imbalance deeper down.
- What is the cost of recomputing heights at every node? O(n²) in the worst case; the sentinel helper reuses each returned height.

## 13. [Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A candidate must match both values and the entire structure from some node down through all descendants.

**Solution:** Try each node in the larger tree as a candidate root. At each candidate, use Same Tree's paired recursion to compare both subtrees exactly, including null positions. If the match fails, search the candidate's children using the original subRoot.

**Complexity:** O(nm) worst-case time for n and m nodes; O(hRoot + hSubRoot) stack is a safe bound.

**Java:** [SubtreeOfAnotherTree](./java/13-SubtreeOfAnotherTree.java) — Solution; both inputs preserved. Also treats an empty subRoot as a match, beyond the judge's nonempty-input contract.

**Potential interviewer follow-ups:**

- Why can repeated values be expensive? Many candidate roots can match long prefixes before a structural mismatch.
- Can you achieve linear time? Serialize preorder into unambiguous value/null tokens and use KMP in O(n + m) time and storage; plain substring scans do not guarantee that bound.

## 14. [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Opposite sides must be mirrors in both values and shape.

**Solution:** Compare the root's left and right children as a pair. Values must match, the first node's left child must mirror the second's right child, and vice versa. Two nulls match; exactly one null breaks symmetry.

**Complexity:** O(n) time; O(h) call stack.

**Java:** [SymmetricTree](./java/14-SymmetricTree.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- How does this differ from Same Tree? Same Tree compares children on the same side; symmetry compares opposite sides.
- How would you implement it iteratively? Queue mirror pairs, retaining null positions inside each pair; ArrayDeque cannot directly store null elements.

## 15. [Path Sum](https://leetcode.com/problems/path-sum/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Determine whether a root-to-leaf path reaches a target sum.

**Solution:** Pass the remaining target down each branch, subtracting the current value. Accept only at a leaf when its value equals the remaining target. Null returns false, so an empty tree or an internal-node prefix cannot accidentally satisfy the request.

**Complexity:** O(n) time; O(h) call stack. Integer arithmetic is sufficient under this problem's value and node-count bounds.

**Java:** [PathSum](./java/15-PathSum.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Can you stop when the remaining target becomes negative? No; negative node values can bring the path back to the target.
- What changes if all matching paths are required? Maintain a path list, copy it at matching leaves, and backtrack as in Path Sum II.

## 16. [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Output is grouped by depth, but its direction alternates between levels.

**Solution:** Use ordinary left-to-right BFS and snapshot each level's size. Write each removed node into index i or size - 1 - i of that level's output array. Toggle placement direction after the level; child enqueue order stays fixed.

**Complexity:** O(n) time; O(w) auxiliary queue for maximum actual level width w, plus O(n) output.

**Java:** [BinaryTreeZigzagLevelOrder](./java/16-BinaryTreeZigzagLevelOrder.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why not insert at the front of an ArrayList on reversed levels? Repeated shifting can make a wide level quadratic; indexed placement avoids that.
- Could you reverse alternating completed level lists? Yes; each node participates in at most one reversal, preserving O(n) total time.

## 17. [Path Sum II](https://leetcode.com/problems/path-sum-ii/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Enumerate all root-to-leaf paths with the requested sum.

**Solution:** Append the current value to one mutable path, recurse with the reduced target, and remove that value before returning. At a matching leaf, append a copy of the path to the result. This makes sibling exploration independent and prevents later mutations from corrupting saved answers.

**Complexity:** O(n + L) time, where L is the total number of values copied into output paths; O(h) auxiliary path/stack plus O(L) output. L can be O(n²).

**Java:** [PathSumII](./java/17-PathSumII.java) — Solution; input preserved and each result owns its path list.

**Potential interviewer follow-ups:**

- Why is output copying part of the complexity? Many matching leaves can share a long prefix that must appear separately in every returned list.
- If only the number of root-to-leaf matches is needed? Return counts from children and eliminate both path storage and output copying.

## 18. [Path Sum III](https://leetcode.com/problems/path-sum-iii/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Count downward paths that may start or end at any node, rather than only at the root and leaves.

**Solution:** Track the current root-to-node prefix sum and frequencies of ancestor prefix sums. A previous prefix equal to currentSum - target identifies a matching path ending here. Seed zero once, query before inserting the current prefix, recurse, then decrement/remove that prefix on return so siblings never share path history. Use long sums because node values can reach magnitude 10⁹.

**Complexity:** O(n) expected time; O(h) active prefix-map entries and recursion stack. Removing zero-frequency entries keeps the map bounded by the current path.

**Java:** [PathSumIII](./java/18-PathSumIII.java) — Solution; input preserved and all state is local to each public call.

**Potential interviewer follow-ups:**

- Why store frequencies instead of a set? Zero and negative values can produce repeated prefix sums, each representing a different valid starting point.
- What does forgetting the decrement do? Prefixes from a finished sibling can be counted as ancestors, creating nonexistent paths.

## 19. [Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Reuse the same TreeNode objects to make a preorder chain through right pointers, with every left pointer null.

**Solution:** When a node has a left child, find the rightmost node along that child's right spine. Attach the old right subtree there, move the left subtree to the right, and clear the left link. Advance right and repeat. The deferred old right subtree remains after the moved left subtree as later rewiring completes preorder.

**Complexity:** O(n) amortized time; O(1) auxiliary space. Predecessor searches traverse disjoint original right-spine segments overall, so the nested loop does not imply O(n²).

**Java:** [FlattenBinaryTree](./java/19-FlattenBinaryTree.java) — Solution mutates original links; creates no replacement nodes and uses no recursion.

**Potential interviewer follow-ups:**

- What is the simpler stack alternative? Push right before left, link consecutive preorder pops, and clear left links; this uses O(h) auxiliary space.
- How would you test the in-place requirement? Save original preorder node references, then check reference identity, null left pointers, and a null final right pointer.

## 20. [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Width includes missing positions between the outermost nodes at a depth, not just the number of present nodes.

**Solution:** BFS actual nodes with conceptual complete-tree indices: children of index i use 2i and 2i + 1. Subtract the first index at each level before deriving child indices. The last normalized index plus one gives that level's width. Normalization preserves gaps while preventing indices from growing just because a narrow tree is deep; long handles intermediate doubling under the signed-int answer guarantee.

**Complexity:** O(n) time; O(w) queue, where w counts actual nodes on the widest occupied level, excluding virtual null slots.

**Java:** [MaximumWidthBinaryTree](./java/20-MaximumWidthBinaryTree.java) — Solution; input preserved, normalized long indices, signed-int result as guaranteed by the problem.

**Potential interviewer follow-ups:**

- Why is queue size insufficient? Two nodes can enclose many missing positions in the conceptual complete tree.
- Why normalize even when using long? An unnormalized chain can overflow long after enough levels despite having width one.
