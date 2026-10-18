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

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/invert-a-binary-tree/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Apply the same left/right transformation to every subtree.

**Talking points:**

- I'd invert the two child subtrees recursively and swap their returned roots.
- Null is the base case, and each node's swap completes its part of the transformation.

**Solution:** Recursively invert both children, then swap their returned roots. Null is the base case. Each original node is visited once.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`; `O(n)` on a chain.

**Java:** [InvertBinaryTree](./java/01-InvertBinaryTree.java) — Solution mutates the tree.

**Potential interviewer follow-ups:**

- Can this be iterative? Process nodes with a stack or queue and swap children.
- How would you preserve input? Allocate a mirrored copy rather than rewiring original nodes.

## 2. [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/depth-of-binary-tree/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A node's depth contribution depends on the deeper child subtree.

**Talking points:**

- I'd return zero for null and one plus the larger child depth otherwise.
- Each call summarizes its subtree; BFS is an alternative that counts complete levels.

**Solution:** Return zero for null, otherwise one plus the greater child depth. The iterative alternative counts BFS levels and avoids recursion, but may store a wide frontier.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes, for both versions.

**Space complexity:** Primary DFS: `O(h)` stack; BFS alternative: `O(w)` queue for maximum
actual level width `w`.

**Java:** [MaximumDepthBinaryTree](./java/02-MaximumDepthBinaryTree.java) — Solution recursive; BreadthFirstSolution avoids stack-depth risk, not always less memory.

**Potential interviewer follow-ups:**

- Why is a null subtree depth zero? It makes a leaf return one naturally.
- Which uses less memory on a chain versus a complete tree? BFS favors chains; DFS favors wide balanced trees.

## 3. [Same Tree](https://leetcode.com/problems/same-tree/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/same-binary-tree/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Both structure and corresponding values must match.

**Talking points:**

- I'd compare corresponding nodes, requiring both their values and structures to match.
- Two nulls match, one null fails, and matching nodes recurse on both child pairs.

**Solution:** Two null nodes match; exactly one null does not. Otherwise require equal values and recursively matching left and right children. A value traversal alone can lose structure.

**Complexity:**

**Time complexity:** `O(n)` worst case for `n` compared node positions.

**Space complexity:** `O(h)` recursive stack for the maximum compared depth.

**Java:** [SameTree](./java/03-SameTree.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why are equal inorder sequences insufficient? Different tree shapes can have the same inorder values.
- How would you compare iteratively? Queue pairs of corresponding nodes, preserving null-position checks.

## 4. [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/level-order-traversal-of-binary-tree/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Output must group nodes by distance from the root.

**Talking points:**

- I'd process the tree with a queue and freeze its size at the start of each level.
- Removing exactly that many nodes keeps newly enqueued children in the next result row.

**Solution:** Use a queue. At each level, snapshot its current size and remove exactly that many nodes, appending their children for the next level. Without the snapshot, newly added children leak into the current level.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(w)` auxiliary queue for maximum level width `w`, plus `O(n)` output.

**Java:** [BinaryTreeLevelOrder](./java/04-BinaryTreeLevelOrder.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- How would you zigzag levels? Reverse placement order within alternating output levels.
- For bottom-up output? Reverse the resulting level list, without changing traversal.

## 5. [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/binary-tree-right-side-view/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Only the rightmost visible node at each depth is needed.

**Talking points:**

- I'd visit right children before left children in DFS.
- The first node reached at each depth is the visible one, so I'd record only previously unseen
  depths.

**Solution:** Visit right before left in DFS. The first node encountered at a depth is the visible one; append only when depth equals the current output size. Later left-side visits fill only depths not already seen.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` auxiliary stack and `O(h)` output for tree height `h`.

**Java:** [BinaryTreeRightSideView](./java/05-BinaryTreeRightSideView.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Could BFS solve it? Keep the final node from each left-to-right level.
- What changes for the left view? Reverse the child visitation priority.

## 6. [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** One traversal identifies each subtree root; another identifies its left/right partition.

**Talking points:**

- Preorder picks the root, and its inorder index divides the left and right subtrees.
- I'd map values to inorder positions and recurse on index ranges, avoiding repeated searches or
  copied slices.

**Solution:** Preorder's first element is the root. Its inorder position divides the subtree into left/right sizes, which identify the corresponding preorder ranges. Map values to inorder indices to avoid repeated scans; values are unique.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` nodes.

**Space complexity:** `O(n)` auxiliary index map plus `O(h)` stack, excluding `O(n)` output
nodes.

**Java:** [BuildTreePreorderInorder](./java/06-BuildTreePreorderInorder.java) — Solution; inputs preserved.

**Potential interviewer follow-ups:**

- Why don't preorder and postorder always determine one tree? Single-child orientation can be ambiguous.
- What if values repeat? A simple value-to-index map no longer identifies the partition uniquely.

## 7. [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Locate the lowest subtree containing both target node identities, without ordered values.

**Talking points:**

- I'd let each subtree return a target it contains or the ancestor already found inside it.
- If both child calls return a target, the current node is their split point; otherwise I'd
  propagate the non-null result.

**Solution:** Return immediately at null or either target. Recurse into both children. If both return nonnull, the current node is the split point; otherwise propagate the nonnull result. This contract relies on both targets being present, as guaranteed.

**Complexity:**

**Time complexity:** `O(n)` worst case for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [LowestCommonAncestorBinaryTree](./java/07-LowestCommonAncestorBinaryTree.java) — Solution; compare node references, not values.

**Potential interviewer follow-ups:**

- If one target might be absent? Return a found-count along with the candidate ancestor.
- Why can't you use the BST value-directed method? A general binary tree has no ordering guarantee.

## 8. [Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/count-good-nodes-in-binary-tree/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Each node is compared with the maximum along its own root-to-node path.

**Talking points:**

- I'd pass the largest value seen on the root-to-node path into each call.
- A node is good when its value is at least that maximum, and each child receives the updated
  path state.

**Solution:** Pass a path maximum downward. Count the node if its value is at least that maximum, update it for the children, and sum their counts. Sibling branches receive independent primitive copies of the path state.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [CountGoodNodes](./java/08-CountGoodNodes.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why not one mutable global maximum? A large value in one branch must not affect its sibling.
- If you needed every good path? Maintain and copy/backtrack a path list when emitting results.

## 9. [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/binary-tree-maximum-path-sum/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A best complete path may join two branches, but a path extended to the parent cannot branch.

**Talking points:**

- I'd return one extendable branch gain from each subtree, ignoring negative child gains.
- At each node I'd separately test a complete path using both children; a negative initial best
  preserves all-negative answers.

**Solution:** Return the best nonnegative one-sided gain from each child. Update a global best with node+leftGain+rightGain, then return node+max(leftGain,rightGain). Initialize the global best to the minimum integer so an all-negative tree still chooses a nonempty path.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [BinaryTreeMaximumPathSum](./java/09-BinaryTreeMaximumPathSum.java) — Solution resets state on every call; tree/Kadane-style hybrid.

**Potential interviewer follow-ups:**

- Why not return both gains to the parent? That would create a branching shape rather than one path.
- How would you recover the actual path? Retain chosen branch information when updating the winning split.

## 10. [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/serialize-and-deserialize-binary-tree/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A reversible representation must preserve null child positions, not just values.

**Talking points:**

- I'd serialize nodes in BFS order with explicit markers for missing children.
- The decoder consumes child pairs for queued parents, so both shape and values are
  reconstructed unambiguously.

**Solution:** Encode in BFS order, emitting each node's left/right values or # for missing children. Decode using a queue of parents awaiting children. Explicit null markers make the structure unambiguous. Iterative processing avoids call-stack limits on long chains.

**Complexity:**

**Time complexity:** `O(n)` per operation for `n` nodes with fixed-width integer values.

**Space complexity:** Serialization: `O(w)` queue plus `O(n)` text/buffer; deserialization:
`O(n)` tokens and `O(w)` queue, excluding `O(n)` reconstructed nodes.

**Java:** [SerializeDeserializeBinaryTree](./java/10-SerializeDeserializeBinaryTree.java) — Codec design class; supports empty trees and negative values.

**Potential interviewer follow-ups:**

- Why aren't values alone enough? They do not identify missing children.
- Could preorder work? Yes, with null markers; choose iterative traversal if depth may be large.

## 11. [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/binary-tree-diameter/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Find the longest path between any two nodes, measured in edges; it need not pass through the root.

**Talking points:**

- I'd compute child heights once in postorder.
- Their sum gives the longest path through the current node in edges, while the returned height
  keeps only one extendable branch.

**Solution:** In postorder, return each subtree's height in nodes. At every node, leftHeight + rightHeight is the longest path through that node in edges. Update the overall diameter, then return only 1 + max(leftHeight, rightHeight) to the parent. This separates a complete path from an extendable branch.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [DiameterBinaryTree](./java/11-DiameterBinaryTree.java) — Solution preserves input and resets its diameter on every call.

**Potential interviewer follow-ups:**

- Why check every node rather than just the root? The longest path may lie entirely inside a child subtree.
- How does this lead to Maximum Path Sum? Replace heights with value gains, discard negative child gains, and keep the one-branch return contract.

## 12. [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/balanced-binary-tree/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** The two child heights must differ by at most one at every node.

**Talking points:**

- I'd return a subtree height when balanced and minus one when unbalanced.
- Combining balance checking with height calculation avoids rescanning subtrees, and the failure
  marker propagates immediately.

**Solution:** Return a nonnegative height for a balanced subtree and -1 for an unbalanced one. Propagate -1 immediately. Otherwise compare the two child heights before returning the current height. Computing height and checking balance in the same postorder traversal avoids repeated subtree scans.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [BalancedBinaryTree](./java/12-BalancedBinaryTree.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why is checking only the root insufficient? Equally tall children can hide an imbalance deeper down.
- What is the cost of recomputing heights at every node? O(n²) in the worst case; the sentinel helper reuses each returned height.

## 13. [Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/subtree-of-a-binary-tree/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A candidate must match both values and the entire structure from some node down through all descendants.

**Talking points:**

- I'd try each node in the larger tree as a possible subtree root.
- An exact paired comparison must match values and null positions, since a matching value
  sequence alone is insufficient.

**Solution:** Try each node in the larger tree as a candidate root. At each candidate, use Same Tree's paired recursion to compare both subtrees exactly, including null positions. If the match fails, search the candidate's children using the original subRoot.

**Complexity:**

**Time complexity:** `O(nm)` worst case for larger-tree size `n` and candidate-tree size `m`.

**Space complexity:** `O(h1 + h2)` is a safe recursive-stack bound for the two tree heights.

**Java:** [SubtreeOfAnotherTree](./java/13-SubtreeOfAnotherTree.java) — Solution; both inputs preserved. Also treats an empty subRoot as a match, beyond the judge's nonempty-input contract.

**Potential interviewer follow-ups:**

- Why can repeated values be expensive? Many candidate roots can match long prefixes before a structural mismatch.
- Can you achieve linear time? Serialize preorder into unambiguous value/null tokens and use KMP in O(n + m) time and storage; plain substring scans do not guarantee that bound.

## 14. [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Opposite sides must be mirrors in both values and shape.

**Talking points:**

- I'd compare the left and right subtrees as mirror pairs.
- Each pair needs equal values, with left matched to right and right matched to left at the next
  level.

**Solution:** Compare the root's left and right children as a pair. Values must match, the first node's left child must mirror the second's right child, and vice versa. Two nulls match; exactly one null breaks symmetry.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [SymmetricTree](./java/14-SymmetricTree.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- How does this differ from Same Tree? Same Tree compares children on the same side; symmetry compares opposite sides.
- How would you implement it iteratively? Queue mirror pairs, retaining null positions inside each pair; ArrayDeque cannot directly store null elements.

## 15. [Path Sum](https://leetcode.com/problems/path-sum/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Determine whether a root-to-leaf path reaches a target sum.

**Talking points:**

- I'd subtract each visited value from the remaining target.
- I'd accept only at a leaf when that remainder is satisfied, so an internal prefix cannot
  produce a false match.

**Solution:** Pass the remaining target down each branch, subtracting the current value. Accept only at a leaf when its value equals the remaining target. Null returns false, so an empty tree or an internal-node prefix cannot accidentally satisfy the request.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(h)` recursive stack for height `h`.

**Java:** [PathSum](./java/15-PathSum.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Can you stop when the remaining target becomes negative? No; negative node values can bring the path back to the target.
- What changes if all matching paths are required? Maintain a path list, copy it at matching leaves, and backtrack as in Path Sum II.

## 16. [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Output is grouped by depth, but its direction alternates between levels.

**Talking points:**

- I'd keep normal BFS traversal order and alternate how values are placed into each level's
  array.
- Writing at index `i` or `size - 1 - i` creates the zigzag without changing child enqueue
  order.

**Solution:** Use ordinary left-to-right BFS and snapshot each level's size. Write each removed node into index i or size - 1 - i of that level's output array. Toggle placement direction after the level; child enqueue order stays fixed.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(w)` auxiliary queue for maximum level width `w`, plus `O(n)` output.

**Java:** [BinaryTreeZigzagLevelOrder](./java/16-BinaryTreeZigzagLevelOrder.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why not insert at the front of an ArrayList on reversed levels? Repeated shifting can make a wide level quadratic; indexed placement avoids that.
- Could you reverse alternating completed level lists? Yes; each node participates in at most one reversal, preserving O(n) total time.

## 17. [Path Sum II](https://leetcode.com/problems/path-sum-ii/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Enumerate all root-to-leaf paths with the requested sum.

**Talking points:**

- I'd carry a mutable root-to-node path and the remaining target.
- At a matching leaf I'd save a copy, then undo each append on return so sibling paths and saved
  answers stay independent.

**Solution:** Append the current value to one mutable path, recurse with the reduced target, and remove that value before returning. At a matching leaf, append a copy of the path to the result. This makes sibling exploration independent and prevents later mutations from corrupting saved answers.

**Complexity:**

**Time complexity:** `O(n + L)` for `n` nodes and `L` total values copied into answers; `L` can
be `O(n^2)`.

**Space complexity:** `O(h)` auxiliary path/stack, plus `O(L)` output.

**Java:** [PathSumII](./java/17-PathSumII.java) — Solution; input preserved and each result owns its path list.

**Potential interviewer follow-ups:**

- Why is output copying part of the complexity? Many matching leaves can share a long prefix that must appear separately in every returned list.
- If only the number of root-to-leaf matches is needed? Return counts from children and eliminate both path storage and output copying.

## 18. [Path Sum III](https://leetcode.com/problems/path-sum-iii/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Count downward paths that may start or end at any node, rather than only at the root and leaves.

**Talking points:**

- I'd count ancestor prefix sums so `currentSum - target` identifies paths ending at the current
  node.
- I'd query before inserting and remove the current prefix on return, preventing sibling
  branches from sharing path history.

**Solution:** Track the current root-to-node prefix sum and frequencies of ancestor prefix sums. A previous prefix equal to currentSum - target identifies a matching path ending here. Seed zero once, query before inserting the current prefix, recurse, then decrement/remove that prefix on return so siblings never share path history. Use long sums because node values can reach magnitude 10⁹.

**Complexity:**

**Time complexity:** `O(n)` expected for `n` nodes.

**Space complexity:** `O(h)` active prefix-map and recursion space for height `h`.

**Java:** [PathSumIII](./java/18-PathSumIII.java) — Solution; input preserved and all state is local to each public call.

**Potential interviewer follow-ups:**

- Why store frequencies instead of a set? Zero and negative values can produce repeated prefix sums, each representing a different valid starting point.
- What does forgetting the decrement do? Prefixes from a finished sibling can be counted as ancestors, creating nonexistent paths.

## 19. [Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Reuse the same TreeNode objects to make a preorder chain through right pointers, with every left pointer null.

**Talking points:**

- For a node with a left subtree, I'd attach its old right subtree to the left subtree's
  rightmost node.
- Then I'd move the left subtree to the right and continue, preserving preorder while rewiring
  in place.

**Solution:** When a node has a left child, find the rightmost node along that child's right spine. Attach the old right subtree there, move the left subtree to the right, and clear the left link. Advance right and repeat. The deferred old right subtree remains after the moved left subtree as later rewiring completes preorder.

**Complexity:**

**Time complexity:** `O(n)` amortized overall; predecessor scans traverse disjoint original
right-spine segments.

**Space complexity:** `O(1)` auxiliary space; rewires the input tree.

**Java:** [FlattenBinaryTree](./java/19-FlattenBinaryTree.java) — Solution mutates original links; creates no replacement nodes and uses no recursion.

**Potential interviewer follow-ups:**

- What is the simpler stack alternative? Push right before left, link consecutive preorder pops, and clear left links; this uses O(h) auxiliary space.
- How would you test the in-place requirement? Save original preorder node references, then check reference identity, null left pointers, and a null final right pointer.

## 20. [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Width includes missing positions between the outermost nodes at a depth, not just the number of present nodes.

**Talking points:**

- I'd BFS with conceptual complete-tree positions so gaps between actual nodes contribute to
  width.
- Subtracting the first position at each level preserves those gaps while keeping indices small.

**Solution:** BFS actual nodes with conceptual complete-tree indices: children of index i use 2i and 2i + 1. Subtract the first index at each level before deriving child indices. The last normalized index plus one gives that level's width. Normalization preserves gaps while preventing indices from growing just because a narrow tree is deep; long handles intermediate doubling under the signed-int answer guarantee.

**Complexity:**

**Time complexity:** `O(n)` for `n` actual nodes.

**Space complexity:** `O(w)` auxiliary queue, where `w` is the maximum number of actual nodes on
a level, rather than its width including gaps.

**Java:** [MaximumWidthBinaryTree](./java/20-MaximumWidthBinaryTree.java) — Solution; input preserved, normalized long indices, signed-int result as guaranteed by the problem.

**Potential interviewer follow-ups:**

- Why is queue size insufficient? Two nodes can enclose many missing positions in the conceptual complete tree.
- Why normalize even when using long? An unnormalized chain can overflow long after enough levels despite having width one.
