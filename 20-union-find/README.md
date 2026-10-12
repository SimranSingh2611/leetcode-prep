# 20 — Union-Find

Union-find (disjoint-set union, DSU) maintains a partition into connected/equivalent groups. Learn find + union first, then model identities, count redundant edges, and add offline activation or multiple DSUs.

## How to recognize this pattern

**Keywords / hints:** “same group,” “connected after adding edges,” “merge accounts,” “equivalent,” “redundant connection,” “number of components,” or connectivity as a threshold increases.

**Structural test:** Are relationships undirected and transitive, and do updates only merge groups? Is the answer based on group identity/size rather than the actual path between vertices?

**Invariant / mental model:** Every element belongs to one rooted parent tree. find returns its representative; union changes the component count only when two different roots merge. Path compression and union by size keep operations nearly constant amortized time.

**When not to use it:** Ordinary DSU does not recover shortest paths, solve directed reachability, or efficiently split components after arbitrary deletions. One static reachability query can be simpler with BFS/DFS.

**Prerequisites:** [18b DFS](../18b-graphs-dfs/README.md) for graph components; 01 maps for mapping names/coordinates to elements; 16 greedy reasoning helps explain which edges to keep. Swim in Rising Water adds offline activation.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 1 Easy / 7 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Only undirected connectivity is requested; no actual path or distance is needed.

**Solution:** Initialize each vertex as its own component and union the endpoints of every edge. A path exists exactly when source and destination have the same representative. This static example is the basic DSU mechanics exercise; BFS is equally valid.

**Complexity:** O(n + E·α(n)) amortized time and O(n) auxiliary space; α is the inverse Ackermann function.

**Java:** [FindIfPathExistsInGraph](./java/01-FindIfPathExistsInGraph.java) — Solution uses iterative path halving and union by size; inputs are unchanged.

**Potential interviewer follow-ups:**

- Would DSU return the path itself? No; its parent tree represents component membership, not necessarily edges from the original graph.
- When does DSU become more attractive than BFS? With many connectivity queries interleaved with edge additions.

## 2. [Number of Provinces](https://leetcode.com/problems/number-of-provinces/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Count connected groups in an undirected adjacency matrix.

**Solution:** Start with n components. For each connected pair above the matrix diagonal, union the two cities. Decrement the component count only for successful merges. Scanning only half the symmetric matrix avoids redundant work without changing the quadratic bound.

**Complexity:** O(n²·α(n)) time as a simple DSU-operation bound; O(n) auxiliary space. The input itself already has n² entries.

**Java:** [NumberOfProvinces](./java/02-NumberOfProvinces.java) — Solution preserves the matrix; a DFS solution is also O(n²) time for this representation.

**Potential interviewer follow-ups:**

- Why can't you subtract one for every matrix edge? Edges within an already connected component do not reduce the number of components.
- What if the matrix is not symmetric? Directed reachability components are a different problem; ordinary DSU would discard direction.

## 3. [Redundant Connection](https://leetcode.com/problems/redundant-connection/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** An edge joins vertices already connected by earlier edges, so it closes an undirected cycle.

**Solution:** Process edges in input order. If union succeeds, keep the edge in the evolving forest. If the endpoints already share a representative, that edge is redundant. Under the tree-plus-one-edge guarantee, the first failed union is the last-listed edge on the unique cycle and satisfies the required tie rule.

**Complexity:** O(n·α(n)) time and O(n) auxiliary space for n edges/vertices.

**Java:** [RedundantConnection](./java/03-RedundantConnection.java) — Solution returns a cloned edge and preserves input; labels are 1-based.

**Potential interviewer follow-ups:**

- Would the same logic detect a directed redundant edge? Not by itself; directed cycles and multiple-parent constraints need different reasoning.
- What if several extra edges are present? Every failed union is redundant relative to the retained spanning forest, but a requested tie/optimization rule must be specified.

## 4. [Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Equalities form equivalence classes; inequalities must not fall within the same class.

**Solution:** First union every equality. Then check all inequalities against the final representatives. Processing inequalities before all equalities are known can miss a contradiction that appears through a later chain.

**Complexity:** O(E·α(26)+26) time and O(26) space; with the fixed lowercase alphabet these are O(E) and O(1).

**Java:** [SatisfiabilityOfEqualityEquations](./java/04-SatisfiabilityOfEqualityEquations.java) — Solution performs two passes and leaves equations unchanged.

**Potential interviewer follow-ups:**

- Why are inequalities not union operations? Inequality is not transitive: a≠b and b≠c do not imply a≠c.
- What if arbitrary string variables are used? Assign compact integer IDs with a map and run the same two-pass algorithm.

## 5. [Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Existing redundant edges may be moved to connect separate components.

**Solution:** A connected network needs at least n-1 edges, so reject if fewer are available. Union all existing edges and count c components. Exactly c-1 new connections are needed; when the total edge count is at least n-1, enough redundant edges exist to supply them because a spanning forest uses n-c edges.

**Complexity:** O(n+E·α(n)) time, O(n) auxiliary space.

**Java:** [NumberOfOperationsToMakeNetworkConnected](./java/05-NumberOfOperationsToMakeNetworkConnected.java) — Solution does not mutate connections.

**Potential interviewer follow-ups:**

- Why is the edge-count condition also sufficient? E-(n-c) redundant edges is at least c-1 when E≥n-1.
- What if some cables cannot be moved? Count only removable redundant edges; total edge count alone is no longer sufficient.

## 6. [Accounts Merge](https://leetcode.com/problems/accounts-merge/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Shared email addresses establish identity equivalence across records; equal names alone do not.

**Solution:** Use account indices as DSU elements. A map remembers the first account containing each email; union later accounts that contain it. Group unique emails by the final account root into sorted sets, then emit the root account's name followed by its emails. The problem guarantees names agree within one person's connected accounts.

**Complexity:** For A accounts, T email occurrences, and U unique emails: expected O(A+T·α(A)+U log U) time counting bounded-length keys; O(A+U) auxiliary storage, excluding output. String comparisons/hashing add their character costs.

**Java:** [AccountsMerge](./java/06-AccountsMerge.java) — Solution preserves accounts and returns groups in unspecified order, with each group's emails sorted.

**Potential interviewer follow-ups:**

- Why not merge matching names? Different people can have the same name; only shared emails establish the required relationship.
- Could you model emails rather than accounts as DSU elements? Yes; union all emails in each account and keep a separate name lookup.

## 7. [Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Transitive character equivalence should map every class to its smallest character.

**Solution:** Union each corresponding character pair. Because the alphabet has only 26 elements, make the smaller root the parent on every merge, so find directly returns the lexicographically smallest representative. Replace each base-string character with that representative.

**Complexity:** O(26·(P+B)) as a simple bound for P equivalence pairs and base length B; the fixed alphabet makes this O(P+B) time and O(1) auxiliary DSU space, excluding output.

**Java:** [LexicographicallySmallestEquivalentString](./java/07-LexicographicallySmallestEquivalentString.java) — Solution deliberately chooses the minimum root instead of union by size. For large universes, use size/rank plus separate minimum metadata.

**Potential interviewer follow-ups:**

- Does choosing the smallest root preserve the usual union-by-rank guarantee? No; it is acceptable here because the alphabet is fixed and tiny.
- How do you preserve both balancing and a minimum representative? Keep minValue at each root and update it during a size-based union.

## 8. [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Stones sharing a row or column form connected groups; each group must leave one stone.

**Solution:** Treat stones as DSU elements. Maps remember one representative stone for each row and each column, allowing unions without comparing every pair. In a connected component of size s, remove s-1 stones by working from leaves of a spanning tree; one stone must remain. Sum over groups as n-components.

**Complexity:** Expected O(n·α(n)) time and O(n) auxiliary space.

**Java:** [MostStonesRemovedWithSameRowOrColumn](./java/08-MostStonesRemovedWithSameRowOrColumn.java) — Solution keeps row and column maps separate and preserves stones.

**Potential interviewer follow-ups:**

- Why can't the last stone in a component be removed? There is no remaining stone in its row/column component to justify that removal.
- Why is comparing all stone pairs unnecessary? Sharing a representative for each row and column connects the same groups using only O(n) union attempts.

## 9. [Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** As a threshold rises, more cells become usable and connected components only merge.

**Solution:** Use the unique elevations 0..n²-1 to index each cell's position. Activate cells in increasing elevation and union each with its active neighbors. The first threshold at which the start and end are active and connected is the minimum feasible water level. This is offline connectivity, not ordinary shortest-path BFS.

**Complexity:** O(n²·α(n²)) time and O(n²) auxiliary space. Direct indexing avoids sorting because the given elevations are a permutation of 0..n²-1.

**Java:** [SwimInRisingWater](./java/09-SwimInRisingWater.java) — Solution preserves grid and relies on the problem's unique bounded elevation range.

**Potential interviewer follow-ups:**

- What if elevations are arbitrary or duplicated? Sort cells by elevation and activate in nondecreasing order; process equal-height cells consistently.
- What is the shortest-path alternative? Dijkstra can minimize the maximum elevation along a path; binary search plus a reachability check also exploits threshold monotonicity.

## 10. [Remove Max Number of Edges to Keep Graph Fully Traversable](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Two users need separate connected graphs, but shared edges can advance both connectivity goals at once.

**Solution:** Keep one DSU for Alice and one for Bob. Process shared type-3 edges first, keeping only successful component merges and applying each to both DSUs. Their partitions are identical during this phase, so each kept shared edge saves one edge for both users. Then process private type-1/type-2 edges. If both graphs become connected, every unused edge can be removed.

**Complexity:** O(n+E·α(n)) time and O(n) auxiliary space for two DSUs; no sorting is needed.

**Java:** [RemoveMaxNumberOfEdges](./java/10-RemoveMaxNumberOfEdges.java) — Solution preserves edges and always executes both shared-edge unions; it does not hide the second union behind short-circuit OR.

**Potential interviewer follow-ups:**

- Why must shared edges come first? A useful shared edge can replace one private edge in each user's spanning structure; taking private edges first may waste that double benefit.
- Why check both final component counts? Enough edges overall does not guarantee that each user can access the required connections.
