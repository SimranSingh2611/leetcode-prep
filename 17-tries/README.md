# 17 — Tries

A trie shares work across strings with common prefixes. This narrower topic deliberately has no artificial Easy filler: begin with the basic data structure, then add prefix metadata, branching searches, and carefully labelled hybrids.

## How to recognize this pattern

**Keywords / hints:** “prefix,” “autocomplete,” “dictionary,” “starts with,” “wildcard word search,” many queries against a fixed word set, or maximizing XOR by high-order bits.

**Structural test:** Can character-by-character traversal share work across many keys or prune whole groups of strings? What information must each prefix node store?

**Invariant / mental model:** A node represents exactly one prefix. Its children extend that prefix by one symbol, and terminal markers distinguish complete words from mere prefixes.

**When not to use it:** For exact whole-key membership alone, a hash set is usually simpler. One prefix query over a tiny list may not justify building a trie. Fixed child arrays trade memory for simple lookup.

**Prerequisites:** 01 hash maps/sets; 11 recursion; 14 backtracking for wildcard/grid searches. Maximum XOR introduces a binary trie; Concatenated Words adds memoization and can be revisited after dynamic programming.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 7 Medium / 3 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Support repeated insertion, exact-word lookup, and prefix lookup over the same dictionary.

**Talking points:**

- I'd store one trie edge per lowercase character and mark complete word endings.
- Exact search requires a terminal marker, while prefix search succeeds as soon as the whole
  prefix path exists.

**Solution:** Walk one edge per lowercase character, creating missing nodes during insert. Mark the last node as a word ending. search requires both a successful walk and a terminal marker; startsWith requires only a successful walk.

**Complexity:**

**Time complexity:** Each `insert`, `search`, or `startsWith`: `O(L)` for argument length `L`.

**Space complexity:** `O(S)` persistent trie space for total inserted characters `S`; `O(1)`
traversal state per operation.

**Java:** [ImplementTrie](./java/01-ImplementTrie.java) — Trie is the required design class. The implementation assumes lowercase English letters.

**Potential interviewer follow-ups:**

- Why is a terminal flag necessary? A prefix such as app may exist on the path for apple without being an inserted word.
- How would you delete a word? Clear its terminal flag and prune nodes only when they have neither children nor a remaining word ending; counts help with duplicate insertions.

## 2. [Replace Words](https://leetcode.com/problems/replace-words/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** For each sentence word, find the shortest dictionary word that is its prefix.

**Talking points:**

- I'd insert dictionary roots into a trie and follow each sentence word from its first letter.
- The first terminal encountered is its shortest replacement; a missing edge leaves the original
  word unchanged.

**Solution:** Insert roots into a trie. Walk each sentence word until a terminal node is reached; that earliest terminal is the shortest replacement. If an edge is missing, retain the original word.

**Complexity:**

**Time complexity:** `O(D + T)` for dictionary character count `D` and sentence length `T`.

**Space complexity:** `O(D)` auxiliary trie and `O(T)` sentence-split/output storage.

**Java:** [ReplaceWords](./java/02-ReplaceWords.java) — Solution assumes the problem's lowercase words and single-space sentence formatting; dictionary is unchanged.

**Potential interviewer follow-ups:**

- What if the longest matching root is wanted? Continue walking while remembering the last terminal node.
- Why is searching roots one by one wasteful? Repeated roots share prefix comparisons, which the trie performs just once per sentence word.

## 3. [Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Store numeric values by string key and answer aggregate sums over prefixes, including overwrites.

**Talking points:**

- I'd store a prefix sum at every trie node and remember each full key's old value.
- Updating a key adds only the value difference along its path, so overwrites do not double
  count.

**Solution:** Each trie node stores the sum of all keys passing through it. A map remembers the previous value of each full key. On insertion apply only newValue-oldValue to the root and every prefix node; adding the full new value again would double count overwritten keys.

**Complexity:**

**Time complexity:** Expected `O(L)` per `insert` for key length `L`; `O(P)` per `sum` for
prefix length `P`.

**Space complexity:** `O(S)` persistent trie/key-map storage for total distinct-key characters
`S`.

**Java:** [MapSumPairs](./java/03-MapSumPairs.java) — MapSum is the design class. Stored sums use int under the problem's bounds.

**Potential interviewer follow-ups:**

- Why keep a separate key-value map? It supplies the delta on overwrite; a terminal stored value in the trie could replace the map.
- How would deletion work? Apply the negative old value as the delta, remove the key, and optionally prune unused nodes.

## 4. [Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A word is buildable only if every nonempty prefix is also a complete dictionary word.

**Talking points:**

- I'd build a trie and require every prefix along a candidate's path to end a word.
- Among qualifying words I'd keep the longest, then use lexicographic order to break ties.

**Solution:** Build a trie containing all words, then walk each candidate and require a terminal marker at every step. Among valid candidates choose the longest, breaking ties lexicographically. This tests a chain of word endings, not just whether a prefix path exists.

**Complexity:**

**Time complexity:** `O(S)` for total input characters `S`, including candidate walks and tie
comparisons.

**Space complexity:** `O(S)` auxiliary trie space.

**Java:** [LongestWordInDictionary](./java/04-LongestWordInDictionary.java) — Solution preserves words and uses iterative traversal. LeetCode currently labels this Medium.

**Potential interviewer follow-ups:**

- Why isn't the deepest trie node always the answer? Intermediate prefixes may not be complete words.
- Could a hash set solve it? Yes; sorting by length and accepting a word when its one-character-shorter prefix is accepted is an alternative.

## 5. [Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** After every typed prefix, return the three lexicographically smallest matching dictionary words.

**Talking points:**

- I'd insert sorted products into a trie and cache the first three suggestions at every prefix.
- Then a query follows one path, returning those cached lists for each typed character.

**Solution:** Sort a copy of the products. Insert them in that order, retaining only the first three product references at each prefix node. Querying then follows the search word once and copies each node's precomputed suggestions; missing prefixes yield empty lists thereafter.

**Complexity:**

**Time complexity:** `O(nL log(n + 1) + S + Q)` for `n` products, maximum product length `L`,
total product characters `S`, and query length `Q`.

**Space complexity:** `O(S + n)` auxiliary trie/sorted-copy storage, plus `O(Q)` output
references.

**Java:** [SearchSuggestionsSystem](./java/05-SearchSuggestionsSystem.java) — Solution keeps at most three suggestions per trie node and leaves products unchanged.

**Potential interviewer follow-ups:**

- Can sorting plus binary search replace the trie? Yes; each prefix identifies a contiguous range in lexicographic order.
- How would deletions affect cached top-three lists? Removing one cached product requires finding a replacement, so richer per-prefix metadata or rebuilding may be needed.

## 6. [Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Dictionary membership with '.' matching any single character; only wildcard positions branch.

**Talking points:**

- I'd insert words normally, but let a dot in a search branch over every existing child.
- A match must end at a terminal node, so matching a prefix of a longer word is insufficient.

**Solution:** Insert words normally. Search follows one edge for a letter, or recursively tries every existing child for a dot. A match requires reaching the end of the pattern at a terminal node, so a longer word cannot satisfy a shorter pattern.

**Complexity:**

**Time complexity:** `addWord`: `O(L)`; `search`: `O(V)` visited-node work, loosely bounded by
`O(L 26^d)` for `d` dots in a length-`L` pattern.

**Space complexity:** `O(S)` persistent trie storage and `O(L)` recursive search stack.

**Java:** [DesignAddAndSearchWordsDataStructure](./java/06-DesignAddAndSearchWordsDataStructure.java) — WordDictionary is the required design class; '.' matches exactly one character.

**Potential interviewer follow-ups:**

- Why isn't wildcard search always O(L)? Each dot may branch to many child paths.
- What if '*' matches any number of characters? The state becomes (trie node, pattern index) with skip/repeat transitions; memoization may be needed to avoid repeated states.

## 7. [Maximum XOR of Two Numbers in an Array](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Maximize a numeric result from its most significant bit downward; a binary trie groups numbers by bit prefixes.

**Talking points:**

- I'd insert numbers into a binary trie from their highest bit downward.
- For each query bit I'd prefer the opposite branch, since setting a higher XOR bit outweighs
  every lower-bit choice.

**Solution:** Insert bits 30 through 0 of each nonnegative int into a binary trie. For each number, prefer the opposite bit at every level, setting that result bit when possible. A higher result bit dominates every combination of lower bits, making that choice safe.

**Complexity:**

**Time complexity:** `O(31n) = O(n)` for `n` nonnegative fixed-width integers.

**Space complexity:** `O(31n) = O(n)` auxiliary trie space.

**Java:** [MaximumXOROfTwoNumbersInAnArray](./java/07-MaximumXOROfTwoNumbersInAnArray.java) — Solution is a bitwise-trie extension, not a string trie; inputs are nonnegative and unchanged.

**Potential interviewer follow-ups:**

- Why choose the opposite high bit greedily? A 1 at bit b is worth more than all lower bits combined.
- What changes for signed numbers or a different width? Define whether XOR results are compared signed or unsigned and adjust the bit range and comparison objective.

## 8. [Word Search II](https://leetcode.com/problems/word-search-ii/) — Hard

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Many dictionary words share prefixes while being searched along no-reuse grid paths.

**Talking points:**

- I'd backtrack over board paths while following dictionary trie edges.
- Missing prefixes prune whole groups of words, and clearing a found terminal prevents duplicate
  answers.

**Solution:** Build a trie of the words, then backtrack from each grid cell while following trie edges. A missing edge prunes all words with that impossible prefix. A terminal stores the original word; emit it once and clear the terminal to deduplicate. Temporarily mark visited cells and restore them on return.

**Complexity:**

**Time complexity:** `O(S + RC 3^L)` loose worst-case bound for dictionary characters `S`, board
size `R` by `C`, and longest word length `L`.

**Space complexity:** `O(S + min(L, RC))` auxiliary trie/stack space, excluding output word
references.

**Java:** [WordSearchII](./java/08-WordSearchII.java) — Solution restores board. It builds a fresh trie per call and clears only that private trie's terminal markers.

**Potential interviewer follow-ups:**

- Why not call Word Search separately for every word? Common prefixes would repeat the same grid exploration.
- Can dead trie branches be removed? After exploring, remove a child with no word ending and no remaining children; this prunes words already found without deleting unfinished candidates.

## 9. [Concatenated Words](https://leetcode.com/problems/concatenated-words/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** A candidate must split into at least two dictionary words; trie walks enumerate valid next pieces without substring lookups.

**Talking points:**

- I'd try dictionary prefixes from each start position and memoize how many pieces can cover the
  remaining suffix.
- A candidate qualifies only with at least two pieces, so its own full-word match cannot satisfy
  the test alone.

**Solution:** Build a trie of all words. For each candidate, memoize the maximum number of dictionary pieces covering each suffix. From a start index, walk the trie and recurse after every terminal node. The whole word by itself yields one piece; require at least two, so it cannot count as its own concatenation. This is explicitly a trie + memoized-recursion hybrid.

**Complexity:**

**Time complexity:** `O(S + sum(L_i^2))` over word lengths `L_i` and total characters `S`.

**Space complexity:** `O(S + Lmax)` auxiliary trie/memo/stack, plus output references; `Lmax` is
the longest word length.

**Java:** [ConcatenatedWords](./java/09-ConcatenatedWords.java) — Solution preserves words. The memoization idea is introduced here; revisit this stretch problem after dynamic programming if needed.

**Potential interviewer follow-ups:**

- Why is testing ordinary dictionary membership insufficient? Every candidate is already in the dictionary; it must have a split into at least two shorter pieces.
- Why memoize by suffix index? For a fixed word and dictionary, the best segmentation of that suffix does not depend on the path used to reach it.

## 10. [Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Many queries simultaneously constrain a word's beginning and ending, with largest original index as the tie-breaker.

**Talking points:**

- I'd index each word under every suffix followed by a separator and the full word.
- Searching `suffix + separator + prefix` reaches only words matching both constraints, and
  cached latest indices resolve ties.

**Solution:** For every suffix of each word, insert suffix + '{' + word into a trie, storing the latest word index on every visited node. The separator is outside the lowercase alphabet. Query suff + '{' + pref; all words on that path match both constraints, and the cached index resolves ties.

**Complexity:**

**Time complexity:** Build: `O(sum((L_i + 1)^2))` over word lengths `L_i`; each query: `O(P + Q
+ 1)` for prefix/suffix lengths `P`, `Q`.

**Space complexity:** `O(sum((L_i + 1)^2))` persistent trie space; `O(P + Q + 1)` temporary
query-string space.

**Java:** [PrefixAndSuffixSearch](./java/10-PrefixAndSuffixSearch.java) — WordFilter is the design class. The 27th child is the separator, and later duplicate words correctly overwrite earlier indices.

**Potential interviewer follow-ups:**

- Why is the separator required? It separates the suffix portion from the prefix portion, preventing ambiguous concatenations.
- What if words are very long? Quadratic preprocessing may be too expensive; consider separate prefix/suffix tries with descending index lists and intersect their candidates.

