# Java Solutions — 18b — Graphs: Depth-First Search

Each file is an independent LeetCode submission. Most classes are named `Solution`; rename a labelled alternative to `Solution` when submitting it. Do not compile all files into one package. Code uses four-space formatting and explicit control-flow braces.

LeetCode supplies `TreeNode`, the problem-specific `Node`, or `NestedInteger`. Read each file's notes: graph `Node.neighbors`, N-ary `Node.children`, and next-pointer-tree `Node.left/right/next` are different judge types. Local test models are not submission code. Java 8-compatible solutions; JDK 11+ for local tests.

1. [Flood Fill](01-FloodFill.java) — Solution mutates image in place and returns the same array reference. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.
2. [Island Perimeter](02-IslandPerimeter.java) — All variants preserve grid. ScanSolution is the space-optimized follow-up and illustrates when a graph model does not require traversal. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.
3. [Number of Connected Components in an Undirected Graph](03-ConnectedComponentsInUndirectedGraph.java) — Premium. Solution preserves edges. Premium NeetCode 150 selection; vertices with no edges still count.
4. [Number of Islands](04-NumberOfIslands.java) — Solution mutates grid by replacing visited '1' cells with '0'. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.
5. [Max Area of Island](05-MaxAreaOfIsland.java) — Solution destroys land marks by changing visited 1s to 0s; clone the grid first if preservation is required.
6. [Nested List Weight Sum](06-NestedListWeightSum.java) — Premium. Solution preserves the nested input. LeetCode supplies NestedInteger; this Premium screenshot problem is a recursive-DFS bridge, not an explicit adjacency-list graph.
7. [Surrounded Regions](07-SurroundedRegions.java) — Solution intentionally modifies board in place and removes all temporary markers before returning. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.
8. [Number of Closed Islands](08-NumberOfClosedIslands.java) — Solution mutates land 0s to water 1s. The land encoding is opposite to Number of Islands.
9. [Number of Enclaves](09-NumberOfEnclaves.java) — Solution mutates boundary-connected land to zero; enclosed land stays one.
10. [Count Sub Islands](10-CountSubIslands.java) — Solution preserves grid1 and changes visited land in grid2 to zero.
11. [Pacific Atlantic Water Flow](11-PacificAtlanticWaterFlow.java) — Solution preserves heights and returns coordinates in row-major order; any order is accepted. Primary Solution uses an explicit DFS stack; BfsSolution preserves the earlier BFS alternative.
12. [All Paths From Source to Target](12-AllPathsFromSourceToTarget.java) — Solution preserves graph and copies each output path. This is DFS plus backtracking, intentionally connecting 14 to graph traversal.
13. [Evaluate Division](13-EvaluateDivision.java) — Solution preserves all inputs and uses recursive DFS on the problem's small equation graph.
14. [Making A Large Island](14-MakingALargeIsland.java) — Solution mutates grid: land 1s become component IDs 2 and above. Iterative traversal handles large components without recursive stack overflow.
