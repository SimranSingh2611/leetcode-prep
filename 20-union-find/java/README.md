# Java Solutions — 20 — Union-Find

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

All ten solutions use the same `UnionFind` helper: private `parent`, `rank`, and `components` fields; union by rank; recursive path compression; and the `union`, `find`, `numOfComponents`, and `isConnected` methods.

1. [Find if Path Exists in Graph](01-FindIfPathExistsInGraph.java) · [LeetCode](https://leetcode.com/problems/find-if-path-exists-in-graph/) — Solution uses recursive path compression and union by rank; inputs are unchanged.
2. [Number of Provinces](02-NumberOfProvinces.java) · [LeetCode](https://leetcode.com/problems/number-of-provinces/) — Solution preserves the matrix; a DFS solution is also O(n²) time for this representation.
3. [Redundant Connection](03-RedundantConnection.java) · [LeetCode](https://leetcode.com/problems/redundant-connection/) · [NeetCode 150](https://neetcode.io/problems/redundant-connection/question?list=neetcode150) — Solution returns a cloned edge and preserves input; labels are 1-based.
4. [Satisfiability of Equality Equations](04-SatisfiabilityOfEqualityEquations.java) · [LeetCode](https://leetcode.com/problems/satisfiability-of-equality-equations/) — Solution performs two passes and leaves equations unchanged.
5. [Number of Operations to Make Network Connected](05-NumberOfOperationsToMakeNetworkConnected.java) · [LeetCode](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) — Solution does not mutate connections.
6. [Accounts Merge](06-AccountsMerge.java) · [LeetCode](https://leetcode.com/problems/accounts-merge/) — Solution preserves accounts and returns groups in unspecified order, with each group's emails sorted.
7. [Lexicographically Smallest Equivalent String](07-LexicographicallySmallestEquivalentString.java) · [LeetCode](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/) — Solution uses the shared UnionFind pattern and tracks each component's smallest letter separately.
8. [Most Stones Removed with Same Row or Column](08-MostStonesRemovedWithSameRowOrColumn.java) · [LeetCode](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) — Solution keeps row and column maps separate and preserves stones.
9. [Swim in Rising Water](09-SwimInRisingWater.java) · [LeetCode](https://leetcode.com/problems/swim-in-rising-water/) · [NeetCode 150](https://neetcode.io/problems/swim-in-rising-water/question?list=neetcode150) — Solution preserves grid and relies on the problem's unique bounded elevation range.
10. [Remove Max Number of Edges to Keep Graph Fully Traversable](10-RemoveMaxNumberOfEdges.java) · [LeetCode](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/) — Solution preserves edges and always executes both shared-edge unions; it does not hide the second union behind short-circuit OR.

