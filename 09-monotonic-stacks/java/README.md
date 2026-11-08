# Java Solutions — 09 — Monotonic Stacks

Compile/submit each file independently using Java 8 or later. Most use `Solution`;
Online Stock Span uses `StockSpanner`. To submit Car Fleet’s alternative, rename
`CompressedSolution` to `Solution` and keep only that implementation.

The numbered order builds from four standalone templates to direct applications,
then greedy adaptations, boundary calculations, and combined techniques. All seven
problems from the [source guide](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/)
remain included once. Every primary solution follows its left-to-right approach.

The four standalone templates at 01–04 return nearest strictly qualifying **indices**,
or `-1` when absent; they are not separate LeetCode submissions. All implementations
preserve their inputs. See the [learning stages](../README.md#practice-progression)
for the reasoning behind the order.

## Problem order

1. [Next Greater](01-NextGreater.java) — Template 1; `<`; resolve successor indices inside while; non-circular.
2. [Previous Greater](02-PreviousGreater.java) — Template 2; `<=`; return predecessor indices after while.
3. [Next Smaller](03-NextSmaller.java) — Template 4; `>`; resolve successor indices inside while.
4. [Previous Smaller](04-PreviousSmaller.java) — Template 5; `>=`; return predecessor indices after while.
5. [Next Greater Element I](05-NextGreaterElementI.java) · [LeetCode](https://leetcode.com/problems/next-greater-element-i/) — Template 1; `<`; indices on the stack, distinct-value map for answers.
6. [Final Prices With a Special Discount in a Shop](06-FinalPrices.java) · [LeetCode](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) — Template 4 with `>=`; equal prices qualify for the discount.
7. [Daily Temperatures](07-DailyTemperatures.java) · [LeetCode](https://leetcode.com/problems/daily-temperatures/) · [NeetCode 150](https://neetcode.io/problems/daily-temperatures/question?list=neetcode150) — Template 1; `<`; record distance inside while.
8. [Buildings With an Ocean View](08-BuildingsWithOceanView.java) · [LeetCode](https://leetcode.com/problems/buildings-with-an-ocean-view/) — Template 1 with `<=`; survivors have ocean views. **Premium.**
9. [Online Stock Span](09-OnlineStockSpan.java) · [LeetCode](https://leetcode.com/problems/online-stock-span/) — Template 2; `<=`; stream `{price, day}` pairs and subtract previous-greater index.
10. [Next Greater Element II](10-NextGreaterElementII.java) · [LeetCode](https://leetcode.com/problems/next-greater-element-ii/) — Template 1; `<`; circular scan, push only on the first pass.
11. [Remove K Digits](11-RemoveKDigits.java) · [LeetCode](https://leetcode.com/problems/remove-k-digits/) — Template 4 with `>` and a deletion budget; store digits for reconstruction.
12. [Car Fleet](12-CarFleet.java) · [LeetCode](https://leetcode.com/problems/car-fleet/) · [NeetCode 150](https://neetcode.io/problems/car-fleet/question?list=neetcode150) — Template 1 with `<=` on sorted arrival times; alternative compresses stack state.
13. [Largest Rectangle in Histogram](13-LargestRectangleHistogram.java) · [LeetCode](https://leetcode.com/problems/largest-rectangle-in-histogram/) · [NeetCode 150](https://neetcode.io/problems/largest-rectangle-in-histogram/question?list=neetcode150) — Template 6 with `>`; compute area on pop and flush with a virtual zero.
14. [Trapping Rain Water](14-TrappingRainWater.java) · [LeetCode](https://leetcode.com/problems/trapping-rain-water/) · [NeetCode 150](https://neetcode.io/problems/trapping-rain-water/question?list=neetcode150) — Template 3; `<=`; compute water layers between the two walls.
15. [Number of Visible People in a Queue](15-NumberOfVisiblePeopleInQueue.java) · [LeetCode](https://leetcode.com/problems/number-of-visible-people-in-a-queue/) — Template 3; `<=`; count visibility inside and after while; distinct heights.
16. [Sum of Subarray Minimums](16-SumSubarrayMinimums.java) · [LeetCode](https://leetcode.com/problems/sum-of-subarray-minimums/) — Template 6; `>=`; next smaller-or-equal plus previous strictly smaller.
17. [132 Pattern](17-Find132Pattern.java) · [LeetCode](https://leetcode.com/problems/132-pattern/) — Template 2; `<=`; query previous greater after while, then test prefix minimum.
18. [Maximal Rectangle](18-MaximalRectangle.java) · [LeetCode](https://leetcode.com/problems/maximal-rectangle/) — Template 6 with `>` for each row’s histogram; compute area on pop.

## Template reference

Scan left to right and store indices. Choose a comparison, pop invalid candidates,
process resolved answers during popping, optionally inspect the surviving top,
then push the current index. Stack order below is **bottom to top**; use
`Stack` with `push`, `peek`, and `pop` to operate on the top.

```java
Stack<Integer> stack = new Stack<>();
for (int i = 0; i < values.length; i++) {
    while (!stack.isEmpty() && values[stack.peek()] < values[i]) {
        int resolved = stack.pop();
        nextGreater[resolved] = i;
    }
    // If needed, stack.peek() now gives the previous greater-or-equal index.
    stack.push(i);
}
```

This concrete example finds strictly greater successors; initialize missing
successors to `-1`. Change the comparison and processing position as follows:

| Guide template | Boundary sought | Pop comparison | Stack values | Process answer |
| --- | --- | --- | --- | --- |
| 1 | Next > | `<` | Non-increasing | Inside while |
| 2 | Previous > | `<=` | Strictly decreasing | After while |
| 3 | Next >= and previous > | `<=` | Strictly decreasing | Both positions |
| 4 | Next < | `>` | Non-decreasing | Inside while |
| 5 | Previous < | `>=` | Strictly increasing | After while |
| 6 | Next <= and previous < | `>=` | Strictly increasing | Both positions |

For template 6, `>` instead gives next < and previous <=. A merged pass makes
one boundary strict and the other inclusive; this matters when values repeat.
For next >= alone use `<=`; for next <= alone use `>=`. Each index is pushed
and popped at most once, giving O(n) time and O(n) worst-case stack space.

## Adaptations used here

- **Circular queries:** the second pass only supplies possible answers, avoiding duplicate pushes.
- **Histogram rectangles:** the popped bar’s two boundaries immediately yield its area; a virtual zero at the end finishes positive-height bars. No boundary arrays are needed.
- **Streaming prices:** store price/day pairs because there is no backing input array; query the previous greater day after popping.
- **Car Fleet:** sorting positions from farthest to nearest makes a later slower car absorb faster fleets behind it with `<=`. The alternate nearest-first scan needs just a count and last arrival time.
- **Digit removal:** the stack stores the output digits. The extra `k > 0` guard limits deletion; once the budget is exhausted, the retained digits need not remain monotonic.
- **Subarray minima:** asymmetric boundaries assign tied minima exactly once. Cast to `long` before multiplying the contribution.
- **Visible people:** the implementation relies on the judge’s distinct-height constraint.
- **Maximal Rectangle:** build consecutive-one column heights, then apply the histogram template independently to each row.

[Pattern explanations and follow-ups](../README.md) · [Local checks](../../tests/README.md)
