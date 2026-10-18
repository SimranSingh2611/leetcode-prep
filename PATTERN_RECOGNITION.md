# Pattern Recognition: 01–21 (BFS and DFS separated)

Keywords narrow the possibilities; they do not prove an algorithm is correct.
Before choosing a template, identify the state, the invariant, and why the next
move cannot discard a valid answer. A problem can combine several patterns.

[Full roadmap](./README.md)

## Quick recognition index

| Pattern | Typical wording | Decisive question |
| --- | --- | --- |
| [01 — Hash Maps and Sets](./01-hash-maps-and-sets/README.md) | Duplicates, counts, complements | Can a key summarize past elements for direct lookup? |
| [02 — Prefix Sums](./02-prefix-sums/README.md) | Range totals, subarray sums, balanced counts | Can two prefix states recover the interval condition? |
| [03 — Two Pointers](./03-two-pointers/README.md) | Sorted pairs, palindrome, in-place compaction | Can moving one pointer safely discard candidates? |
| [04 — Sliding Window](./04-sliding-window/README.md) | Contiguous longest/shortest, fixed k, at most k | Can endpoints move forward while maintaining/restoring validity? |
| [05 — Sorting-Based Techniques](./05-sorting-based-techniques/README.md) | Overlap, grouping, ordered matching | Does sorting make the needed relation local or monotonic? |
| [06 — Binary Search](./06-binary-search/README.md) | First/last boundary, minimum feasible answer | Does a predicate change truth value only once? |
| [07 — Kadane's Algorithm](./07-kadanes-algorithm/README.md) | Best contiguous sum ending here | Can one state decide extend versus restart? |
| [08 — Stacks](./08-stacks/README.md) | Nesting, undo, matching, unresolved recent item | Must the most recently opened item be resolved first? |
| [09 — Monotonic Stacks](./09-monotonic-stacks/README.md) | Next greater/smaller, span, rectangle boundaries | Does a new value resolve or dominate ordered pending candidates? |
| [10 — Linked Lists](./10-linked-lists/README.md) | Node links, reversal, cycle, kth from end | Can pointer rewiring or a pointer gap replace array indexing? |
| [11 — Recursion](./11-recursion/README.md) | Smaller instance, recursive generation, halving | What is the call contract, base case, and decreasing measure? |
| [12 — Binary Trees](./12-binary-trees/README.md) | Root, leaf, subtree, depth, paths | Can child results be combined, or levels processed in order? |
| [13 — Binary Search Trees](./13-binary-search-trees/README.md) | Ordered tree, rank, predecessor, search by value | Does the global BST property justify pruning a subtree? |
| [14 — Backtracking](./14-backtracking/README.md) | All combinations, permutations, valid placements | Can choices be tried, pruned, and undone? |
| [15 — Heaps and Priority Queues](./15-heaps-and-priority-queues/README.md) | Top k, running median, repeated best candidate | Which changing candidates belong in the heap? |
| [16 — Greedy and Intervals](./16-greedy-and-intervals/README.md) | Local assignment, reachability frontier, intervals | Can an exchange/stays-ahead proof justify the commitment? |
| [17 — Tries](./17-tries/README.md) | Prefix, autocomplete, wildcard dictionary | Can shared prefixes avoid repeated work or prune candidates? |
| [18a — Graphs: BFS](./18a-graphs-bfs/README.md) | Minimum moves, nearest source, spread, levels | Do equal-cost edges let FIFO layers finalize shortest distance? |
| [18b — Graphs: DFS](./18b-graphs-dfs/README.md) | Components, islands, enclosed regions, all paths | Is visitation global, path-local, or a memoized subproblem? |
| [19 — Topological Sort](./19-topological-sort/README.md) | Prerequisites, dependencies, strict-increase DAG | Must every predecessor be finalized before a node is processed? |
| [20 — Union-Find](./20-union-find/README.md) | Equivalence, merge groups, incremental connectivity | Do undirected relationships only merge components? |
| [21 — Intervals](./21-intervals/README.md) | Merge ranges, minimum removals, meeting rooms, point coverage, calendar bookings, delayed scheduling | Does ordering expose overlaps, compatible choices, or active candidates? |

## A practical decision process

1. **What is being selected?** A contiguous interval, arbitrary subsequence,
   pair, path, component, ordering, or complete arrangement?
2. **What is required?** Existence, one answer, all answers, a count, an optimum,
   or repeated queries? “All answers” may make exponential output unavoidable.
3. **What structure is guaranteed?** Sorted order, positive values, a fixed
   alphabet, a tree, an acyclic graph, equal edge costs, or merge-only updates?
4. **What is the bottleneck?** Repeated scans suggest caching/hash lookup;
   repeated range totals suggest prefixes; repeated best-candidate selection
   suggests a heap.
5. **What proves the optimization?** State the elimination rule, monotonicity,
   exchange argument, recurrence, or visitation invariant.
6. **Which counterexample would break it?** Try negatives, duplicates, an empty
   result, an all-negative array, a cycle, a skewed tree, or touching endpoints
   where the statement allows them.

Small constraints may permit backtracking; large constraints may rule out a
quadratic scan. Constraints help reject algorithms, but input size alone does
not identify the correct pattern.

## The distinctions that matter most

### Two pointers versus sliding window

Sliding window is a specialized two-pointer technique for a **contiguous range
with maintained state**. Two pointers also includes inward pair search,
read/write compaction, fast/slow traversal, and center expansion.

There is no one universal two-pointer template. Learn the movement proof for
each family:

- **Inward pointers:** sorted pair sums or symmetric comparisons. Move the end
  whose remaining candidates are provably unusable.
- **Read/write pointers:** the prefix before write already contains the desired
  output; read examines the next input.
- **Fast/slow pointers:** maintain a speed or distance relationship for linked
  list cycles, midpoints, or kth-from-end nodes.
- **Center expansion:** a currently valid palindrome expands symmetrically;
  consider both odd and even centers.
- **Sliding window:** add the right endpoint, adjust the left endpoint according
  to validity, and record at the correct time.

Concrete sorted-pair example (Java; returns whether two distinct indices sum to target):

```java
boolean hasPair(int[] sorted, long target) {
    int left = 0, right = sorted.length - 1;
    while (left < right) {
        long sum = (long) sorted[left] + sorted[right];
        if (sum == target) return true;
        if (sum < target) left++;
        else right--;
    }
    return false;
}
```

If the sum is too small, every remaining pair using that left value is also too
small because right holds the largest available value. The too-large case is
symmetric. Without sorted order, that proof fails.

Sliding-window outlines below are **pseudocode**, not drop-in Java:

```text
Longest valid window:
    for each right endpoint:
        add right to state
        while window is invalid:
            remove left from state; left++
        record this valid window

Shortest valid window:
    for each right endpoint:
        add right to state
        while window is valid:
            record this valid window
            remove left from state; left++
```

These outlines need problem-specific state and monotonicity. For example,
positive numbers justify the usual minimum-size sum window; arbitrary negative
numbers do not. “Subarray” alone is not enough.

### Prefix sums versus Kadane versus a sum window

- **Repeated exact range totals / target-sum counts:** try prefix sums, often
  with a map of earlier prefix frequencies.
- **Maximum nonempty contiguous sum:** try Kadane's extend-or-restart recurrence.
- **Shortest interval meeting a sum threshold with positive values:** a sliding
  window can work.
- **That same threshold with arbitrary negatives:** the simple window rule
  breaks; prefix sums plus a monotonic deque is one advanced alternative.

A substring and a subarray are contiguous; a subsequence preserves order but
may skip elements. Do not apply a window template to an arbitrary subsequence.

### Stack versus monotonic stack

A normal stack models **last-in-first-out dependency**: nested brackets,
operators, undo, or deferred frames. It need not order values.

A monotonic stack additionally orders its pending values. A new value may
resolve several earlier indices or prove them dominated. Each index is pushed
and popped at most once, so a nested pop loop can still total O(n).

Pseudocode for next **strictly greater** values:

```text
for each index i:
    while stack is nonempty and a[stack.top] < a[i]:
        j = stack.pop()
        answer[j] = a[i]
    stack.push(i)
```

Unpopped indices have no greater value to their right. Equal values are not
popped by this strict comparison. Contribution/counting problems require
careful strict/non-strict tie rules to avoid double counting.

### Recursion versus backtracking

Recursion is a way to express a smaller-instance contract. Binary exponentiation
combines a fixed half-power result; it does not search for a valid arrangement.

Backtracking explores candidate choices and restores shared state:

```text
search(state):
    if state is a complete valid answer:
        save a COPY; return
    for each legal next choice:
        apply choice
        search(smaller remaining problem)
        undo choice
```

Not all recursion is backtracking, and backtracking can be simulated iteratively.
Memoization is safe only when the cache key includes everything that determines
the remaining answer; a suffix index may suffice for segmentation but not for a
grid search whose set of already-used cells differs between paths.

### Binary trees versus BSTs

A binary tree guarantees at most two children, not any ordering of values.
Traversals, depth, subtree summaries, and path aggregation work on general
binary trees.

A BST adds a **global** ordering invariant across entire subtrees. Checking only
immediate children does not validate it. Search can prune one subtree by value;
inorder traversal produces sorted values under the problem's duplicate policy.

Balanced BST search is O(log n), but a skewed BST can take O(n). The name “BST”
does not imply balancing.

### BFS/DFS versus topological sort versus union-find

- **[BFS](./18a-graphs-bfs/README.md):** use a FIFO queue for equal-cost shortest
  paths, distance layers, or simultaneous spread. Seed every source at distance
  zero for multi-source BFS. Mark the complete state on enqueue; a cell alone is
  insufficient when remaining obstacle eliminations affect future moves.
- **[DFS](./18b-graphs-dfs/README.md):** use recursion or a LIFO stack for components,
  nested results, and path enumeration. Global visited works for reachability;
  all-path enumeration instead restores path-local state after each branch.
  Memoized DAG DFS caches a completed subproblem result. First discovery by DFS
  does not guarantee a shortest route.
- **Topological sort:** respect directed dependencies; a node becomes ready only
  after all incoming prerequisites are processed.
- **Union-find:** maintain undirected equivalence/connectivity under merges;
  it does not preserve an actual route or a shortest distance.

Either traversal can answer ordinary reachability; choose based on the required
result, not merely the word “graph.” A tree is also a graph: adding parent links
makes distance/infection questions ordinary graph traversals.

Kahn's algorithm uses a queue, but it is not ordinary shortest-path BFS. Its
queue is driven by remaining indegree. Longest Increasing Path uses dependency
layers, not first-arrival distance.

### Merging versus selecting versus counting intervals

Merging keeps the union of covered ranges; earliest-finish selection keeps the
maximum number of compatible ranges; meeting-room counting keeps overlapping
meetings separate and measures maximum concurrency. A point-coverage query
may instead need a heap ordered by interval length.

Check the statement's endpoint rule before writing comparisons: touching closed
ranges merge in Merge/Insert, while a meeting can reuse a room exactly when
another ends. Calendar bookings also use half-open `[start, end)` ranges. For
My Calendar I, disjoint accepted bookings allow ordered-map neighbor checks.
My Calendar II/III instead sweep endpoint deltas: the running prefix sum is
current concurrency. Calendar II rolls back any booking that exceeds two;
Calendar III accepts every booking and returns the maximum. Meeting Rooms III
instead simulates a fixed set of rooms: a heap of free IDs chooses the lowest
available room, while a heap ordered by finish time and ID determines delays.
The [Intervals guide](./21-intervals/README.md) compares all ten questions and
their endpoint conventions.

## What to say before coding

“I think this is **[pattern]** because **[structural fact, not only a keyword]**.
I will maintain **[invariant]**. The next move is safe because **[proof]**.
Every element/state is processed **[bound]** times, so time is **[complexity]**,
with **[auxiliary space]**, including recursion if used.”

If you cannot fill in the safety/proof sentence, test the pattern hypothesis
before memorizing its code. Each linked folder gives its own invariant,
counter-indications, prerequisites, and practice problems.
