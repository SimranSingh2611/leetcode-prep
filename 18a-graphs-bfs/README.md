# 18a — Graphs: Breadth-First Search

BFS explores a graph by distance layers. Begin with tree levels and explicit adjacency lists, then single-source grid routes, multi-source distance maps, tree-to-graph conversion, and richer states. Tree examples are labelled bridges into graph BFS, not additional generic tree-traversal assignments.

## How to recognize this pattern

**Keywords / hints:** “minimum steps,” “nearest,” “shortest unweighted path,” “level order,” “spread each minute,” “distance k,” or simultaneous sources.

**Structural test:** What is one state and one legal move? If every move costs the same, can first discovery fix that state's shortest distance? Include remaining resources in the state when they affect future moves.

**Invariant / mental model:** Queue order is nondecreasing distance. Mark a full state when enqueuing it, not when removing it. For multi-source BFS, enqueue all distance-zero sources before processing any of them.

**When not to use it:** Plain BFS does not optimize arbitrary weighted routes. A cell-only visited flag is insufficient when reaching that cell with a different remaining budget changes future choices. Use DFS for exhaustive path enumeration unless minimum distance is the actual goal.

**Prerequisites:** 12 trees, 01 sets/maps, and basic FIFO queue operations (contrast with 08 stacks). Java ArrayDeque offer/poll implements FIFO. Node identity matters for tree-to-graph problems.

## Selection and practice order

14 problems: 12 Medium / 2 Hard. We selected available, suitable NeetCode 150 problems first, then filled out the progression from your first (BFS) screenshot. Source priority determines selection, while the numbering teaches prerequisites before harder combinations. Retained existing exercises are explicitly labelled; no original graph exercise was dropped.

**NeetCode 150 first pass:** 3. Clone Graph; 4. Graph Valid Tree; 7. Walls and Gates; 8. Rotting Oranges; 13. Word Ladder.

Premium requirements are labelled per problem. Your screenshots include Premium questions; no paywall-free claim is made. Difficulty mix follows pattern fit rather than the earlier quota. Both traversals can solve many connectivity problems; these folders teach the named traversal as the primary solution, not an exclusive classification.

All 12 problems from screenshot 1 are included, plus NeetCode 150's Clone Graph and Graph Valid Tree. Binary Tree Level Order Traversal is already in 12, so the N-ary variant is the warm-up here. Course Schedule I/II remain in 19; they use dependency readiness rather than ordinary shortest-path layers.

[Java solution index](./java/README.md) · [Other traversal](../18b-graphs-dfs/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## Core queue template

```java
// Pseudocode: customize State, neighbors, and the distance-zero sources.
Queue<State> queue = new ArrayDeque<>();
for (State source : sources) {
    if (seen.add(source)) {
        queue.offer(source);
    }
}
int distance = 0;
while (!queue.isEmpty()) {
    int layerSize = queue.size();
    for (int i = 0; i < layerSize; i++) {
        State current = queue.poll();
        // Check the goal here if returning its distance.
        for (State next : neighbors(current)) {
            if (seen.add(next)) {
                queue.offer(next);
            }
        }
    }
    distance++;
}
```

Decide whether the answer counts edges, cells, or words. A tree remains acyclic, but allowing parent moves lets a traversal bounce back across an edge. Track visited nodes (or the previous node) to prevent that backtracking.

## 1. [N-ary Tree Level Order Traversal](https://leetcode.com/problems/n-ary-tree-level-order-traversal/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Group nodes by their distance from the root; the number of children is not fixed at two.

**Talking points:**

- I'd use BFS and snapshot the queue size for each level.
- Processing exactly that many nodes groups their values together while leaving their children
  for the next level.

**Solution:** Queue the root and freeze the current queue size for each level. Remove exactly that many nodes, append their values to one result row, and enqueue their children for the next row. This is the simplest layer-boundary exercise.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(w)` auxiliary queue for maximum level width `w`, plus `O(n)` output.

**Java:** [NaryTreeLevelOrderTraversal](./java/01-NaryTreeLevelOrderTraversal.java) — Solution preserves the N-ary tree. LeetCode supplies Node with val and children.

**Potential interviewer follow-ups:**

- Why freeze queue.size() before the inner loop? Children enqueued during that loop belong to the next level, not the current one.
- Could DFS produce the same output? Yes, append to a depth-indexed result; BFS makes level order explicit.

## 2. [Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Connect neighbors on each depth level of a perfect binary tree; compare explicit BFS with reusing the level links.

**Talking points:**

- I'd process one BFS layer at a time and connect each node to the next node removed from that
  layer.
- The final node points to null; the perfect-tree follow-up traverses existing next links to
  eliminate the queue.

**Solution:** The main solution processes one BFS layer at a time, connecting the previously removed node to the current node and terminating each layer with null. The optimized alternative walks a level using existing next links and stitches its children's next level without a queue. The latter relies on the perfect-tree guarantee.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes, for both versions.

**Space complexity:** Primary BFS: `O(w)` auxiliary queue; perfect-tree alternative: `O(1)`
auxiliary space.

**Java:** [PopulateNextRightPointers](./java/02-PopulateNextRightPointers.java) — Both mutate next pointers, not left/right child links. Judge Node has val, left, right, next.

**Potential interviewer follow-ups:**

- How does the constant-space solution connect across parents? parent.right.next = parent.next.left when a next parent exists.
- What if the tree is not perfect? A dummy next-level head and tail can chain whichever children exist; the direct perfect-tree links are no longer sufficient.

## 3. [Clone Graph](https://leetcode.com/problems/clone-graph/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Copy a graph with cycles/shared neighbors while preserving identity relationships.

**Talking points:**

- I'd map each original node object to exactly one clone before exploring its neighbors.
- BFS then copies each edge through that map, preventing repeated clones and infinite traversal
  around cycles.

**Solution:** A map links each original node object to its unique copy. BFS creates a copy when an original node is first discovered, then appends copied neighbors to the current copy's adjacency list. Storing a copy before exploring edges prevents infinite recursion/repeated copies on cycles.

**Complexity:**

**Time complexity:** `O(V + E)` expected for reachable vertices `V` and edges `E`.

**Space complexity:** `O(V)` auxiliary map/queue, plus `O(V + E)` cloned output.

**Java:** [CloneGraph](./java/03-CloneGraph.java) — Solution uses IdentityHashMap to make object identity explicit. LeetCode supplies Node with val and neighbors; input is unchanged.

**Potential interviewer follow-ups:**

- Why not use only the node value as a map key? That depends on a uniqueness guarantee; object identity works even when values repeat.
- How would you clone a disconnected graph? Accept a collection of starting nodes and reuse the same original-to-copy map across component traversals.

## 4. [Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority

**Access:** LeetCode Premium (checked 2026-09-12).

**Recognize it:** An undirected graph is a tree exactly when it is connected and has n-1 edges.

**Talking points:**

- I'd require exactly `n - 1` edges, then BFS to check that all vertices are connected.
- Connectivity plus that edge count proves the graph is a tree, so I can avoid a separate cycle
  detector.

**Solution:** Reject an edge count other than n-1, build adjacency lists, then BFS from vertex 0. If all n vertices are reached, the edge-count condition rules out cycles. Mark nodes when enqueuing. This keeps cycle reasoning separate from the traversal mechanics.

**Complexity:**

**Time complexity:** `O(n + E)` for `n` vertices and `E` edges; a failing edge count returns in
`O(1)`.

**Space complexity:** `O(n + E)` auxiliary adjacency/visited/queue storage.

**Java:** [GraphValidTree](./java/04-GraphValidTree.java) — Solution preserves edges. This Premium NeetCode 150 exercise uses an undirected graph.

**Potential interviewer follow-ups:**

- Why is connectivity plus n-1 edges enough? Every connected graph contains a spanning tree of n-1 edges; with exactly that many, no extra cycle edge exists.
- Can you detect cycles during BFS instead? Track parent vertices and reject a visited neighbor other than the parent, under the simple-graph assumptions.

## 5. [Shortest Path to Get Food](https://leetcode.com/problems/shortest-path-to-get-food/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** LeetCode Premium (checked 2026-09-12).

**Recognize it:** Find the nearest food cell through equally costly four-directional grid moves.

**Talking points:**

- I'd BFS from the starting cell through non-wall neighbors, marking cells when queued.
- BFS explores increasing move counts, so the first food reached gives the shortest path.

**Solution:** Find the starting '*' cell and BFS through non-wall cells. 'X' blocks movement, 'O' is traversable, and '#' is a food goal. Mark cells on enqueue; the first dequeued food cell gives the minimum number of moves. Multiple food cells require no special ordering.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` auxiliary visited/queue space.

**Java:** [ShortestPathToGetFood](./java/05-ShortestPathToGetFood.java) — Solution preserves grid using a separate visited matrix. Premium screenshot selection.

**Potential interviewer follow-ups:**

- What if several people start simultaneously? Seed all starting cells at distance zero to find the distance from the closest person.
- What if you need the route, not just its length? Store a parent for each discovered cell and reconstruct from the first food reached.

## 6. [Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** The shortest open-cell route allows eight directions, and its length counts cells rather than edges.

**Talking points:**

- I'd reject a blocked endpoint, then BFS through zero cells in all eight directions.
- Distance starts at one because the path counts cells, and marking on enqueue prevents repeated
  visits.

**Solution:** Reject a blocked start or end. BFS over zero cells with all eight neighboring offsets, marking on enqueue. Start the distance at one because the starting cell counts. The first arrival at the bottom-right cell is shortest.

**Complexity:**

**Time complexity:** `O(n^2)` for an `n` by `n` grid.

**Space complexity:** `O(n^2)` auxiliary visited/queue space.

**Java:** [ShortestPathInBinaryMatrix](./java/06-ShortestPathInBinaryMatrix.java) — Solution preserves grid. A one-cell open grid returns 1.

**Potential interviewer follow-ups:**

- Why include diagonal neighbors here but not in island problems? Connectivity is part of the statement, not an inherent property of a grid.
- What does bidirectional BFS change? It can reduce explored states, but both frontiers must use the same move rules and distance convention.

## 7. [Walls and Gates](https://leetcode.com/problems/walls-and-gates/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority; also screenshot 1

**Access:** LeetCode Premium (checked 2026-09-12).

**Recognize it:** Fill each room with its distance to the nearest of several gates: every gate is a distance-zero source.

**Talking points:**

- I'd enqueue all gates before starting BFS.
- Each unfilled room gets its distance when first discovered, making the nearest gate win
  automatically.

**Solution:** Queue all gates first. BFS only into unfilled rooms (Integer.MAX_VALUE), assigning neighbor distance current+1 before enqueueing. Walls remain -1 and unreachable rooms retain infinity. The room value acts as the visited marker.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` auxiliary queue; the rooms array stores visited distances in
place.

**Java:** [WallsAndGates](./java/07-WallsAndGates.java) — Solution updates rooms in place. Premium NeetCode 150 and screenshot selection.

**Potential interviewer follow-ups:**

- Why not run BFS from every empty room? That repeats the same exploration; reversing the query and seeding every gate shares the work.
- Why is the first assigned distance final? The multi-source queue explores every smaller distance before any larger distance.

## 8. [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority; also screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Multiple sources spread simultaneously in unit-time steps; ask when the last reachable target is reached.

**Talking points:**

- I'd start BFS from every rotten orange and count the fresh ones.
- Each layer is one minute; any fresh oranges remaining after the frontier disappears are
  unreachable.

**Solution:** Enqueue every initially rotten orange before BFS and count fresh oranges. Each BFS layer represents one minute. Mark fresh neighbors rotten when enqueued and decrement the fresh count. If the frontier disappears while fresh oranges remain, return -1.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` auxiliary queue; mutates the grid to mark newly rotten oranges.

**Java:** [RottingOranges](./java/08-RottingOranges.java) — Solution mutates grid by changing newly rotten oranges from 1 to 2.

**Potential interviewer follow-ups:**

- Why not run a separate BFS from every rotten orange? A multi-source queue finds each cell's nearest source in one traversal.
- What if different cells take different times to rot? Unit BFS layers no longer model arrival times; a weighted shortest-path method may be needed.

## 9. [01 Matrix](https://leetcode.com/problems/01-matrix/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Every cell needs its distance to the closest zero; zero cells are simultaneous sources.

**Talking points:**

- I'd seed every zero with distance zero and BFS outward from all of them.
- A cell's first assigned distance is its shortest distance to any zero.

**Solution:** Initialize all distances to -1, set zeros to distance 0, and enqueue all zeros. BFS assigns each unvisited neighbor its predecessor distance plus one. Unlike Walls and Gates, every cell is traversable; the result is a separate distance matrix.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` matrix.

**Space complexity:** `O(RC)` auxiliary queue, plus `O(RC)` output distances.

**Java:** [ZeroOneMatrix](./java/09-ZeroOneMatrix.java) — Solution preserves mat and returns a new matrix.

**Potential interviewer follow-ups:**

- Why not start from all ones? Each one asks about its nearest zero, so zeros are the natural shared sources.
- Can two DP passes solve this obstacle-free variant? Yes, combine top/left and bottom/right distances; obstacles require more care and BFS generalizes directly.

## 10. [As Far from Land as Possible](https://leetcode.com/problems/as-far-from-land-as-possible/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Maximize the distance to the nearest land cell: the last reached water layer is the answer.

**Talking points:**

- I'd start BFS from every land cell and expand into water.
- The final layer measures the farthest cell's distance to its nearest land; all-land and all-
  water inputs return failure.

**Solution:** Enqueue all land cells at distance zero, then spread into water. The final BFS layer gives the largest nearest-land distance. Reject all-land and all-water grids because the problem requires both cell types.

**Complexity:**

**Time complexity:** `O(n^2)` for an `n` by `n` grid.

**Space complexity:** `O(n^2)` auxiliary visited/queue space.

**Java:** [AsFarFromLandAsPossible](./java/10-AsFarFromLandAsPossible.java) — Solution preserves grid using a separate visited matrix.

**Potential interviewer follow-ups:**

- Why is this a max of minimum distances? Multi-source BFS assigns each water cell its minimum distance to any land; the last layer maximizes that value.
- How could space be reduced by modifying input? Turn water into visited land on enqueue, eliminating the separate visited matrix but not the queue.

## 11. [All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Distance is measured from an arbitrary tree node, so moves must include parents as well as children.

**Talking points:**

- I'd first record parent links so the tree can be traversed in both directions.
- A BFS from the target over child and parent links leaves exactly the distance-`k` nodes after
  `k` layers.

**Solution:** First traverse the tree to map each node to its parent. Then BFS from the target over left, right, and parent links, with identity-based visited tracking. After k complete layers, the queue contains exactly the nodes at distance k.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(n)` auxiliary parent/visited/queue storage, plus up to `O(n)` output.

**Java:** [AllNodesDistanceKInBinaryTree](./java/11-AllNodesDistanceKInBinaryTree.java) — Solution preserves the tree and uses the actual target node reference, not just its value.

**Potential interviewer follow-ups:**

- Why is visited needed if the input is a tree? Adding reverse parent moves creates immediate backtracking edges, so traversal can bounce between parent and child.
- What if k is zero? The source itself is the only node in the distance-zero layer.

## 12. [Amount of Time for Binary Tree to Be Infected](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Infection spreads one tree edge per minute from an arbitrary starting node; find the farthest distance.

**Talking points:**

- I'd build parent links and locate the infection's starting node.
- BFS over the resulting undirected tree spreads infection one edge per minute, so the final
  layer gives the total time.

**Solution:** Build parent links and locate the uniquely valued start node using an initial BFS. A second BFS treats the tree as an undirected graph. The final layer number is the time until all nodes are infected. Iterative traversals avoid overflow on a highly skewed tree.

**Complexity:**

**Time complexity:** `O(n)` for `n` nodes.

**Space complexity:** `O(n)` auxiliary parent/visited/queue storage.

**Java:** [TimeForBinaryTreeToBeInfected](./java/12-TimeForBinaryTreeToBeInfected.java) — Solution preserves the tree. The problem guarantees unique values and that start exists.

**Potential interviewer follow-ups:**

- How does this differ from distance-k queries? It explores all layers and returns the largest distance rather than the contents of one layer.
- Can a tree DP avoid parent storage? A postorder computation can combine subtree heights and distance to the infected node, but deep recursion needs care.

## 13. [Word Ladder](https://leetcode.com/problems/word-ladder/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority; also screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Words are states; changing one letter is one equal-cost edge. Find the shortest transformation length.

**Talking points:**

- I'd BFS through words differing by one letter, generating neighbors by replacing each
  position.
- Removing words from the dictionary when queued prevents repeat work; the first end-word
  arrival minimizes transformations.

**Solution:** BFS from beginWord, generating neighbors by replacing each position with each lowercase letter. Keep remaining dictionary words in a set and remove a word as soon as it is enqueued. The first arrival at endWord has the minimum number of transformations; the returned length counts words, including both ends.

**Complexity:**

**Time complexity:** `O(26 N L^2)` worst-case bound for `N` dictionary words of length `L`,
including constructed-string hashing.

**Space complexity:** `O(NL)` retained candidate strings plus `O(N)` queue references and `O(L)`
character workspace; `O(NL)` overall.

**Java:** [WordLadder](./java/13-WordLadder.java) — Solution copies wordList into a set and leaves the input list unchanged.

**Potential interviewer follow-ups:**

- How can the search frontier be reduced? Bidirectional BFS expands the smaller frontier from the start or end, often visiting fewer words while preserving shortest-path distance.
- What if all shortest ladders are required? Keep all parents that reach a word at its minimum distance, then backtrack through that predecessor DAG.

## 14. [Shortest Path in a Grid with Obstacles Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Selection:** User screenshot 1

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Position alone is not a complete state: two visits with different remaining obstacle budgets have different options.

**Talking points:**

- I'd BFS over position and remaining obstacle budget, since visits to the same cell can have
  different futures.
- A state spends budget when entering an obstacle; the dominance follow-up skips visits already
  reached no later with at least as much budget.

**Solution:** BFS over (row, column, remaining eliminations). Entering an obstacle spends one unit; reject negative budgets and mark each three-dimensional state on enqueue. The alternative records only the greatest budget seen per cell and enqueues a new visit only when it improves that budget. Because BFS discovers candidates in nondecreasing distance, an already discovered visit with at least as much budget dominates the new one. Both use a safe Manhattan-distance shortcut when the budget is sufficiently large.

**Complexity:**

**Time complexity:** `O(RC(K + 1))` worst case for either version, with grid dimensions `R`, `C`
and budget `K`.

**Space complexity:** Primary: `O(RC(K + 1))` visited/queue; dominance version uses `O(RC)`
visited storage but still has an `O(RC(K + 1))` worst-case queue bound.

**Java:** [ShortestPathWithObstacleElimination](./java/14-ShortestPathWithObstacleElimination.java) — Both preserve grid. DominanceSolution optimizes visited storage; neither changes the distance objective. The start and destination are guaranteed empty.

**Potential interviewer follow-ups:**

- Why is boolean visited[row][col] incorrect? An early visit may have spent its budget, while a later visit with more budget can complete the route.
- Can a later higher-budget visit always cancel an earlier lower-budget queue entry? Not solely on budget: it might have taken more steps. This implementation prunes new dominated arrivals and does not discard queued entries using a future budget value.

## Sources and checks

Selection labels were checked against [NeetCode 150](https://neetcode.io/practice/practice/neetcode150) and [LeetCode's public catalog](https://leetcode.com/api/problems/all/) on 2026-09-12. Screenshot membership comes from the two images you supplied, not an inferred popularity ranking.

Run `bash Leetcode/tests/verify.sh 18a-graphs-bfs` from the workspace root. These are local checks, not a claim of hidden-judge acceptance.
