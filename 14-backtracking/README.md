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

**Talking points:**

- I'd choose LED positions in increasing index order so each selection appears once.
- Only selections producing a valid hour and minute become formatted answers.

**Solution:** Assign each LED a weight and recursively choose the next LED from a higher index. This prevents choosing an LED twice or generating different orders of the same selection. Emit a formatted time when no choices remain.

**Complexity:**

**Time complexity:** A fixed bound of `O(2^10)` search states, so `O(1)` with ten LEDs.

**Space complexity:** `O(10)` stack, hence `O(1)` auxiliary space, excluding the fixed-size
output set.

**Java:** [BinaryWatch](./java/01-BinaryWatch.java) — Solution uses choose/recurse without mutable shared path state.

**Potential interviewer follow-ups:**

- What prevents duplicate times? Every LED subset is generated in increasing index order, and each valid time has a unique binary encoding.
- Could you scan all possible times instead? Test the bit counts of all 12 × 60 times; the fixed domain makes both approaches practical.

## 2. [Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Every element offers two choices: include it in the subset or exclude it.

**Talking points:**

- I'd branch on including or excluding each value while carrying the current XOR.
- Summing at the leaves covers every subset; the follow-up uses the fact that each present bit
  appears in half the subsets.

**Solution:** Carry the current XOR down an include/exclude decision tree and sum the leaf values. The optimized solution observes that each bit appearing anywhere is set in exactly half of all subsets: pairing subsets by toggling one element that has that bit proves the claim.

**Complexity:**

**Time complexity:** Primary enumeration: `O(2^n)` for `n` values; bit-contribution alternative:
`O(n)`.

**Space complexity:** Primary: `O(n)` recursive stack; alternative: `O(1)` auxiliary space.

**Java:** [SumOfAllSubsetXORTotals](./java/02-SumOfAllSubsetXORTotals.java) — Solution teaches backtracking; BitContributionSolution is the faster follow-up. Neither mutates nums.

**Potential interviewer follow-ups:**

- Why is a present bit set in half the subsets? Toggle a fixed element containing that bit to pair each XOR-zero subset with an XOR-one subset for that bit.
- Would the shortcut work for sums of subset AND values? Re-derive per-bit contributions: only subsets using exclusively elements with that bit set contribute.

## 3. [Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Enumerate root-to-leaf paths, maintaining a path that is local to the current branch.

**Talking points:**

- I'd append node values to a shared path builder and copy it only at leaves.
- Restoring the previous builder length after each call keeps sibling paths independent.

**Solution:** Append the current value to a shared StringBuilder. At a leaf copy the complete path into the answer. Save and restore the builder length around each call so a sibling never inherits the previous branch's values.

**Complexity:**

**Time complexity:** `O(n + Z)` for `n` nodes and `Z` total output characters.

**Space complexity:** `O(h + P)` auxiliary stack/builder for height `h` and maximum path
character length `P`, plus `O(Z)` output.

**Java:** [BinaryTreePaths](./java/03-BinaryTreePaths.java) — Solution restores its path buffer and does not mutate the tree.

**Potential interviewer follow-ups:**

- What changes if you only need whether a target-sum path exists? Carry a remaining sum and stop on the first valid leaf rather than collecting paths.
- Why must the builder be restored? Siblings share the same object; without undoing, later paths contain earlier branches.

## 4. [Subsets](https://leetcode.com/problems/subsets/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** All subsets of distinct elements: order within a subset should not create separate answers.

**Talking points:**

- I'd record the current subset, then choose only later indices for its next element.
- Undoing each append restores the path, and copying when recording prevents later mutations
  from changing answers.

**Solution:** Record the current subset, then try each next index from start onward. Recurse with i + 1 so elements cannot be reused. Remove the just-added element before trying the next candidate; copy the list when saving an answer.

**Complexity:**

**Time complexity:** `O(n 2^n)` including subset copies for `n` values.

**Space complexity:** `O(n)` auxiliary stack/path, plus `O(n 2^n)` output.

**Java:** [Subsets](./java/04-Subsets.java) — Solution leaves nums unchanged and saves independent list copies.

**Potential interviewer follow-ups:**

- What if nums contains duplicates? Sort a copy and skip equal candidates at the same recursion depth, not at every depth.
- Can subsets be streamed? Emit to a callback instead of retaining the output, reducing retained auxiliary storage to O(n).

## 5. [Permutations](https://leetcode.com/problems/permutations/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Order matters, but each input element must be used exactly once.

**Talking points:**

- I'd choose an unused index at each depth and mark it until that recursive branch returns.
- A length-`n` path is one permutation; undoing both the mark and append enables the next
  choice.

**Solution:** At each depth try every unused index. Mark it, append its value, recurse, then undo both changes. A path of length n is one complete permutation.

**Complexity:**

**Time complexity:** `O(n n!)` including permutation copies for `n` values.

**Space complexity:** `O(n)` auxiliary used-array/path/stack, plus `O(n n!)` output.

**Java:** [Permutations](./java/05-Permutations.java) — Solution uses a used-index array and does not mutate nums.

**Potential interviewer follow-ups:**

- Can you eliminate the used array? Swap each candidate into the current position and swap back; the recursion stack still uses O(n).
- How do duplicate values change the algorithm? Sort and skip an equal value when its preceding equal value has not been used on the current path.

## 6. [Combination Sum](https://leetcode.com/problems/combination-sum/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Generate combinations reaching a target; a positive candidate may be used repeatedly, and order does not distinguish answers.

**Talking points:**

- I'd choose positive candidates in nondecreasing index order and allow reuse of the chosen
  index.
- Sorting enables an early stop when a candidate exceeds the remaining target, while index
  ordering avoids duplicate combinations.

**Solution:** Sort a copy. Choose candidates in nondecreasing index order, recurring with the same index to permit reuse. Stop at remaining == 0; break once a candidate exceeds the remaining target. Positivity makes every recursive choice move toward termination.

**Complexity:**

**Time complexity:** For `n` candidates, `D = floor(target / minCandidate)` and output size `Z`:
a loose bound is `O(n log(n + 1) + n^(D + 1) + Z)`.

**Space complexity:** `O(n + D)` auxiliary sorted-copy/path/stack, plus `O(Z)` output.

**Java:** [CombinationSum](./java/06-CombinationSum.java) — Solution sorts a clone and leaves the caller's candidates unchanged.

**Potential interviewer follow-ups:**

- What if each candidate may be used only once? Recurse with i + 1 and skip duplicate candidates at the same depth.
- Can zero or negative candidates be allowed unchanged? No: unlimited reuse can prevent termination or create infinitely many combinations; extra constraints are needed.

## 7. [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Choose cut positions so every resulting substring is a palindrome; return every valid partition.

**Talking points:**

- I'd precompute which substrings are palindromes, then backtrack over valid next cuts.
- Reaching the end records one partition, and the table makes each proposed cut's palindrome
  check constant time.

**Solution:** Precompute whether s[i..j] is a palindrome using matching endpoints and the inner substring. Backtrack over the next cut, accepting only palindromic pieces. Reaching the end records one partition. The boolean table is a small dynamic-programming helper; the main task remains enumeration.

**Complexity:**

**Time complexity:** `O(n^2 + n 2^n)` including copying for string length `n`.

**Space complexity:** `O(n^2)` auxiliary palindrome table and path/stack, plus up to `O(n 2^n)`
output.

**Java:** [PalindromePartitioning](./java/07-PalindromePartitioning.java) — Solution uses a precomputed palindrome table; substring objects are copied into saved partitions.

**Potential interviewer follow-ups:**

- Could you avoid the table? Check each candidate with two pointers, trading O(n²) table space for repeated palindrome checks.
- What if only the fewest cuts are required? Replace enumeration with an optimization DP over prefix cut positions.

## 8. [Word Search](https://leetcode.com/problems/word-search/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Find a word by adjacent grid moves, but a cell cannot be reused within the current path.

**Talking points:**

- I'd try matching paths from each possible starting cell using DFS.
- I'd mark each cell only for the current path and restore it on return, since cells may be
  reused by other candidate paths.

**Solution:** Start a DFS from each matching cell. Temporarily replace the current character with a sentinel, search its four neighbors for the next letter, and restore the character before returning, including on success. Visited state belongs to one candidate path, not to the entire search.

**Complexity:**

**Time complexity:** `O(RC 3^L)` loose worst-case bound for `R` rows, `C` columns and word
length `L`; the first step allows four directions.

**Space complexity:** `O(min(L, RC))` recursive stack; the board provides visited markers.

**Java:** [WordSearch](./java/08-WordSearch.java) — Solution temporarily mutates board but restores every cell before returning.

**Potential interviewer follow-ups:**

- Why is one global visited set wrong? A cell rejected in one path may belong to a different valid path; only the current path forbids reuse.
- What changes when searching for many words? A trie shares prefix exploration across words; see Word Search II in 17.

## 9. [N-Queens](https://leetcode.com/problems/n-queens/) — Hard

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Place one queen per row while maintaining column and diagonal constraints; enumerate all valid boards.

**Talking points:**

- I'd place one queen per row and track occupied columns and both diagonals.
- Each legal placement recurses to the next row, then undoes its marks; complete placements
  become boards.

**Solution:** A recursive level chooses the column for one row. Three boolean arrays track occupied columns, descending diagonals r-c+n-1, and ascending diagonals r+c. Mark all three, recurse, and unmark. Build strings only for complete placements.

**Complexity:**

**Time complexity:** `O(n n! + S n^2)` conservative bound for board size `n` and `S` solutions.

**Space complexity:** `O(n)` auxiliary constraints/path/stack, plus `O(S n^2)` output boards.

**Java:** [NQueens](./java/09-NQueens.java) — Solution stores column choices and materializes boards only at leaves.

**Potential interviewer follow-ups:**

- Why do diagonal indices work? Cells share r-c on one diagonal direction and r+c on the other.
- Can you exploit symmetry? Mirror placements across the vertical axis, handling the middle column separately for odd n; counting and enumerating need different output handling.

## 10. [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Fill constrained empty cells; a locally valid choice may need to be undone after a later contradiction.

**Talking points:**

- I'd track row, column, and box digits with bit masks.
- Choosing the empty cell with the fewest legal digits prunes the search; each tried digit is
  marked and undone on failure.

**Solution:** Track digits already used in each row, column, and 3×3 box with nine-bit masks. At each step choose the remaining cell with the fewest legal digits (MRV), then try each allowed bit. Mark a digit and recurse; undo on failure. The low-bit expression mask & -mask extracts one candidate.

**Complexity:**

**Time complexity:** `O(E 9^E)` loose bound for `E` empty cells, including each minimum-
candidate scan; the completed-board case costs `O(1)`.

**Space complexity:** `O(E + 1)` auxiliary empty-cell/stack storage and fixed masks; the board
is solved in place.

**Java:** [SudokuSolver](./java/10-SudokuSolver.java) — Solution fills board in place; assumes the problem's valid puzzle with a unique solution.

**Potential interviewer follow-ups:**

- Why choose the most constrained cell first? It exposes contradictions earlier and reduces branching, without changing which solutions are legal.
- What if uniqueness is not guaranteed? Continue after the first solution and count up to two, copying the first solved board before undoing it.

