# Java Solutions — 08 — Stacks

Compile/submit each file independently: most use LeetCode's `Solution` class; design problems retain their required class names. Alternative classes are clearly named and must be renamed to `Solution` when submitted instead. Keep only the selected implementation if the judge requires it.

`TreeNode`, `ListNode`, and problem-specific `Node` types are supplied by LeetCode. Local equivalents exist only in `../../tests/Models.java`; do not paste those into the judge. All examples use Java 8-compatible language/library features. Read each problem's input-mutation and auxiliary-space notes. Recursion space includes the call stack; skewed structures can exhaust Java's stack.

1. [Valid Parentheses](01-ValidParentheses.java) — Solution; input preserved.
2. [Baseball Game](02-BaseballGame.java) — Solution; input preserved.
3. [Remove All Adjacent Duplicates In String](03-RemoveAdjacentDuplicates.java) — Solution; input preserved.
4. [Min Stack](04-MinStack.java) — MinStack recommended; DifferenceMinStack is a storage-layout alternative.
5. [Evaluate Reverse Polish Notation](05-EvaluateReversePolishNotation.java) — Solution; valid RPN input assumed.
6. [Simplify Path](06-SimplifyPath.java) — Solution; Unix absolute-path rules, not filesystem access.
7. [Decode String](07-DecodeString.java) — Solution; iterative context stacks avoid recursive parsing depth.
8. [Asteroid Collision](08-AsteroidCollision.java) — Solution; survivor stack, not a monotonic stack.
9. [Basic Calculator](09-BasicCalculator.java) — Solution; handles +, -, parentheses, spaces, and valid unary minus.
10. [Maximum Frequency Stack](10-MaximumFrequencyStack.java) — FreqStack design class; grouping-by-frequency hybrid.

