# LeetCode Pattern Roadmap

Start with the foundational exercises in numeric order, then revisit the harder hybrids.
There are **22 pattern sections and 241 distinct problems (250 exercise entries, including 4 standalone templates)**: 76 in 01–07 (the original 70 plus six binary-search additions),
plus 174 entries in 08–21. Step 18 is split into **18a BFS** and **18b DFS**, so steps 19
and 20 keep their existing names. Step 21 collects the six Intervals questions, My Calendar I/II/III, and Meeting Rooms III.
The old combined graph directory is a navigation page.

Each pattern folder contains a recognition guide and a numbered problem list; its
`java/` folder contains a solution index and one independent file per problem.
Every exercise includes two short **Talking points** to open an interview
discussion, followed by a solution explanation and separate **Time complexity**
and **Space complexity** notes. These quick notes also appear at the top of each
Java file. Problems retain their LeetCode links, recognition cues, implementations,
and interviewer follow-ups. Selected files include alternate implementations with
their actual trade-offs.

Complexity notes describe the primary Java implementation and label alternatives
where relevant. Auxiliary space excludes the input and returned output; output
storage, recursion stacks, sorting workspace, and persistent design-class storage
are called out explicitly. Hash-based bounds are expected, and amortized bounds
describe a sequence of operations. Unless defined locally, `n` is the input size,
`h` is tree height, `w` is maximum actual level width, `R`/`C` are grid dimensions,
and `V`/`E` are graph vertex/edge counts.

[How to recognize the patterns](./PATTERN_RECOGNITION.md) · [Testing instructions](./tests/README.md)

## Learning order

| Order | Pattern | Problems | Difficulty mix |
| --- | --- | ---: | --- |
| 1 | [Hash Maps and Sets](./01-hash-maps-and-sets/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 2 | [Prefix Sums](./02-prefix-sums/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 3 | [Two Pointers](./03-two-pointers/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 4 | [Sliding Window](./04-sliding-window/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 5 | [Sorting-Based Techniques](./05-sorting-based-techniques/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 6 | [Binary Search](./06-binary-search/README.md) | 16 | 6 Easy / 8 Medium / 2 Hard |
| 7 | [Kadane's Algorithm](./07-kadanes-algorithm/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 8 | [Stacks](./08-stacks/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 9 | [Monotonic Stacks](./09-monotonic-stacks/README.md) | 18 | 2 Easy / 8 Medium / 4 Hard + 4 templates |
| 10 | [Linked Lists](./10-linked-lists/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 11 | [Recursion](./11-recursion/README.md) | 8 | 3 Easy / 5 Medium |
| 12 | [Binary Trees](./12-binary-trees/README.md) | 20 | 8 Easy / 10 Medium / 2 Hard |
| 13 | [Binary Search Trees](./13-binary-search-trees/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 14 | [Backtracking](./14-backtracking/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 15 | [Heaps and Priority Queues](./15-heaps-and-priority-queues/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 16 | [Greedy and Intervals](./16-greedy-and-intervals/README.md) | 10 | 3 Easy / 5 Medium / 2 Hard |
| 17 | [Tries](./17-tries/README.md) | 10 | 7 Medium / 3 Hard |
| 18a | [Graphs: BFS](./18a-graphs-bfs/README.md) | 14 | 12 Medium / 2 Hard |
| 18b | [Graphs: DFS](./18b-graphs-dfs/README.md) | 14 | 2 Easy / 11 Medium / 1 Hard |
| 19 | [Topological Sort](./19-topological-sort/README.md) | 10 | 6 Medium / 4 Hard |
| 20 | [Union-Find](./20-union-find/README.md) | 10 | 1 Easy / 7 Medium / 2 Hard |
| 21 | [Intervals](./21-intervals/README.md) | 10 | 1 Easy / 6 Medium / 3 Hard |

The earlier 3 Easy / 5 Medium / 2 Hard mix remains in 01–05 and 07. Binary search
retains its original ten and adds the six missing screenshot problems. In 08–21, genuine
pattern fit takes priority over that quota, as requested. Recursion has eight
focused problems; binary trees has twenty; tries and topological sort start at Medium. BFS and DFS each
have fourteen problems. Monotonic stacks has fourteen problems and four template
exercises. Intervals has the six questions from your screenshot, three
supplemental calendar problems, and Meeting Rooms III, arranged Easy → Medium → Hard. The remaining new folders have ten problems each. Ordering within one difficulty band is a learning
recommendation, not an official fine-grained LeetCode ranking.

## What builds on what

- **08 stacks → 09 monotonic stacks:** first understand last-in-first-out processing,
  then add an ordering invariant and amortized push/pop reasoning.
- **10 linked lists:** apply pointer movement to nodes, then combine lists and maps
  in cache designs. Recursive alternatives can wait until 11.
- **11 recursion → 12 binary trees → 13 BSTs:** call contracts and returned subtree
  results come before reasoning that depends on the BST ordering property.
- **11 recursion → 14 backtracking:** add choice enumeration, pruning, and undoing
  shared state. Backtracking is not a synonym for recursion.
- **15 heaps → 16 greedy and intervals:** learn dynamic candidate selection before
  offline interval-query hybrids. Greedy still requires a proof.
- **17 tries:** combines string indexing with earlier recursive/backtracking work.
  Binary tries and memoized segmentation are labelled extensions.
- **18a BFS → 18b DFS → 19 topological sort and 20 union-find:** learn distance
  layers, then depth-first components and path-local state; next distinguish
  directed dependencies from undirected equivalence/connectivity.
- **21 intervals:** review sorted-range scans, earliest-finish selection, and
  heap sweeps using the six-question Intervals category. Four questions revisit
  earlier exercises from 05/16; the two meeting-room questions are new. Then use
  My Calendar I/II/III to practice ordered maps, prefix sums over endpoint changes,
  and maintaining booking state between calls. Meeting Rooms III adds room assignment
  and delayed scheduling with two heaps.

You do not need to finish every Hard problem before advancing. Several early
stretch exercises already use later tools (for example, the monotonic deque in
02/04 or heaps in 05). Learn the basic pattern now and return to those hybrids.
Some later exercises use small memoized/DP states, explained where needed; this
expansion does not add a separate dynamic-programming section.

## Source labels and selection

- **Blind 75 + NeetCode 150:** listed in both source collections.
- **NeetCode 150:** listed in NeetCode 150, but not labelled Blind 75 in the checked data.
- **Supplemental:** an additional LeetCode exercise selected for this pattern.
  This label does not claim it is part of either curated list.
- **Monotonic-stack guide:** an exercise added from the linked guide; existing
  overlaps retain their original source labels. The monotonic-stack exercises follow a learning progression.

New problems additionally distinguish **Core list selection**, **Additional pattern
practice**, and **Advanced stretch**. A source-list label is not a popularity
ranking or a promise that a problem is frequently asked by a particular company.

The original 08–20 selections were checked on **2026-09-09**; all 28 selections
in the split graph sections were checked again on **2026-09-12**. Titles, URLs,
difficulty, and Premium status use the
[LeetCode public problem catalog](https://leetcode.com/api/problems/all/); source
membership uses the published metadata behind
[NeetCode 150](https://neetcode.io/practice/practice/neetcode150) and
[Blind 75](https://neetcode.io/practice/practice/blind75).

For the graph split, suitable NeetCode 150 questions were selected first, then
questions from your BFS/DFS screenshots. Each guide labels the source and lists
a NeetCode-first pass, while numbered order builds toward harder combinations.
All ten original graph exercises were retained. Five graph selections require
LeetCode Premium, clearly labelled per problem. The other screenshot exercises
already assigned to topological sort or union-find are cross-linked, not duplicated.
Availability and labels can change. The original 01–07 problem selections were retained; their source
labels were not comprehensively re-audited in the expansion. The 06 Java solutions
have since been updated to the [unified boundary-search convention](./06-binary-search/README.md).

The ten binary-tree additions were checked against their linked LeetCode statements
on **2026-09-12**. The [coverage checklist](./12-binary-trees/README.md#neetcode-150-coverage-first)
maps all 15 NeetCode 150 Trees questions to 12 general-tree solutions and the
three existing BST solutions. This completes that category before supplemental practice.

The [new-problem catalog snapshot](./tests/catalog.json) records the checked
metadata. Trapping Rain Water intentionally appears in both two pointers and monotonic stacks to teach both approaches.
The six core [Intervals selections](./21-intervals/README.md) come from your screenshot;
the four free statements and both meeting-room Premium gates were checked on
**2026-10-09**. Meeting-room difficulty follows the screenshot. Insert Interval,
Merge Intervals, Non-overlapping Intervals, and Minimum Interval intentionally
repeat earlier exercises to keep this category together. Meeting Rooms and
Meeting Rooms II require LeetCode Premium.
My Calendar I, II, and III were added as user-requested supplemental Intervals
practice; their free statements, difficulty, class names, and booking rules were
checked on **2026-10-17**. The primary solutions use ordered-map neighbor checks
for I and endpoint sweeps for II/III; segment trees are an optional follow-up.
Meeting Rooms III was checked on **2026-10-17** and added as supplemental Hard
practice with a two-heap scheduling simulation and `long` finishing times.
This update does not change the separate HackerRank collection.

Practice links were audited across all 22 pattern folders on **2026-10-17**.
Each LeetCode exercise has its problem URL in the Java header and Java index.
NeetCode 150 matches also have direct practice links in their Java headers,
indexes, and folder guides. LeetCode URLs were checked against the
[public problem catalog](https://leetcode.com/api/problems/all/); NeetCode 150
membership and practice URLs were checked against NeetCode's
[official repository metadata](https://raw.githubusercontent.com/neetcode-gh/leetcode/main/.problemSiteData.json)
and [published site data](https://neetcode.io/main.e881a04d25e3264a.js).
The four standalone stack templates retain their guide links.

## How to use the Java solutions

Open the problem's `java/` index. Each file is independent: repeated `Solution`
class names must **not** be compiled together into one package. Design problems
use the judge-required class name (for example, `Trie` or `MedianFinder`).
For an alternative implementation, select that class and rename it to
`Solution` if required by the judge.

LeetCode supplies `ListNode`, `TreeNode`, and problem-specific `Node` definitions.
The local test models are not part of the submission. New implementations use
Java 8-compatible language/library features; the local validation runner requires
a JDK 11 or newer. Mutation, output-storage, recursion-stack, and comparator
overflow considerations are noted per problem. Recursive versions can exhaust the
Java stack on highly skewed inputs even when their algorithmic complexity is good.

## Suggested practice loop

1. Read the statement without its pattern label; describe a brute-force solution.
2. Identify the hint, then prove the structural condition and invariant.
3. Try the implementation for 20–30 minutes before opening the solution.
4. Trace one normal case and one boundary case; state time and auxiliary space.
5. Explain the follow-ups aloud, distinguishing expected, worst-case, and amortized bounds.
6. Re-solve missed questions after 2 days, 1 week, and 3 weeks.

Run the local checks from the workspace root:

```bash
ruby -EUTF-8 leetcode-prep/tests/check_catalog.rb
bash leetcode-prep/tests/verify.sh
```

The Java suite covers 190 files (06 and 08–21) with deterministic examples, edge
cases, selected randomized/brute-force comparisons, and selected deep-structure
tests. Run only binary search with `bash leetcode-prep/tests/verify.sh 06-binary-search`.
These are local checks, not a claim that the files have been submitted to or
accepted by LeetCode's hidden judge.

Monotonic stacks uses the [requested guide](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/) with four standalone templates first, then direct applications, greedy adaptations, boundary calculations, and combined techniques. Its [Java index](./09-monotonic-stacks/java/README.md) maps each solution to a template. Buildings With an Ocean View requires Premium.
