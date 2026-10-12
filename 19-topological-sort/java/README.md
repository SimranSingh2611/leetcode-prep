# Java Solutions — 19 — Topological Sort

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Course Schedule](01-CourseSchedule.java) — Solution preserves prerequisites. Pay attention to the pair direction; later Course Schedule variants use a different pair convention.
2. [Course Schedule II](02-CourseScheduleII.java) — Solution returns any valid order and leaves input unchanged.
3. [Find Eventual Safe States](03-FindEventualSafeStates.java) — Solution preserves graph and returns safe node indices in ascending order.
4. [Find All Possible Recipes from Given Supplies](04-FindAllPossibleRecipesFromGivenSupplies.java) — Solution preserves inputs. The queue starts with actual supplies, not every graph node of indegree zero.
5. [Course Schedule IV](05-CourseScheduleIV.java) — Solution assumes the problem's acyclic graph guarantee. Its pair direction differs from Course Schedule I/II.
6. [All Ancestors of a Node in a Directed Acyclic Graph](06-AllAncestorsOfANodeInADAG.java) — Solution uses Java BitSet for compact set unions and preserves edges.
7. [Longest Increasing Path in a Matrix](07-LongestIncreasingPathInAMatrix.java) — Solution is iterative and preserves matrix; strict inequality is essential for acyclicity.
8. [Parallel Courses III](08-ParallelCoursesIII.java) — Solution normalizes the problem's 1-based course labels. This is topological ordering plus a one-number-per-node DP; inputs are unchanged.
9. [Build a Matrix With Conditions](09-BuildAMatrixWithConditions.java) — Solution preserves conditions and returns any valid matrix; an empty matrix signals infeasibility.
10. [Largest Color Value in a Directed Graph](10-LargestColorValueInADirectedGraph.java) — Solution is a topological-sort + vector-DP stretch problem. Each color's best count may come from a different path; the vector is not one shared physical path.

