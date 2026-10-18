# 18b — Graphs: Depth-First Search

DFS follows one branch before returning to alternatives. Learn global component marking first, then returned subtree/nesting results, border-connected regions, reverse reachability, and path-local enumeration. Large grid traversals use explicit stacks; bounded nesting/path problems also show recursive call contracts.

## How to recognize this pattern

**Keywords / hints:** “connected component,” “island,” “enclosed region,” “all paths,” “nested structure,” “reachable,” or a value accumulated along one route.

**Structural test:** Does a visit finalize global reachability, return a reusable subproblem answer, or belong only to the current path? Choose global visited, memoization, or choose/recurse/undo accordingly.

**Invariant / mental model:** For component traversal, mark a node when pushing it so it is scheduled once. For path enumeration, the current path is valid and is restored after each branch. Recursive returned results summarize exactly the specified subtree/suffix.

**When not to use it:** DFS does not guarantee a shortest unweighted route on first discovery. Do not globally suppress revisits when enumerating all paths. Java recursive DFS can overflow on long grid components; use an explicit stack when depth can be large.

**Prerequisites:** 11 recursion, 14 backtracking, 01 maps/sets; 18a BFS provides the contrast between distance layers and depth-first reachability.

## Selection and practice order

14 problems: 2 Easy / 11 Medium / 1 Hard. We selected available, suitable NeetCode 150 problems first, then filled out the progression from your second (DFS) screenshot. Source priority determines selection, while the numbering teaches prerequisites before harder combinations. Retained existing exercises are explicitly labelled; no original graph exercise was dropped.

**NeetCode 150 first pass:** 3. Number of Connected Components in an Undirected Graph; 4. Number of Islands; 5. Max Area of Island; 7. Surrounded Regions; 11. Pacific Atlantic Water Flow.

Premium requirements are labelled per problem. Your screenshots include Premium questions; no paywall-free claim is made. Difficulty mix follows pattern fit rather than the earlier quota. Both traversals can solve many connectivity problems; these folders teach the named traversal as the primary solution, not an exclusive classification.

Nine problems from screenshot 2 are included. The other two already have full exercises: [329. Longest Increasing Path in a Matrix](../19-topological-sort/README.md#7-longest-increasing-path-in-a-matrix--hard) in topological sort and [1971. Find if Path Exists in Graph](../20-union-find/README.md#1-find-if-path-exists-in-graph--easy) in union-find. They remain cross-references rather than duplicate numbered assignments. NeetCode 150 adds Connected Components, Surrounded Regions, and Pacific Atlantic; Island Perimeter and Making A Large Island are retained from the original graph section. Clone Graph's primary BFS implementation is in 18a.

[Java solution index](./java/README.md) · [Other traversal](../18a-graphs-bfs/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## Global visited versus path-local state

```java
// Component traversal pseudocode: State and neighbors are problem-specific.
Deque<State> stack = new ArrayDeque<>();
seen.add(start);
stack.push(start);
while (!stack.isEmpty()) {
    State current = stack.pop();
    for (State next : neighbors(current)) {
        if (seen.add(next)) {
            stack.push(next);
        }
    }
}
```

For all-path enumeration, append the next node, recurse, then remove it. The same node may belong to several answers. A DAG has no cycles, but that does not make a single global visited set correct for enumerating paths. For memoized DAG DFS, cache a returned result only after its dependency computation is complete.

Grid values are not universal: Number of Islands uses 1 as land; Number of Closed Islands uses 0 as land. Read the contract before reusing a template.

## 1. [Flood Fill](https://leetcode.com/problems/flood-fill/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Recolor exactly the component connected to a starting cell through equal-colored neighbors.

**Talking points:**

- I'd explore the starting cell's same-color component using an explicit DFS stack.
- Recoloring on push prevents duplicate scheduling; an unchanged replacement color needs an
  immediate return.

**Solution:** Use a LIFO stack to explore the same-color component. Recolor each cell when pushing it, so it cannot be discovered twice. If the replacement color equals the original color, return immediately because recoloring would not mark progress. BfsSolution retains the earlier queue-based alternative.

**Complexity:**

**Time complexity:** `O(RC)` worst case for an `R` by `C` image.

**Space complexity:** `O(RC)` auxiliary DFS stack; recolors the image in place.

**Java:** [FloodFill](./java/01-FloodFill.java) — Solution mutates image in place and returns the same array reference. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.

**Potential interviewer follow-ups:**

- Why mark on push rather than pop? Otherwise several neighbors can push the same cell before its first removal.
- What if the input must remain unchanged? Copy the image or keep a separate visited matrix and write a separate result.

## 2. [Island Perimeter](https://leetcode.com/problems/island-perimeter/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** Retained from the original graph section

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Count component edges that touch water or leave the grid; a boundary-accounting traversal warm-up.

**Talking points:**

- I'd traverse the land component and count every edge facing water or the grid boundary.
- The scan follow-up instead adds four per land cell and subtracts shared edges, avoiding
  traversal storage.

**Solution:** The primary iterative DFS visits one land component and counts edges facing water or leaving the grid. ScanSolution instead adds four per land cell and subtracts two for each shared top/left edge, removing the need for traversal storage under the single-island contract. BfsSolution retains the earlier traversal for comparison.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid, for all supplied variants.

**Space complexity:** Primary DFS/BFS: `O(RC)` visited/frontier space; scan alternative: `O(1)`
auxiliary space.

**Java:** [IslandPerimeter](./java/02-IslandPerimeter.java) — All variants preserve grid. ScanSolution is the space-optimized follow-up and illustrates when a graph model does not require traversal. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.

**Potential interviewer follow-ups:**

- What if there are several islands? The scan gives their combined perimeter; a BFS from a chosen start gives that component's perimeter.
- What about lakes inside an island? Water-facing edges around holes contribute to this boundary definition; excluding them requires classifying exterior water.

## 3. [Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/count-connected-components/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority

**Access:** LeetCode Premium (checked 2026-09-12).

**Recognize it:** Count groups in an explicit undirected adjacency list, including isolated vertices.

**Talking points:**

- I'd scan all vertices and start DFS whenever I find one not yet visited.
- Every new traversal identifies one component, including isolated vertices and groups
  disconnected from vertex zero.

**Solution:** Build adjacency lists, scan all vertices, and start an iterative DFS whenever a vertex has not been visited. Each new traversal contributes one component. Starting from only vertex zero would miss disconnected groups.

**Complexity:**

**Time complexity:** `O(V + E)` for `V` vertices and `E` edges.

**Space complexity:** `O(V + E)` auxiliary adjacency/visited/stack storage.

**Java:** [ConnectedComponentsInUndirectedGraph](./java/03-ConnectedComponentsInUndirectedGraph.java) — Solution preserves edges. Premium NeetCode 150 selection; vertices with no edges still count.

**Potential interviewer follow-ups:**

- How does union-find compare? It joins endpoints and counts remaining roots, which is useful when edges are added incrementally.
- Why isn't a single DFS enough? It explores only its starting component; the outer vertex loop accounts for disconnected components and isolated nodes.

## 4. [Number of Islands](https://leetcode.com/problems/number-of-islands/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/count-number-of-islands/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority; also screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Count connected components of four-directional land, not the number of land cells.

**Talking points:**

- Each unseen land cell begins an island, so I'd count it and consume its entire component with
  DFS.
- Marking on push ensures multiple neighbors cannot schedule the same cell.

**Solution:** Scan for unseen land. Each such cell begins a new component; increment the count and mark its whole component using an explicit DFS stack. Mark on push, not on pop, so multiple neighbors cannot schedule the same cell. The old BFS implementation is retained as BfsSolution.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` auxiliary stack in the worst case; the grid stores visited
markers.

**Java:** [NumberOfIslands](./java/04-NumberOfIslands.java) — Solution mutates grid by replacing visited '1' cells with '0'. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.

**Potential interviewer follow-ups:**

- What if land is added incrementally with repeated count queries? Union-find can merge neighboring components efficiently; see 20.
- What if diagonal cells are connected too? Change the neighbor set to eight directions; the component traversal stays the same.

## 5. [Max Area of Island](https://leetcode.com/problems/max-area-of-island/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/max-area-of-island/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority; also screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Find every land component and keep the largest number of cells in one component.

**Talking points:**

- I'd DFS from each unseen land cell and count the cells in that component.
- After consuming it, I'd compare its area with the best found so far.

**Solution:** Scan the grid. Each unseen land cell starts an iterative DFS, which counts its component while marking discovered land as water. Update the largest component area after the stack empties.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` worst-case auxiliary stack; land is marked in place.

**Java:** [MaxAreaOfIsland](./java/05-MaxAreaOfIsland.java) — Solution destroys land marks by changing visited 1s to 0s; clone the grid first if preservation is required.

**Potential interviewer follow-ups:**

- How do you also return the island's cells? Collect coordinates during each traversal, retaining the largest component.
- Why is the nested scan plus DFS still linear? Each cell is discovered and processed at most once across all DFS calls.

## 6. [Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** LeetCode Premium (checked 2026-09-12).

**Recognize it:** Each nested integer contributes a value multiplied by its depth; recursive calls carry the current depth.

**Talking points:**

- I'd recurse through each nested list with its current depth.
- An integer contributes its value times that depth, while a sublist contributes its children's
  recursive total.

**Solution:** Walk each list recursively. An integer contributes value*depth; a nested list contributes the result of traversing its children at depth+1. The returned sum is a complete summary of one list at its specified depth. Top-level integers have depth one.

**Complexity:**

**Time complexity:** `O(N)` for `N` nested elements.

**Space complexity:** `O(D)` recursive stack for maximum nesting depth `D`.

**Java:** [NestedListWeightSum](./java/06-NestedListWeightSum.java) — Solution preserves the nested input. LeetCode supplies NestedInteger; this Premium screenshot problem is a recursive-DFS bridge, not an explicit adjacency-list graph.

**Potential interviewer follow-ups:**

- How would you avoid recursive stack limits? Use explicit iterator/depth frames; pushing every sibling at once may use O(N), while iterator frames can retain O(D).
- What if deeper values receive smaller weights? First determine the relevant maximum integer depth or use a level-based accumulation strategy; the weighting rule changes.

## 7. [Surrounded Regions](https://leetcode.com/problems/surrounded-regions/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/surrounded-regions/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Keep regions connected to the border; capture all other regions. Work from what is definitely safe.

**Talking points:**

- I'd mark the O cells reachable from the border as safe.
- Every remaining O is enclosed and can flip to X, then I'd restore the safe markers.

**Solution:** Mark every border-connected O as safe using an explicit DFS stack seeded from the boundary. Convert remaining O cells to X, then restore safe markers to O. This traverses what must be preserved instead of testing each enclosed region separately. The retained BfsSolution computes the same result with a queue.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` board.

**Space complexity:** `O(RC)` auxiliary DFS stack; temporary markers use the board itself.

**Java:** [SurroundedRegions](./java/07-SurroundedRegions.java) — Solution intentionally modifies board in place and removes all temporary markers before returning. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.

**Potential interviewer follow-ups:**

- Why are border-connected O cells safe? A region is surrounded only if it has no path through O cells to the boundary.
- Can union-find express the same test? Join border cells to a virtual outside node, then preserve cells connected to that node.

## 8. [Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Count zero-components that never touch the boundary; note that zero means land in this problem.

**Talking points:**

- I'd consume each zero component while remembering whether it touches the border.
- Even after finding a border cell I'd finish the traversal, so the same open component cannot
  be counted later.

**Solution:** For each unvisited zero, use iterative DFS to consume its entire component and track whether any cell touches a border. Count the component only if it stays interior. Even after discovering a border cell, finish the traversal so its other cells cannot be counted again.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` auxiliary DFS stack; visited cells are marked in place.

**Java:** [NumberOfClosedIslands](./java/08-NumberOfClosedIslands.java) — Solution mutates land 0s to water 1s. The land encoding is opposite to Number of Islands.

**Potential interviewer follow-ups:**

- Why not return immediately when a border cell is found? Unvisited fragments of the same component could later be mistaken for separate closed islands.
- What is the boundary-first alternative? Flood all border-connected zeroes first, then count remaining zero-components.

## 9. [Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Count land cells, not islands, that cannot reach the grid boundary.

**Talking points:**

- I'd erase every land cell reachable from border land.
- The remaining land cannot escape the grid, so summing it directly gives the enclave area.

**Solution:** Seed an explicit DFS stack with border land and erase all land reachable from those seeds. Every remaining 1 belongs to an enclave, so sum the remaining cells. This shares the boundary-reachability idea with Surrounded Regions but asks for area instead of a transformed board.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` grid.

**Space complexity:** `O(RC)` auxiliary DFS stack; marks the grid in place.

**Java:** [NumberOfEnclaves](./java/09-NumberOfEnclaves.java) — Solution mutates boundary-connected land to zero; enclosed land stays one.

**Potential interviewer follow-ups:**

- How is this different from closed-island counting? This returns the number of enclosed land cells; one enclosed component may contribute many cells.
- Can you avoid mutating the grid? Track outside-reachable land in a separate visited matrix, then count unvisited land.

## 10. [Count Sub Islands](https://leetcode.com/problems/count-sub-islands/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** An entire component in grid2 must be contained in land from grid1; one mismatch invalidates the whole component.

**Talking points:**

- I'd traverse each island in the second grid and verify every cell is land in the first.
- I'd finish consuming the island even after a mismatch, preventing its remaining cells from
  being counted again.

**Solution:** DFS each island of grid2, checking every visited coordinate against grid1. Accumulate a valid flag but always finish consuming the component even after a mismatch. Since the component is connected, if every cell is land in grid1 they all belong to the same grid1 island.

**Complexity:**

**Time complexity:** `O(RC)` for the two `R` by `C` grids.

**Space complexity:** `O(RC)` auxiliary DFS stack; marks the second grid in place.

**Java:** [CountSubIslands](./java/10-CountSubIslands.java) — Solution preserves grid1 and changes visited land in grid2 to zero.

**Potential interviewer follow-ups:**

- Why is short-circuiting traversal dangerous? A false result can stop visitation early, leaving fragments to be counted incorrectly later.
- Must you label components in grid1? No; adjacency plus every grid2 cell being land already proves containment in one connected grid1 island.

## 11. [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/pacific-atlantic-water-flow/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Selection:** NeetCode 150 priority

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Ask which cells can reach two destinations; reversing reachability avoids a search from every cell.

**Talking points:**

- I'd reverse the water flow and explore uphill or level from each ocean's boundary.
- Intersecting those two reached sets finds cells that can flow downhill to both oceans.

**Solution:** Run two reverse-flow DFS traversals, one from each ocean's border cells. Reverse moves go uphill or level, because ordinary water flow goes downhill or level. Intersect the reached sets. The primary code uses explicit stacks; BfsSolution retains the queue-based alternative.

**Complexity:**

**Time complexity:** `O(RC)` for an `R` by `C` height grid.

**Space complexity:** `O(RC)` auxiliary reached-set/stack storage, plus up to `O(RC)` output.

**Java:** [PacificAtlanticWaterFlow](./java/11-PacificAtlanticWaterFlow.java) — Solution preserves heights and returns coordinates in row-major order; any order is accepted. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.

**Potential interviewer follow-ups:**

- Why is reversing edges valid? A path from a cell to an ocean exists exactly when the reversed graph has a path from that ocean to the cell.
- Why allow equal heights in reverse traversal? The original flow allows level moves as well as downhill moves.

## 12. [All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Enumerate every source-to-target path in a DAG; a node may appear in several different answers.

**Talking points:**

- I'd backtrack through each outgoing edge while carrying the current source-to-node path.
- I'd copy the path at the target and avoid a global visited set, since different paths may
  share later vertices.

**Solution:** Recursively append each next vertex to a shared path, recurse, and remove it afterward. Copy the path at the target. No cycle guard is needed because the graph is a DAG, but a global visited set would be wrong: distinct prefixes can share the same suffix vertices and still form different paths.

**Complexity:**

**Time complexity:** `O(n 2^n)` worst-case bound including path copies for an `n`-vertex DAG.

**Space complexity:** `O(n)` auxiliary path/stack, plus potentially `O(n 2^n)` output.

**Java:** [AllPathsFromSourceToTarget](./java/12-AllPathsFromSourceToTarget.java) — Solution preserves graph and copies each output path. This is DFS plus backtracking, intentionally connecting 14 to graph traversal.

**Potential interviewer follow-ups:**

- What if the graph can contain cycles? Enumerating simple paths needs a path-local visited set; unrestricted walks may be infinite.
- What if only the number of paths is needed? Memoize the path count from each vertex or propagate counts in topological order instead of materializing paths.

## 13. [Evaluate Division](https://leetcode.com/problems/evaluate-division/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** User screenshot 2

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** Equations define weighted graph edges; multiply edge ratios along any connecting path.

**Talking points:**

- I'd model each equation as two directed edges with reciprocal weights.
- A query DFS multiplies edge ratios until it reaches the denominator; unknown variables fail
  even when both names are equal.

**Solution:** For a/b=v, add a→b with weight v and b→a with weight 1/v. For each query, DFS from numerator to denominator while multiplying ratios and marking visited variables. Consistent equations guarantee every valid route yields the same ratio, so this is reachability with accumulated weights, not a shortest-path problem. Unknown variables return -1 even for x/x.

**Complexity:**

**Time complexity:** Expected `O(V + E + Q(V + E))` for `V` variables, `E` equations and `Q`
queries, with bounded-length names.

**Space complexity:** `O(V + E)` auxiliary graph and per-query visited/stack, plus `O(Q)`
output.

**Java:** [EvaluateDivision](./java/13-EvaluateDivision.java) — Solution preserves all inputs and uses recursive DFS on the problem's small equation graph.

**Potential interviewer follow-ups:**

- Why isn't Dijkstra needed despite edge weights? The task asks for a ratio from consistent equations, not a minimum-cost route.
- How would many queries improve? A weighted union-find can maintain each variable's ratio to its component representative; that is an advanced extension of 20.

## 14. [Making A Large Island](https://leetcode.com/problems/making-a-large-island/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Selection:** Retained from the original graph section

**Access:** Free problem (checked 2026-09-12).

**Recognize it:** One change can merge neighboring components; compute component sizes once, then evaluate each possible change locally.

**Talking points:**

- I'd label islands with distinct IDs and record their areas.
- For each zero I'd add one and the areas of its distinct neighboring IDs, avoiding double
  counting the same island.

**Solution:** Label every island with a distinct ID using iterative DFS and record its area. For each zero, add one plus the areas of its distinct neighboring island IDs. A small set prevents counting the same island from multiple sides. If there are no zeroes, the existing full-grid island is already optimal.

**Complexity:**

**Time complexity:** `O(n^2)` expected for an `n` by `n` grid.

**Space complexity:** `O(n^2)` auxiliary component-area/stack storage; at most four neighbor IDs
need deduplication per zero.

**Java:** [MakingALargeIsland](./java/14-MakingALargeIsland.java) — Solution mutates grid: land 1s become component IDs 2 and above. Iterative traversal handles large components without recursive stack overflow.

**Potential interviewer follow-ups:**

- Why deduplicate adjacent component IDs? A zero can touch the same island on several sides, but that island's cells can be included only once.
- Can the same preprocessing use union-find? Yes; union neighboring land cells and query unique adjacent roots and their sizes.

## Sources and checks

Selection labels were checked against [NeetCode 150](https://neetcode.io/practice/practice/neetcode150) and [LeetCode's public catalog](https://leetcode.com/api/problems/all/) on 2026-09-12. Screenshot membership comes from the two images you supplied, not an inferred popularity ranking.

Run `bash Leetcode/tests/verify.sh 18b-graphs-dfs` from the workspace root. These are local checks, not a claim of hidden-judge acceptance.
