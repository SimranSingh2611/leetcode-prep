# 08 — Stacks

Learn ordinary last-in-first-out state before monotonic ordering. Use ArrayDeque for Java stack operations. These problems do not require the stack values to be sorted.

## How to recognize this pattern

**Keywords / hints:** “nested”, “matching brackets”, “undo”, “most recent”, “evaluate expression”, “cancel adjacent items”, “resume after a closing delimiter”.

**Structural test:** Does the next operation need the most recently opened or unresolved item? Can resolving it expose an earlier item that must now be reconsidered?

**Invariant / mental model:** The stack holds unresolved context in opening order; only its top can be resolved next.

**When not to use it:** A queue is appropriate for oldest-first work. A monotonic stack additionally needs a dominance/order proof; ordinary nesting is not that proof.

**Prerequisites:** Arrays, strings, hash maps. No recursion is needed for the iterative implementations here.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) — Easy

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Closing delimiters must match the most recent unclosed opening delimiter.

**Solution:** Push the expected closing character for each opener. On a closer, require it to equal the stack top, then pop. The stack must be empty at the end; merely counting bracket types does not enforce nesting.

**Complexity:** O(n) time, O(n) auxiliary space.

**Java:** [ValidParentheses](./java/01-ValidParentheses.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Why does ([)] fail despite balanced counts? Closing order violates nesting.
- How would you report the first bad index? Store positions with expected closers.

## 2. [Baseball Game](https://leetcode.com/problems/baseball-game/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Operations cancel or reuse the most recent valid scores.

**Solution:** Maintain valid scores in a stack. C removes the last score, D doubles it, and + sums the last two. Temporarily pop one value for +, then restore it before pushing the new score.

**Complexity:** O(n) time and O(n) auxiliary space.

**Java:** [BaseballGame](./java/02-BaseballGame.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- Can the final sum be maintained online? Adjust a running total on every push/pop.
- Why not retain canceled scores? Later operations refer only to valid scores.

## 3. [Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Deleting a neighboring equal pair can expose another pair.

**Solution:** Treat a StringBuilder as a character stack. If the next character equals its final character, remove that final character; otherwise append. The builder always contains the fully reduced processed prefix.

**Complexity:** O(n) time, O(n) working/output storage.

**Java:** [RemoveAdjacentDuplicates](./java/03-RemoveAdjacentDuplicates.java) — Solution; input preserved.

**Potential interviewer follow-ups:**

- For deleting runs of k instead of pairs? Stack character/count pairs.
- Why is one left-to-right pass sufficient? Every exposed boundary is revisited at the stack top.

## 4. [Min Stack](https://leetcode.com/problems/min-stack/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** A stack must also return its minimum after arbitrary pops.

**Solution:** Store each value together with the minimum at that depth. Popping automatically restores the previous minimum. A second design stores long differences from the current minimum; negative differences mark a new minimum and let pop restore the old one.

**Complexity:** Both designs O(1) per operation and O(n) space. The difference design stores one long per entry instead of value/min pairs; it does not improve asymptotic space.

**Java:** [MinStack](./java/04-MinStack.java) — MinStack recommended; DifferenceMinStack is a storage-layout alternative.

**Potential interviewer follow-ups:**

- Why keep duplicate minima? Popping one occurrence must not discard another.
- Why long differences? Subtracting two valid int values can overflow int.

## 5. [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Each operator consumes the two most recent unevaluated operands.

**Solution:** Push numbers. On an operator, pop the right operand first and the left second, compute the result, and push it. Java integer division truncates toward zero as required.

**Complexity:** O(n) time, O(n) auxiliary space.

**Java:** [EvaluateReversePolishNotation](./java/05-EvaluateReversePolishNotation.java) — Solution; valid RPN input assumed.

**Potential interviewer follow-ups:**

- Why does operand order matter? Subtraction and division are not commutative.
- How would unary operators change parsing? Associate each operator with its arity.

## 6. [Simplify Path](https://leetcode.com/problems/simplify-path/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Parent-directory navigation cancels the last retained path component.

**Solution:** Split an absolute Unix path by /. Ignore empty components and '.', pop a component for '..' if available, and retain every other name literally. Join the retained components with a leading slash.

**Complexity:** O(n) time and O(n) auxiliary/output storage.

**Java:** [SimplifyPath](./java/06-SimplifyPath.java) — Solution; Unix absolute-path rules, not filesystem access.

**Potential interviewer follow-ups:**

- Is ... special? No; only exactly . and .. have navigation meanings.
- What changes for relative paths? Leading .. components may need to be retained.

## 7. [Decode String](https://leetcode.com/problems/decode-string/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Nested repetition brackets require suspending an outer string while decoding an inner one.

**Solution:** On [, save the outer builder and repeat count, then start a fresh builder. On ], append the completed inner string the saved number of times to the outer builder. Keep multidigit count parsing separate from text.

**Complexity:** O(n + sum of intermediate expansion lengths) time, O(n+L) peak storage for input length n and decoded length L. Deep repeated copying can exceed O(n+L) time.

**Java:** [DecodeString](./java/07-DecodeString.java) — Solution; iterative context stacks avoid recursive parsing depth.

**Potential interviewer follow-ups:**

- Why not claim linear in input length? The output itself can be much larger.
- How would you handle an output-length cap? Track lengths and reject before materializing oversized expansions.

## 8. [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A new left-moving asteroid may repeatedly collide with recent right-moving survivors.

**Solution:** Maintain surviving asteroids. Collisions occur only when the top is positive and the incoming value is negative. Pop smaller opponents, remove both for equal magnitudes, or discard the incoming asteroid if the top is larger.

**Complexity:** O(n) amortized time; each asteroid is pushed/popped at most once. O(n) space.

**Java:** [AsteroidCollision](./java/08-AsteroidCollision.java) — Solution; survivor stack, not a monotonic stack.

**Potential interviewer follow-ups:**

- Why don't negative-then-positive asteroids collide? They move apart.
- How do you justify linear time despite the while loop? Charge every pop to the element removed.

## 9. [Basic Calculator](https://leetcode.com/problems/basic-calculator/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Parentheses suspend a partial sum and its sign; unary minus must also work.

**Solution:** Track result, current number, and sign. Commit a number at + or -. At (, save the outer result and sign and reset the inner context. At ), finish the inner expression and combine it with the saved signed outer context.

**Complexity:** O(n) time; O(depth) stack space.

**Java:** [BasicCalculator](./java/09-BasicCalculator.java) — Solution; handles +, -, parentheses, spaces, and valid unary minus.

**Potential interviewer follow-ups:**

- How would multiplication change this? Add precedence handling or use a precedence-aware parser.
- Why iterative parsing? The expression can be too deeply nested for Java recursion.

## 10. [Maximum Frequency Stack](https://leetcode.com/problems/maximum-frequency-stack/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Pop highest frequency, breaking ties by most recent insertion.

**Solution:** Map each value to its count and each frequency to a stack of values that reached it. Push records the new frequency level; pop removes from the maximum-frequency stack and decrements the value's count. Lower-frequency records remain as the history needed when higher levels are removed.

**Complexity:** O(1) expected time per operation; O(n) space for n currently stacked occurrences, plus active maps.

**Java:** [MaximumFrequencyStack](./java/10-MaximumFrequencyStack.java) — FreqStack design class; grouping-by-frequency hybrid.

**Potential interviewer follow-ups:**

- Why is a heap less attractive? It introduces logarithmic push/pop overhead.
- How is recency preserved within a frequency? The corresponding bucket is a LIFO stack.

