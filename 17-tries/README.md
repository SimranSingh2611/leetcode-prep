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

**Solution:** Walk one edge per lowercase character, creating missing nodes during insert. Mark the last node as a word ending. search requires both a successful walk and a terminal marker; startsWith requires only a successful walk.

**Complexity:** Each operation O(L) time for its string length. O(S) nodes/storage for S total inserted characters, with a constant 26-child array per node.

**Java:** [ImplementTrie](./java/01-ImplementTrie.java) — Trie is the required design class. The implementation assumes lowercase English letters.

**Potential interviewer follow-ups:**

- Why is a terminal flag necessary? A prefix such as app may exist on the path for apple without being an inserted word.
- How would you delete a word? Clear its terminal flag and prune nodes only when they have neither children nor a remaining word ending; counts help with duplicate insertions.

## 2. [Replace Words](https://leetcode.com/problems/replace-words/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** For each sentence word, find the shortest dictionary word that is its prefix.

**Solution:** Insert roots into a trie. Walk each sentence word until a terminal node is reached; that earliest terminal is the shortest replacement. If an edge is missing, retain the original word.

**Complexity:** O(D+T) time for total dictionary characters D and sentence characters T; O(D) trie space plus sentence/output storage.

**Java:** [ReplaceWords](./java/02-ReplaceWords.java) — Solution assumes the problem's lowercase words and single-space sentence formatting; dictionary is unchanged.

**Potential interviewer follow-ups:**

- What if the longest matching root is wanted? Continue walking while remembering the last terminal node.
- Why is searching roots one by one wasteful? Repeated roots share prefix comparisons, which the trie performs just once per sentence word.

## 3. [Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Store numeric values by string key and answer aggregate sums over prefixes, including overwrites.

**Solution:** Each trie node stores the sum of all keys passing through it. A map remembers the previous value of each full key. On insertion apply only newValue-oldValue to the root and every prefix node; adding the full new value again would double count overwritten keys.

**Complexity:** insert O(L), sum O(P) time; O(S) trie/key storage for total distinct-key characters S.

**Java:** [MapSumPairs](./java/03-MapSumPairs.java) — MapSum is the design class. Stored sums use int under the problem's bounds.

**Potential interviewer follow-ups:**

- Why keep a separate key-value map? It supplies the delta on overwrite; a terminal stored value in the trie could replace the map.
- How would deletion work? Apply the negative old value as the delta, remove the key, and optionally prune unused nodes.

## 4. [Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A word is buildable only if every nonempty prefix is also a complete dictionary word.

**Solution:** Build a trie containing all words, then walk each candidate and require a terminal marker at every step. Among valid candidates choose the longest, breaking ties lexicographically. This tests a chain of word endings, not just whether a prefix path exists.

**Complexity:** O(S) time and O(S) trie space for total input characters S, including candidate walks and tie comparisons.

**Java:** [LongestWordInDictionary](./java/04-LongestWordInDictionary.java) — Solution preserves words and uses iterative traversal. LeetCode currently labels this Medium.

**Potential interviewer follow-ups:**

- Why isn't the deepest trie node always the answer? Intermediate prefixes may not be complete words.
- Could a hash set solve it? Yes; sorting by length and accepting a word when its one-character-shorter prefix is accepted is an alternative.

## 5. [Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** After every typed prefix, return the three lexicographically smallest matching dictionary words.

**Solution:** Sort a copy of the products. Insert them in that order, retaining only the first three product references at each prefix node. Querying then follows the search word once and copies each node's precomputed suggestions; missing prefixes yield empty lists thereafter.

**Complexity:** For n products, maximum length L, total product characters S, and query length Q: O(nL log n + S + Q) time to build and query, counting string-comparison cost; O(S+n) index/copy space plus O(Q) output references.

**Java:** [SearchSuggestionsSystem](./java/05-SearchSuggestionsSystem.java) — Solution keeps at most three suggestions per trie node and leaves products unchanged.

**Potential interviewer follow-ups:**

- Can sorting plus binary search replace the trie? Yes; each prefix identifies a contiguous range in lexicographic order.
- How would deletions affect cached top-three lists? Removing one cached product requires finding a replacement, so richer per-prefix metadata or rebuilding may be needed.

## 6. [Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) — Medium

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Dictionary membership with '.' matching any single character; only wildcard positions branch.

**Solution:** Insert words normally. Search follows one edge for a letter, or recursively tries every existing child for a dot. A match requires reaching the end of the pattern at a terminal node, so a longer word cannot satisfy a shorter pattern.

**Complexity:** addWord O(L); search O(V) for visited trie nodes, bounded by total trie size and loosely by O(L · 26^d) for d wildcard positions. O(L) search stack; O(S) persistent trie space.

**Java:** [DesignAddAndSearchWordsDataStructure](./java/06-DesignAddAndSearchWordsDataStructure.java) — WordDictionary is the required design class; '.' matches exactly one character.

**Potential interviewer follow-ups:**

- Why isn't wildcard search always O(L)? Each dot may branch to many child paths.
- What if '*' matches any number of characters? The state becomes (trie node, pattern index) with skip/repeat transitions; memoization may be needed to avoid repeated states.

## 7. [Maximum XOR of Two Numbers in an Array](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Maximize a numeric result from its most significant bit downward; a binary trie groups numbers by bit prefixes.

**Solution:** Insert bits 30 through 0 of each nonnegative int into a binary trie. For each number, prefer the opposite bit at every level, setting that result bit when possible. A higher result bit dominates every combination of lower bits, making that choice safe.

**Complexity:** O(31n) time and O(31n) trie space; the fixed 31-bit width makes both O(n).

**Java:** [MaximumXOROfTwoNumbersInAnArray](./java/07-MaximumXOROfTwoNumbersInAnArray.java) — Solution is a bitwise-trie extension, not a string trie; inputs are nonnegative and unchanged.

**Potential interviewer follow-ups:**

- Why choose the opposite high bit greedily? A 1 at bit b is worth more than all lower bits combined.
- What changes for signed numbers or a different width? Define whether XOR results are compared signed or unsigned and adjust the bit range and comparison objective.

## 8. [Word Search II](https://leetcode.com/problems/word-search-ii/) — Hard

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Many dictionary words share prefixes while being searched along no-reuse grid paths.

**Solution:** Build a trie of the words, then backtrack from each grid cell while following trie edges. A missing edge prunes all words with that impossible prefix. A terminal stores the original word; emit it once and clear the terminal to deduplicate. Temporarily mark visited cells and restore them on return.

**Complexity:** For total dictionary characters S, R×C cells, and longest word L: O(S + RC · 3^L) loose worst-case time; O(S+min(L,RC)) auxiliary trie/stack space, excluding output. Prefix pruning improves practical work.

**Java:** [WordSearchII](./java/08-WordSearchII.java) — Solution restores board. It builds a fresh trie per call and clears only that private trie's terminal markers.

**Potential interviewer follow-ups:**

- Why not call Word Search separately for every word? Common prefixes would repeat the same grid exploration.
- Can dead trie branches be removed? After exploring, remove a child with no word ending and no remaining children; this prunes words already found without deleting unfinished candidates.

## 9. [Concatenated Words](https://leetcode.com/problems/concatenated-words/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** A candidate must split into at least two dictionary words; trie walks enumerate valid next pieces without substring lookups.

**Solution:** Build a trie of all words. For each candidate, memoize the maximum number of dictionary pieces covering each suffix. From a start index, walk the trie and recurse after every terminal node. The whole word by itself yields one piece; require at least two, so it cannot count as its own concatenation. This is explicitly a trie + memoized-recursion hybrid.

**Complexity:** O(S + sum(L_i²)) time over word lengths L_i; O(S + max L_i) auxiliary trie, memo, and recursion space, excluding output.

**Java:** [ConcatenatedWords](./java/09-ConcatenatedWords.java) — Solution preserves words. The memoization idea is introduced here; revisit this stretch problem after dynamic programming if needed.

**Potential interviewer follow-ups:**

- Why is testing ordinary dictionary membership insufficient? Every candidate is already in the dictionary; it must have a split into at least two shorter pieces.
- Why memoize by suffix index? For a fixed word and dictionary, the best segmentation of that suffix does not depend on the path used to reach it.

## 10. [Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Many queries simultaneously constrain a word's beginning and ending, with largest original index as the tie-breaker.

**Solution:** For every suffix of each word, insert suffix + '{' + word into a trie, storing the latest word index on every visited node. The separator is outside the lowercase alphabet. Query suff + '{' + pref; all words on that path match both constraints, and the cached index resolves ties.

**Complexity:** Build O(sum L_i²) time and space; query O(|pref|+|suff|) time with O(1) traversal state (the constructed query string uses O(|pref|+|suff|) temporary space). This trade-off fits the problem's short words.

**Java:** [PrefixAndSuffixSearch](./java/10-PrefixAndSuffixSearch.java) — WordFilter is the design class. The 27th child is the separator, and later duplicate words correctly overwrite earlier indices.

**Potential interviewer follow-ups:**

- Why is the separator required? It separates the suffix portion from the prefix portion, preventing ambiguous concatenations.
- What if words are very long? Quadratic preprocessing may be too expensive; consider separate prefix/suffix tries with descending index lists and intersect their candidates.

