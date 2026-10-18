# Local validation

The Java suite covers **190 Java files: 16 in 06 and 174 in 08–21 (including 4 standalone templates)**.
Folders 01–05 and 07 are not part of the compilation/sample suite; the static
inventory still checks all 22 pattern sections for problem counts, recognition sections,
and unexpected duplicate URLs. The metadata snapshot covers only 08–21.

## Run

From the workspace root:

```bash
ruby -EUTF-8 leetcode-prep/tests/check_catalog.rb
bash leetcode-prep/tests/verify.sh
```

To run only the revised binary-search solutions:

```bash
bash leetcode-prep/tests/verify.sh 06-binary-search
```

To run all 20 binary-tree solutions:

```bash
bash leetcode-prep/tests/verify.sh 12-binary-trees
```

To run either graph section:

```bash
bash leetcode-prep/tests/verify.sh 18a-graphs-bfs
bash leetcode-prep/tests/verify.sh 18b-graphs-dfs
```

The optional argument is one supported folder name; omit it to run the full suite.

To run all ten Intervals questions, including the calendar series and Meeting Rooms III:

```bash
bash leetcode-prep/tests/verify.sh 21-intervals
```

Requirements: Ruby 2.5+ for the static checker, and **JDK 11+** (`java` and
`javac` on PATH) for the Java runner. No downloads or external libraries are used.

## What is checked

- Steps 01–21 with 18 split into 18a/18b: 22 pattern sections, 241 unique LeetCode
  problem slugs (250 entries, including 4 standalone templates, the intentional two-pointer/stack repeat of Trapping Rain Water, and four Intervals reviews from 05/16), and the intended per-folder counts. The old combined graph
  directory contains navigation only and is excluded from exercise counts.
- New README entries against the [catalog snapshot](./catalog.json): ID, title,
  URL, difficulty, source label, Premium/free status, Java filename, and case file.
- Recognition sections, solution/complexity notes, and at least two follow-ups
  on every new problem.
- Relative Markdown file/directory links (not remote URL availability or anchors).
- Each covered Java file compiled independently with Java 8 compatibility enforced
  (`--release 8`), [local judge models](./Models.java),
  and its corresponding test case.
- Examples and edge cases, selected seeded randomized comparisons, exhaustive
  small checks, and selected deep-tree/grid tests. Randomized quickselect uses
  a random pivot, but its checked input arrays and expected answers are fixed.
- Binary-search checks cover sentinel/absent answers, small rotations, timestamp
  predecessor queries, and integer-limit API versions. Median tests compare all
  nonempty pairs of sorted arrays of lengths 0–5 over values -2…2 against a sorted
  merge; split-array tests enumerate every cut set on selected small inputs.
  First Bad Version has a test-only VersionControl stub in its case file.
  The six screenshot additions check occurrence ranges/majorities against direct
  counts, any-peak validity over exhaustive small unequal-adjacent arrays,
  triangular-number boundaries, every singleton position in generated paired
  arrays, and strict next-letter/wraparound queries. Integer extremes and input
  preservation are included where relevant.

Binary-tree additions check off-root diameters, internal balance failures, exact
subtree/mirror structure, leaf-only path matching, zigzag placement, and independent
output lists. Path Sum III is compared with direct path enumeration on 300 seeded
random trees and checked for prefix overflow and sibling isolation. Flattening
checks original node identity and repeated calls, including a 2,000-node chain.
Width checks virtual gaps, a 3,000-node chain, and a width of Integer.MAX_VALUE.

[Verify.java](./Verify.java) isolates each problem's class loader so repeated
`Solution` and alternative class names cannot collide. [verify.sh](./verify.sh)
creates a fresh temporary build directory and removes only that generated
directory on exit; it leaves no class files in the study folders.

Graph checks additionally include exhaustive small island/border boards, seeded
traversal comparisons, deep tree/grid cases, and full-state versus dominance-pruned
obstacle-elimination BFS. The test-only `Node` combines fields needed by several
different LeetCode APIs; use the judge's own graph/N-ary/next-pointer definition
when submitting. `NestedInteger` and `TestNested` model nested-list inputs locally.

The source snapshot records metadata checked on 2026-09-09, with the 28 graph
selections rechecked on 2026-09-12. The ten binary-tree additions have per-problem
check dates of 2026-09-12; these are not a live availability promise.
These tests do not prove all edge cases, benchmark judge resource limits, or
claim acceptance by LeetCode's hidden judge. Do not submit the local model/test
classes to LeetCode.

Run the 18 monotonic-stack exercises with `bash leetcode-prep/tests/verify.sh 09-monotonic-stacks`. The guide additions have metadata check dates of 2026-09-23. Tests include exhaustive small 132-pattern arrays and seeded brute-force comparisons for ocean views, trapped water, visible people, and car fleets.

Next Greater, Previous Greater, Next Smaller, and Previous Smaller have separate template metadata (no invented LeetCode IDs). Their tests compare nearest qualifying indices against direct scans over exhaustive small arrays, including duplicates, empty input, and integer extremes.

The six core Intervals selections come from the user screenshot; four free statements and both meeting-room
Premium gates were checked on 2026-10-09. Meeting-room difficulty follows the
screenshot. Its cases check touching/nested/duplicate ranges, query order and
expiration, input preservation, and output-row independence. Seeded small-input
comparisons use overlap components for Merge, exhaustive compatible subsets for
Non-overlapping Intervals, direct conflict checks for Meeting Rooms, direct
concurrency counts for Meeting Rooms II, and direct coverage scans for Minimum
Interval.

My Calendar I/II/III were checked against their free statements on 2026-10-17.
Their cases cover sequential acceptance, duplicate/nested/touching bookings,
both neighbor conflicts, rollback after repeated rejections, independent calendar
instances, endpoints at 0 and `10^9`, and the stated call limits. Seeded comparisons
use direct interval intersections for Calendar I and a small per-time-unit
occupancy array for II/III, independently of the ordered-map implementations.
All ten Intervals exercises, catalog entries, and corresponding case files use
Easy → Medium → Hard order, with a recommended study order within each band.

Meeting Rooms III's free statement and scheduling rules were checked on 2026-10-17.
Its cases cover both examples, room-ID and usage ties, reuse at equal endpoints,
unsorted input preservation, repeated solution calls, and 500 seeded comparisons
against a direct room-availability scan. A 20,000-meeting case keeps every input
timestamp within the statement's limits while delayed finishing times exceed
`Integer.MAX_VALUE`; using `int` for those times changes the winning room.
