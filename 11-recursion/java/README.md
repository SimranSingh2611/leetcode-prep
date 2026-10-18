# Java Solutions — 11 — Recursion

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Fibonacci Number](01-FibonacciNumber.java) · [LeetCode](https://leetcode.com/problems/fibonacci-number/) — Solution memoized; IterativeSolution optimized; NaiveSolution is for small-input tracing only.
2. [Power of Two](02-PowerOfTwo.java) · [LeetCode](https://leetcode.com/problems/power-of-two/) — Solution teaches recursion; BitwiseSolution is the optimized alternative.
3. [Power of Three](03-PowerOfThree.java) · [LeetCode](https://leetcode.com/problems/power-of-three/) — Solution recursive; IterativeSolution removes stack space.
4. [Pow(x, n)](04-PowXN.java) · [LeetCode](https://leetcode.com/problems/powx-n/) · [NeetCode 150](https://neetcode.io/problems/pow-x-n/question?list=neetcode150) — Solution recursive; IterativeSolution optimized for stack space.
5. [K-th Symbol in Grammar](05-KthSymbolGrammar.java) · [LeetCode](https://leetcode.com/problems/k-th-symbol-in-grammar/) — Solution; k is a valid 1-based position.
6. [Find Kth Bit in Nth Binary String](06-FindKthBit.java) · [LeetCode](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/) — Solution; uses the statement's n<=20 bound for int shifts.
7. [Elimination Game](07-EliminationGame.java) · [LeetCode](https://leetcode.com/problems/elimination-game/) — Solution recursive; IterativeSolution state-compressed alternative.
8. [Find the Winner of the Circular Game](08-CircularGameWinner.java) · [LeetCode](https://leetcode.com/problems/find-the-winner-of-the-circular-game/) — Solution recursive; IterativeSolution optimized for stack space.

