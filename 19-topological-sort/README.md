# 19 — Topological Sort

Topological sorting orders a directed acyclic graph so every prerequisite comes before its dependent. Start with feasibility and ordering, then propagate richer information through that order. This topic naturally uses Medium and Hard problems, not a forced Easy quota.

## How to recognize this pattern

**Keywords / hints:** “prerequisite,” “dependency,” “must happen before,” “build order,” “all required ingredients,” “directed acyclic graph,” or strict-increase transitions that cannot form cycles.

**Structural test:** Can you orient each relationship from prerequisite to dependent? Is the task about an order, detecting cycles, or combining predecessor results after all predecessors are finalized?

**Invariant / mental model:** Kahn's queue contains vertices with zero remaining unmet prerequisites. Removing a vertex decrements each outgoing neighbor's remaining indegree; a vertex is processed only after its prerequisites.

**When not to use it:** Do not topologically sort undirected connectivity problems. If cycles are allowed and meaningful, an ordinary topological order does not exist. Missing external resources are not automatically available merely because they have no incoming graph edges.

**Prerequisites:** [18a BFS](../18a-graphs-bfs/README.md) for adjacency lists/queues and [18b DFS](../18b-graphs-dfs/README.md) for traversal state; 01 sets/maps. Java BitSet is introduced for ancestor sets. The harder DAG aggregation exercises use small dynamic-programming states explained locally; revisit after DP if needed.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 6 Medium / 4 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Course Schedule](https://leetcode.com/problems/course-schedule/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Determine whether all directed prerequisite constraints can be satisfied; a cycle makes completion impossible.

**Solution:** For each [course, prerequisite], add prerequisite → course and increment the course's indegree. Queue all zero-indegree courses, remove them, and unlock dependents. Completion is possible exactly when the processed count equals numCourses.

**Complexity:** O(V+E) time and O(V+E) adjacency/indegree/queue space.

**Java:** [CourseSchedule](./java/01-CourseSchedule.java) — Solution preserves prerequisites. Pay attention to the pair direction; later Course Schedule variants use a different pair convention.

**Potential interviewer follow-ups:**

- Why does an unfinished remainder imply a cycle? In a finite remaining graph where every vertex has an unmet incoming edge, following predecessors must eventually revisit a vertex.
- Can DFS detect the same issue? Use unseen/active/finished states; an edge to an active vertex is a directed back edge.

## 2. [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Return one valid dependency order, not merely whether one exists.

**Solution:** Run Kahn's algorithm, appending each dequeued course to the result. An indegree-zero course can safely come next because every prerequisite has already been placed. Return an empty array if fewer than all courses are processed.

**Complexity:** O(V+E) time and O(V+E) auxiliary graph/queue space, excluding output.

**Java:** [CourseScheduleII](./java/02-CourseScheduleII.java) — Solution returns any valid order and leaves input unchanged.

**Potential interviewer follow-ups:**

- How would you return the lexicographically smallest order? Use a min-heap instead of a FIFO queue, at O(E + V log V) time.
- When is the topological order unique? There must be exactly one available zero-indegree vertex at every step, and all vertices must be processed.

## 3. [Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A node is safe only if every path from it eventually terminates; work backward from terminal nodes.

**Solution:** Reverse all edges and track each original node's outgoing count. Terminal nodes are initially safe. Removing a safe node reduces its predecessors' unresolved outgoing counts; a predecessor becomes safe when all of its outgoing neighbors are safe. Nodes trapped in or able to reach a cycle are never removed.

**Complexity:** O(V+E) time and O(V+E) auxiliary space; scanning the final safe flags gives sorted output without sorting.

**Java:** [FindEventualSafeStates](./java/03-FindEventualSafeStates.java) — Solution preserves graph and returns safe node indices in ascending order.

**Potential interviewer follow-ups:**

- Why is reaching one terminal node insufficient? Safety quantifies over every possible path, so another branch into a cycle makes the node unsafe.
- Could DFS solve it? Yes; track active recursion states and memoize whether all descendants are safe.

## 4. [Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A recipe becomes available only after all its ingredients are actually available; supplies seed the process.

**Solution:** Map each ingredient to recipes waiting on it and count each recipe's missing ingredients. Queue initial supplies, then decrement waiting counts as items become available. When a recipe's count reaches zero, output it and enqueue its name as a newly available ingredient. Unavailable raw ingredients and dependency cycles remain unresolved.

**Complexity:** Expected O(I+R+S) time and space for ingredient references I, recipes R, and supplies S, treating bounded-length names as keys.

**Java:** [FindAllPossibleRecipesFromGivenSupplies](./java/04-FindAllPossibleRecipesFromGivenSupplies.java) — Solution preserves inputs. The queue starts with actual supplies, not every graph node of indegree zero.

**Potential interviewer follow-ups:**

- Why isn't ordinary indegree-zero initialization enough? An unsupplied raw ingredient has no recipe prerequisites but is still unavailable.
- What changes for finite ingredient quantities? Availability becomes a resource-allocation problem; a boolean available/unavailable state no longer captures consumption.

## 5. [Course Schedule IV](https://leetcode.com/problems/course-schedule-iv/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Many indirect prerequisite queries on a DAG; precompute reachability while processing dependencies in order.

**Solution:** Here each pair [a,b] means a → b. Keep reach[p][v] indicating that p is a prerequisite of v. For every edge u → v, add u and every known prerequisite of u to v's prerequisites. Kahn's order ensures u's prerequisite set is complete before it is propagated.

**Complexity:** O(V² + EV + Q) time and O(V²+E) auxiliary space for Q queries, excluding output.

**Java:** [CourseScheduleIV](./java/05-CourseScheduleIV.java) — Solution assumes the problem's acyclic graph guarantee. Its pair direction differs from Course Schedule I/II.

**Potential interviewer follow-ups:**

- Could Floyd–Warshall answer the same queries? Yes, with O(V³) preprocessing and O(V²) space; it also handles general directed reachability.
- What if the graph changes between queries? Cached transitive closure needs updates; recomputation or dynamic reachability methods may be required.

## 6. [All Ancestors of a Node in a Directed Acyclic Graph](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Propagate whole ancestor sets through a DAG, then list each node's ancestors in sorted order.

**Solution:** Represent each ancestor set as a BitSet. On edge u → v, OR u's complete ancestor set into v and set u's bit. Process vertices topologically, so no predecessor information arrives after v is processed. Iterating set bits in increasing index order produces sorted answers.

**Complexity:** O(V+E + EV/w + V²) worst-case time including output enumeration, where w is the machine-word bit width; O(V²/w + V+E) auxiliary machine words, excluding potentially O(V²) output.

**Java:** [AllAncestorsOfANodeInADAG](./java/06-AllAncestorsOfANodeInADAG.java) — Solution uses Java BitSet for compact set unions and preserves edges.

**Potential interviewer follow-ups:**

- Why must u's ancestor set be complete before propagation? A missing ancestor discovered afterward would never reach v unless the edge were processed again.
- When would repeated reverse BFS be simpler? For small graphs or only a few queried vertices; full all-ancestor output benefits from shared propagation.

## 7. [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Strictly increasing moves create an implicit DAG; longest path length becomes the number of dependency layers.

**Solution:** Orient each neighboring unequal pair from the smaller value to the larger. A cell's indegree is its count of smaller neighbors. Start with local minima and peel the graph in complete Kahn layers. A cell waits for every smaller predecessor, so its removal layer is one plus the largest predecessor layer, exactly the longest increasing path ending there.

**Complexity:** O(RC) time and O(RC) auxiliary indegree/queue space; edges are generated from four neighbors, not stored explicitly.

**Java:** [LongestIncreasingPathInAMatrix](./java/07-LongestIncreasingPathInAMatrix.java) — Solution is iterative and preserves matrix; strict inequality is essential for acyclicity.

**Potential interviewer follow-ups:**

- Why is this longest-path layering different from shortest-path BFS? A node is released only after all predecessors, not on its first discovery.
- Can DFS with memoization solve it? Yes; cache the longest increasing continuation from each cell, while accounting for potentially deep recursion.

## 8. [Parallel Courses III](https://leetcode.com/problems/parallel-courses-iii/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Prerequisite tasks have durations and unlimited parallel execution; the finish time is a weighted DAG critical path.

**Solution:** Initialize each course's earliest finish to its own duration. Process prerequisites topologically. For edge u → v, update finish[v] with max(finish[v], finish[u]+time[v]). A course can start only after its latest-finishing prerequisite, and independent courses can overlap. The largest finish is the total minimum completion time.

**Complexity:** O(V+E) time and O(V+E) auxiliary space.

**Java:** [ParallelCoursesIII](./java/08-ParallelCoursesIII.java) — Solution normalizes the problem's 1-based course labels. This is topological ordering plus a one-number-per-node DP; inputs are unchanged.

**Potential interviewer follow-ups:**

- Why take the maximum predecessor finish rather than the sum? Prerequisites can run in parallel; only the latest one delays the dependent.
- What if at most k courses can run simultaneously? Resource contention invalidates this critical-path-only solution and requires a harder scheduling model.

## 9. [Build a Matrix With Conditions](https://leetcode.com/problems/build-a-matrix-with-conditions/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Two independent sets of precedence constraints determine row order and column order.

**Solution:** Topologically sort row conditions and column conditions separately. If either has a cycle, no matrix is possible. Otherwise assign each value its row rank and column rank and place it at that unique coordinate. Different values have different row ranks and different column ranks, so no collision occurs.

**Complexity:** O(k + E_row + E_col + k²) time including output initialization; O(k+E_row+E_col) auxiliary space excluding the k×k matrix.

**Java:** [BuildAMatrixWithConditions](./java/09-BuildAMatrixWithConditions.java) — Solution preserves conditions and returns any valid matrix; an empty matrix signals infeasibility.

**Potential interviewer follow-ups:**

- Why can the row and column constraints be solved independently? Each constrains only one coordinate, and using permutations for both coordinates prevents collisions.
- What if additional conditions bind row and column jointly? Independent topological orders may no longer be sufficient.

## 10. [Largest Color Value in a Directed Graph](https://leetcode.com/problems/largest-color-value-in-a-directed-graph/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Maximize a color's frequency along a directed path; detect cycles and propagate a vector of best path statistics.

**Solution:** Maintain best[v][c], the largest count of color c on any path ending at v. Start each vertex with one count of its own color. In topological order, propagate each of the 26 counts over u → v, adding one if v has that color. Take the largest count seen, but return -1 if Kahn's processed count reveals any cycle.

**Complexity:** O(26(V+E)) time and O(26V+E) auxiliary space.

**Java:** [LargestColorValueInADirectedGraph](./java/10-LargestColorValueInADirectedGraph.java) — Solution is a topological-sort + vector-DP stretch problem. Each color's best count may come from a different path; the vector is not one shared physical path.

**Potential interviewer follow-ups:**

- Why can't a single best number per vertex work? A path with fewer current occurrences of one color may have more of the color that later dominates.
- What if only one target color matters? Keep one scalar per vertex instead of 26 counters, while retaining cycle detection.
