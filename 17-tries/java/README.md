# Java Solutions — 17 — Tries

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Implement Trie (Prefix Tree)](01-ImplementTrie.java) · [LeetCode](https://leetcode.com/problems/implement-trie-prefix-tree/) · [NeetCode 150](https://neetcode.io/problems/implement-prefix-tree/question?list=neetcode150) — Trie is the required design class. The implementation assumes lowercase English letters.
2. [Replace Words](02-ReplaceWords.java) · [LeetCode](https://leetcode.com/problems/replace-words/) — Solution assumes the problem's lowercase words and single-space sentence formatting; dictionary is unchanged.
3. [Map Sum Pairs](03-MapSumPairs.java) · [LeetCode](https://leetcode.com/problems/map-sum-pairs/) — MapSum is the design class. Stored sums use int under the problem's bounds.
4. [Longest Word in Dictionary](04-LongestWordInDictionary.java) · [LeetCode](https://leetcode.com/problems/longest-word-in-dictionary/) — Solution preserves words and uses iterative traversal. LeetCode currently labels this Medium.
5. [Search Suggestions System](05-SearchSuggestionsSystem.java) · [LeetCode](https://leetcode.com/problems/search-suggestions-system/) — Solution keeps at most three suggestions per trie node and leaves products unchanged.
6. [Design Add and Search Words Data Structure](06-DesignAddAndSearchWordsDataStructure.java) · [LeetCode](https://leetcode.com/problems/design-add-and-search-words-data-structure/) · [NeetCode 150](https://neetcode.io/problems/design-word-search-data-structure/question?list=neetcode150) — WordDictionary is the required design class; '.' matches exactly one character.
7. [Maximum XOR of Two Numbers in an Array](07-MaximumXOROfTwoNumbersInAnArray.java) · [LeetCode](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/) — Solution is a bitwise-trie extension, not a string trie; inputs are nonnegative and unchanged.
8. [Word Search II](08-WordSearchII.java) · [LeetCode](https://leetcode.com/problems/word-search-ii/) · [NeetCode 150](https://neetcode.io/problems/search-for-word-ii/question?list=neetcode150) — Solution restores board. It builds a fresh trie per call and clears only that private trie's terminal markers.
9. [Concatenated Words](09-ConcatenatedWords.java) · [LeetCode](https://leetcode.com/problems/concatenated-words/) — Solution preserves words. The memoization idea is introduced here; revisit this stretch problem after dynamic programming if needed.
10. [Prefix and Suffix Search](10-PrefixAndSuffixSearch.java) · [LeetCode](https://leetcode.com/problems/prefix-and-suffix-search/) — WordFilter is the design class. The 27th child is the separator, and later duplicate words correctly overwrite earlier indices.

