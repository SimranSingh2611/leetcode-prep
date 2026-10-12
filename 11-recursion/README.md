# 11 — Recursion

A focused eight-problem foundations section. Recursion means defining a problem through smaller instances; it does not automatically mean exploring alternatives. No backtracking search tree is needed here. Several optimized alternatives remove recursion to make its space cost visible.

## How to recognize this pattern

**Keywords / hints:** “defined recursively”, “previous generation”, “half the problem”, “mirror and invert”, “repeat the same operation on a smaller input”.

**Structural test:** Can you specify a base case, a strictly smaller recursive input, and how its returned answer constructs the current answer?

**Invariant / mental model:** Every call has a precise contract. A decreasing measure proves termination; each stack frame owns its local state until its child returns.

**When not to use it:** Do not recurse just because a loop exists. Linear-depth recursion can overflow Java's stack; repeated identical subproblems often need memoization or iteration. Enumerating and undoing choices belongs in backtracking.

**Prerequisites:** Functions, integer arithmetic, tracing a call stack. Bit tricks in optional follow-ups are not prerequisites.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Fibonacci Number](https://leetcode.com/problems/fibonacci-number/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** The current value is the sum of two smaller-index values, with overlapping subproblems.

**Solution:** Naive recursion follows the definition but recomputes the same indices. The primary recursive solution memoizes each index. The iterative alternative retains only the previous two values because no earlier state will be used again.

**Complexity:** Naive O(2^n) upper-bound time and O(n) stack; memoized O(n) time/space; iterative O(n) time and O(1) space.

**Java:** [FibonacciNumber](./java/01-FibonacciNumber.java) — Solution memoized; IterativeSolution optimized; NaiveSolution is for small-input tracing only.

**Potential interviewer follow-ups:**

- What is actually optimized by memoization? The number of repeated subproblem evaluations.
- Why is naive recursion not backtracking here? It combines fixed child answers rather than choosing candidate solutions.

## 2. [Power of Two](https://leetcode.com/problems/power-of-two/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A positive number is a power of two if repeatedly dividing by two reaches one exactly.

**Solution:** Reject nonpositive inputs. One is the successful base case; an odd value greater than one fails. Otherwise recurse on n/2. The bitwise alternative checks that a positive value has exactly one set bit.

**Complexity:** Recursive O(log n) time and stack; bitwise O(1) fixed-word time/space.

**Java:** [PowerOfTwo](./java/02-PowerOfTwo.java) — Solution teaches recursion; BitwiseSolution is the optimized alternative.

**Potential interviewer follow-ups:**

- Why must n>0 be checked in the bit test? Zero also satisfies n&(n-1)==0.
- What happens if recursion reaches zero without a guard? Dividing zero by two never reduces it.

## 3. [Power of Three](https://leetcode.com/problems/power-of-three/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** The same decreasing-input contract must work for division by a non-binary base.

**Solution:** Reject nonpositive n, succeed at one, and recurse only when n is divisible by three. The iterative version performs the same reduction without stack frames. Unlike powers of two, a one-set-bit test is not applicable.

**Complexity:** Both O(log base 3 n) time; recursive O(log n) stack versus iterative O(1) auxiliary space.

**Java:** [PowerOfThree](./java/03-PowerOfThree.java) — Solution recursive; IterativeSolution removes stack space.

**Potential interviewer follow-ups:**

- Why not compare floating-point logarithms to an integer? Rounding can misclassify exact powers.
- How would you generalize to base b? Require b>1 and replace the divisor, preserving the termination argument.

## 4. [Pow(x, n)](https://leetcode.com/problems/powx-n/) — Medium

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Exponentiation can halve the exponent instead of performing n multiplications.

**Solution:** Compute the half-power once, square it, and multiply by x for odd exponents. Convert the exponent to long before negating Integer.MIN_VALUE. For negative exponents invert x. Iterative binary exponentiation keeps the same logarithmic work with constant auxiliary space.

**Complexity:** O(log |n|) time; recursive O(log |n|) stack versus iterative O(1) space.

**Java:** [PowXN](./java/04-PowXN.java) — Solution recursive; IterativeSolution optimized for stack space.

**Potential interviewer follow-ups:**

- Why compute the half-power once? Calling it twice destroys the logarithmic recurrence.
- What about x=0 with a negative exponent? That case is excluded by the statement.

## 5. [K-th Symbol in Grammar](https://leetcode.com/problems/k-th-symbol-in-grammar/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Only one ancestor chain of a recursively generated row determines the requested symbol.

**Solution:** The kth symbol's parent is at (k+1)/2 in the preceding row. Odd positions copy the parent; even positions flip it. Recurse until the first row rather than generating exponentially long rows.

**Complexity:** O(n) time and stack for row n; no row strings constructed.

**Java:** [KthSymbolGrammar](./java/05-KthSymbolGrammar.java) — Solution; k is a valid 1-based position.

**Potential interviewer follow-ups:**

- Can the answer depend only on k? It equals the parity of the set bits in k-1.
- Why is this not exponential recursion? Only the relevant parent is evaluated.

## 6. [Find Kth Bit in Nth Binary String](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** A generated string consists of a previous string, a middle bit, and an inverted reversed copy.

**Solution:** At level n, the middle position is 2^(n-1). Return one there; recurse unchanged into the left half. In the right half, reflect k to length-k+1 in the previous string and invert its returned bit. Level one contains zero.

**Complexity:** O(n) time and stack; no O(2^n) string materialization.

**Java:** [FindKthBit](./java/06-FindKthBit.java) — Solution; uses the statement's n<=20 bound for int shifts.

**Potential interviewer follow-ups:**

- How do you derive the reflected index? Count the same offset from the right endpoint.
- Could inversion be accumulated iteratively? Yes; track a flip flag while descending.

## 7. [Elimination Game](https://leetcode.com/problems/elimination-game/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** After eliminating every other value, the remaining sequence is a scaled/reflected smaller instance.

**Solution:** After the left-to-right pass, survivors are 2,4,...,2*floor(n/2). The next direction reverses the reduced problem, giving f(n)=2*(floor(n/2)+1-f(floor(n/2))). Base f(1)=1. The iterative alternative tracks the first surviving value, spacing, remaining count, and direction.

**Complexity:** O(log n) time; recursive O(log n) stack versus iterative O(1) space.

**Java:** [EliminationGame](./java/07-EliminationGame.java) — Solution recursive; IterativeSolution state-compressed alternative.

**Potential interviewer follow-ups:**

- Why reflect the smaller answer? The next pass starts from the opposite side.
- When does the first survivor move during a right-to-left pass? Only when the remaining count is odd.

## 8. [Find the Winner of the Circular Game](https://leetcode.com/problems/find-the-winner-of-the-circular-game/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Removing one person leaves the same circular problem with shifted numbering.

**Solution:** Use a zero-based survivor recurrence: winner(1)=0 and winner(n)=(winner(n-1)+k)%n. Add one only at the outer boundary. The iterative version builds the same recurrence from size one upward without recursive frames.

**Complexity:** O(n) time; recursive O(n) stack versus iterative O(1) space.

**Java:** [CircularGameWinner](./java/08-CircularGameWinner.java) — Solution recursive; IterativeSolution optimized for stack space.

**Potential interviewer follow-ups:**

- Why use zero-based indexing internally? Modular shifting becomes a direct addition and remainder.
- Can simulation with a queue be slower? Repeatedly rotating k-1 positions adds avoidable work.

