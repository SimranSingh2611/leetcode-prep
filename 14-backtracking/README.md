# 14 — Backtracking

Recursion describes how calls are organized; backtracking explores choices, rejects invalid partial candidates, and restores state before trying siblings. Keep these separate from the single-answer recursive decompositions in 11.

## How to recognize this pattern

**Keywords / hints:** “all combinations,” “all permutations,” “generate every valid…,” “place,” “partition,” “choose without reusing,” or a small search space with local constraints.

**Structural test:** Can a solution be built one choice at a time, and can you reject a partial candidate before enumerating all completions? Define the choice set, stopping condition, and state to undo.

**Invariant / mental model:** At entry, the current path is a valid partial candidate. After a recursive branch returns, shared state is exactly what it was before that choice.

**When not to use it:** Do not enumerate exponentially many candidates when only a count or optimum is needed and overlapping states can be merged by dynamic programming. A shortest unweighted path usually calls for BFS, not DFS enumeration.

**Prerequisites:** 11 recursion; 12 tree traversal for Binary Tree Paths. Hash sets/arrays represent used choices. Bit masks in Sudoku and the Subset XOR shortcut are explained locally.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Binary Watch](https://leetcode.com/problems/binary-watch/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Choose exactly turnedOn LEDs out of a fixed set of ten; reject hour/minute combinations outside the clock range.

**Solution:** Assign each LED a weight and recursively choose the next LED from a higher index. This prevents choosing an LED twice or generating different orders of the same selection. Emit a formatted time when no choices remain.

**Complexity:** At most O(2^10) search states, a fixed bound; O(10) call-stack space excluding the output.

**Java:** [BinaryWatch](./java/01-BinaryWatch.java) — Solution uses choose/recurse without mutable shared path state.

**Potential interviewer follow-ups:**

- What prevents duplicate times? Every LED subset is generated in increasing index order, and each valid time has a unique binary encoding.
- Could you scan all possible times instead? Test the bit counts of all 12 × 60 times; the fixed domain makes both approaches practical.

## 2. [Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Every element offers two choices: include it in the subset or exclude it.

**Solution:** Carry the current XOR down an include/exclude decision tree and sum the leaf values. The optimized solution observes that each bit appearing anywhere is set in exactly half of all subsets: pairing subsets by toggling one element that has that bit proves the claim.

**Complexity:** Enumeration: O(2^n) time, O(n) stack. Bit-contribution shortcut: O(n) time, O(1) auxiliary space. LeetCode's small bounds keep the int result safe.

**Java:** [SumOfAllSubsetXORTotals](./java/02-SumOfAllSubsetXORTotals.java) — Solution teaches backtracking; BitContributionSolution is the faster follow-up. Neither mutates nums.

**Potential interviewer follow-ups:**

- Why is a present bit set in half the subsets? Toggle a fixed element containing that bit to pair each XOR-zero subset with an XOR-one subset for that bit.
- Would the shortcut work for sums of subset AND values? Re-derive per-bit contributions: only subsets using exclusively elements with that bit set contribute.

## 3. [Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Enumerate root-to-leaf paths, maintaining a path that is local to the current branch.

**Solution:** Append the current value to a shared StringBuilder. At a leaf copy the complete path into the answer. Save and restore the builder length around each call so a sibling never inherits the previous branch's values.

**Complexity:** O(n + Z) time, where Z is the total number of output characters; O(h) stack plus the current path's characters, excluding output.

**Java:** [BinaryTreePaths](./java/03-BinaryTreePaths.java) — Solution restores its path buffer and does not mutate the tree.

**Potential interviewer follow-ups:**

- What changes if you only need whether a target-sum path exists? Carry a remaining sum and stop on the first valid leaf rather than collecting paths.
- Why must the builder be restored? Siblings share the same object; without undoing, later paths contain earlier branches.

## 4. [Subsets](https://leetcode.com/problems/subsets/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** All subsets of distinct elements: order within a subset should not create separate answers.

**Solution:** Record the current subset, then try each next index from start onward. Recurse with i + 1 so elements cannot be reused. Remove the just-added element before trying the next candidate; copy the list when saving an answer.

**Complexity:** O(n · 2^n) time and output space; O(n) auxiliary stack/path space.

**Java:** [Subsets](./java/04-Subsets.java) — Solution leaves nums unchanged and saves independent list copies.

**Potential interviewer follow-ups:**

- What if nums contains duplicates? Sort a copy and skip equal candidates at the same recursion depth, not at every depth.
- Can subsets be streamed? Emit to a callback instead of retaining the output, reducing retained auxiliary storage to O(n).

## 5. [Permutations](https://leetcode.com/problems/permutations/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Order matters, but each input element must be used exactly once.

**Solution:** At each depth try every unused index. Mark it, append its value, recurse, then undo both changes. A path of length n is one complete permutation.

**Complexity:** O(n · n!) time/output space; O(n) auxiliary state and stack.

**Java:** [Permutations](./java/05-Permutations.java) — Solution uses a used-index array and does not mutate nums.

**Potential interviewer follow-ups:**

- Can you eliminate the used array? Swap each candidate into the current position and swap back; the recursion stack still uses O(n).
- How do duplicate values change the algorithm? Sort and skip an equal value when its preceding equal value has not been used on the current path.

## 6. [Combination Sum](https://leetcode.com/problems/combination-sum/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Generate combinations reaching a target; a positive candidate may be used repeatedly, and order does not distinguish answers.

**Solution:** Sort a copy. Choose candidates in nondecreasing index order, recurring with the same index to permit reuse. Stop at remaining == 0; break once a candidate exceeds the remaining target. Positivity makes every recursive choice move toward termination.

**Complexity:** Let n be the number of candidates, D = target/minCandidate, and Z the total output size. A loose bound is O(n log n + n^(D+1) + Z) time, with O(n + D) auxiliary space excluding output; the search is exponential.

**Java:** [CombinationSum](./java/06-CombinationSum.java) — Solution sorts a clone and leaves the caller's candidates unchanged.

**Potential interviewer follow-ups:**

- What if each candidate may be used only once? Recurse with i + 1 and skip duplicate candidates at the same depth.
- Can zero or negative candidates be allowed unchanged? No: unlimited reuse can prevent termination or create infinitely many combinations; extra constraints are needed.

## 7. [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Choose cut positions so every resulting substring is a palindrome; return every valid partition.

**Solution:** Precompute whether s[i..j] is a palindrome using matching endpoints and the inner substring. Backtrack over the next cut, accepting only palindromic pieces. Reaching the end records one partition. The boolean table is a small dynamic-programming helper; the main task remains enumeration.

**Complexity:** O(n² + n · 2^n) time including string/output copying; O(n²) auxiliary table plus O(n) recursion/path space, excluding output.

**Java:** [PalindromePartitioning](./java/07-PalindromePartitioning.java) — Solution uses a precomputed palindrome table; substring objects are copied into saved partitions.

**Potential interviewer follow-ups:**

- Could you avoid the table? Check each candidate with two pointers, trading O(n²) table space for repeated palindrome checks.
- What if only the fewest cuts are required? Replace enumeration with an optimization DP over prefix cut positions.

## 8. [Word Search](https://leetcode.com/problems/word-search/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Find a word by adjacent grid moves, but a cell cannot be reused within the current path.

**Solution:** Start a DFS from each matching cell. Temporarily replace the current character with a sentinel, search its four neighbors for the next letter, and restore the character before returning, including on success. Visited state belongs to one candidate path, not to the entire search.

**Complexity:** For R×C cells and word length L, O(RC · 3^L) worst-case time (the first step has up to four choices); O(min(L, RC)) recursion space. No separate visited matrix.

**Java:** [WordSearch](./java/08-WordSearch.java) — Solution temporarily mutates board but restores every cell before returning.

**Potential interviewer follow-ups:**

- Why is one global visited set wrong? A cell rejected in one path may belong to a different valid path; only the current path forbids reuse.
- What changes when searching for many words? A trie shares prefix exploration across words; see Word Search II in 17.

## 9. [N-Queens](https://leetcode.com/problems/n-queens/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Place one queen per row while maintaining column and diagonal constraints; enumerate all valid boards.

**Solution:** A recursive level chooses the column for one row. Three boolean arrays track occupied columns, descending diagonals r-c+n-1, and ascending diagonals r+c. Mark all three, recurse, and unmark. Build strings only for complete placements.

**Complexity:** A conservative bound for this column-scanning implementation is O(n · n! + S · n²) time for S solutions; O(n) auxiliary state/stack, excluding O(S · n²) output.

**Java:** [NQueens](./java/09-NQueens.java) — Solution stores column choices and materializes boards only at leaves.

**Potential interviewer follow-ups:**

- Why do diagonal indices work? Cells share r-c on one diagonal direction and r+c on the other.
- Can you exploit symmetry? Mirror placements across the vertical axis, handling the middle column separately for odd n; counting and enumerating need different output handling.

## 10. [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Fill constrained empty cells; a locally valid choice may need to be undone after a later contradiction.

**Solution:** Track digits already used in each row, column, and 3×3 box with nine-bit masks. At each step choose the remaining cell with the fewest legal digits (MRV), then try each allowed bit. Mark a digit and recurse; undo on failure. The low-bit expression mask & -mask extracts one candidate.

**Complexity:** For E empty cells, a loose bound is O(E · 9^E) time including the MRV scan at each state; O(E) recursion/empty-cell storage plus fixed-size masks. MRV prunes practice cases but does not remove exponential worst-case behavior.

**Java:** [SudokuSolver](./java/10-SudokuSolver.java) — Solution fills board in place; assumes the problem's valid puzzle with a unique solution.

**Potential interviewer follow-ups:**

- Why choose the most constrained cell first? It exposes contradictions earlier and reduces branching, without changing which solutions are legal.
- What if uniqueness is not guaranteed? Continue after the first solution and count up to two, copying the first solved board before undoing it.

