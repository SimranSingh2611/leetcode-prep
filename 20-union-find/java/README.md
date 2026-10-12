# Java Solutions — 20 — Union-Find

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Find if Path Exists in Graph](01-FindIfPathExistsInGraph.java) — Solution uses iterative path halving and union by size; inputs are unchanged.
2. [Number of Provinces](02-NumberOfProvinces.java) — Solution preserves the matrix; a DFS solution is also O(n²) time for this representation.
3. [Redundant Connection](03-RedundantConnection.java) — Solution returns a cloned edge and preserves input; labels are 1-based.
4. [Satisfiability of Equality Equations](04-SatisfiabilityOfEqualityEquations.java) — Solution performs two passes and leaves equations unchanged.
5. [Number of Operations to Make Network Connected](05-NumberOfOperationsToMakeNetworkConnected.java) — Solution does not mutate connections.
6. [Accounts Merge](06-AccountsMerge.java) — Solution preserves accounts and returns groups in unspecified order, with each group's emails sorted.
7. [Lexicographically Smallest Equivalent String](07-LexicographicallySmallestEquivalentString.java) — Solution deliberately chooses the minimum root instead of union by size. For large universes, use size/rank plus separate minimum metadata.
8. [Most Stones Removed with Same Row or Column](08-MostStonesRemovedWithSameRowOrColumn.java) — Solution keeps row and column maps separate and preserves stones.
9. [Swim in Rising Water](09-SwimInRisingWater.java) — Solution preserves grid and relies on the problem's unique bounded elevation range.
10. [Remove Max Number of Edges to Keep Graph Fully Traversable](10-RemoveMaxNumberOfEdges.java) — Solution preserves edges and always executes both shared-edge unions; it does not hide the second union behind short-circuit OR.

