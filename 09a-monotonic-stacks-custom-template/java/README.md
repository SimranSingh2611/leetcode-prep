# Java Solutions — 09a — Monotonic Stacks — Custom Template

Compare these implementations with the [original Java solutions](../../09-monotonic-stacks/java/README.md).

Compile/submit each file independently using Java 8 or later. Most use `Solution`;
Online Stock Span uses `StockSpanner`. To submit Car Fleet's alternative, rename
`CompressedSolution` to `Solution` and keep only that implementation.

The numbered order builds from four standalone templates to direct applications,
then greedy adaptations, boundary calculations, and combined techniques. All seven
problems from the [source guide](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/)
remain included once. The teaching templates share one resolve-on-pop structure:
next queries scan forward, previous queries scan backward.

The four standalone templates at 01–04 return nearest strictly qualifying **indices**,
or `-1` when absent; they are not separate LeetCode submissions. All implementations
preserve their inputs. See the [learning stages](../README.md#practice-progression)
for the reasoning behind the order.

## Problem order

1. [Next Greater](01-NextGreater.java) — Forward scan; pop `<`; record the popped index's successor.
2. [Previous Greater](02-PreviousGreater.java) — Reverse scan; pop `<`; record the popped index's predecessor.
3. [Next Smaller](03-NextSmaller.java) — Forward scan; pop `>`; record the popped index's successor.
4. [Previous Smaller](04-PreviousSmaller.java) — Reverse scan; pop `>`; record the popped index's predecessor.
5. [Next Greater Element I](05-NextGreaterElementI.java) · [LeetCode](https://leetcode.com/problems/next-greater-element-i/) — Next Greater; record values in a distinct-value answer map.
6. [Final Prices With a Special Discount in a Shop](06-FinalPrices.java) · [LeetCode](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) — Next Smaller-or-Equal; pop `>=`; subtract the discount from a copy.
7. [Daily Temperatures](07-DailyTemperatures.java) · [LeetCode](https://leetcode.com/problems/daily-temperatures/) · [NeetCode 150](https://neetcode.io/problems/daily-temperatures/question?list=neetcode150) — Next Greater; record distance `i - j`.
8. [Buildings With an Ocean View](08-BuildingsWithOceanView.java) · [LeetCode](https://leetcode.com/problems/buildings-with-an-ocean-view/) — Next Greater-or-Equal; pop `<=`; buildings without blockers have views. **Premium.**
9. [Online Stock Span](09-OnlineStockSpan.java) · [LeetCode](https://leetcode.com/problems/online-stock-span/) — Streaming previous-greater query; discard `<=`, then read the surviving top.
10. [Next Greater Element II](10-NextGreaterElementII.java) · [LeetCode](https://leetcode.com/problems/next-greater-element-ii/) — Next Greater; circular scan, push only on the first pass.
11. [Remove K Digits](11-RemoveKDigits.java) · [LeetCode](https://leetcode.com/problems/remove-k-digits/) — Greedy deletion; pop `>` while the deletion budget remains.
12. [Car Fleet](12-CarFleet.java) · [LeetCode](https://leetcode.com/problems/car-fleet/) · [NeetCode 150](https://neetcode.io/problems/car-fleet/question?list=neetcode150) — Sorted fleet merging; pop `<=` on arrival times; alternative compresses stack state.
13. [Largest Rectangle in Histogram](13-LargestRectangleHistogram.java) · [LeetCode](https://leetcode.com/problems/largest-rectangle-in-histogram/) · [NeetCode 150](https://neetcode.io/problems/largest-rectangle-in-histogram/question?list=neetcode150) — Previous Smaller + Next Smaller, then compute areas.
14. [Trapping Rain Water](14-TrappingRainWater.java) · [LeetCode](https://leetcode.com/problems/trapping-rain-water/) · [NeetCode 150](https://neetcode.io/problems/trapping-rain-water/question?list=neetcode150) — Water layers; pop a bottom and inspect its surviving left wall.
15. [Number of Visible People in a Queue](15-NumberOfVisiblePeopleInQueue.java) · [LeetCode](https://leetcode.com/problems/number-of-visible-people-in-a-queue/) — Count visibility for popped people and the surviving blocker; distinct heights.
16. [Sum of Subarray Minimums](16-SumSubarrayMinimums.java) · [LeetCode](https://leetcode.com/problems/sum-of-subarray-minimums/) — Previous Smaller + Next Smaller-or-Equal; asymmetric ties prevent double-counting.
17. [132 Pattern](17-Find132Pattern.java) · [LeetCode](https://leetcode.com/problems/132-pattern/) — Previous Greater from a reverse scan, then test prefix minima.
18. [Maximal Rectangle](18-MaximalRectangle.java) · [LeetCode](https://leetcode.com/problems/maximal-rectangle/) — Build row histograms and call a private helper using exercise 13's two-boundary approach.

## Template reference

The stack holds indices waiting for an answer. Current index `i` resolves qualifying
tops; popped index `j` receives the answer. Push `i` last so it can wait for its own
answer. Use `Stack<Integer>` with `push`, `peek`, and `pop`.

**Direction chooses next or previous. Comparison chooses greater or smaller.**
All four templates record `answer[j] = i` **inside the while loop**.

| Boundary sought | Scan direction | Pop comparison: `values[top] ... values[i]` | Stack values, bottom to top |
| --- | --- | --- | --- |
| Next strictly greater | Left → right | `<` | Non-increasing |
| Previous strictly greater | Right → left | `<` | Non-increasing |
| Next strictly smaller | Left → right | `>` | Non-decreasing |
| Previous strictly smaller | Right → left | `>` | Non-decreasing |

For example, Next Greater:

```java
int[] answer = new int[values.length];
Arrays.fill(answer, -1);
Stack<Integer> stack = new Stack<>();
for (int i = 0; i < values.length; i++) {
    while (!stack.isEmpty() && values[stack.peek()] < values[i]) {
        int j = stack.pop();
        answer[j] = i;
    }
    stack.push(i);
}
```

For Previous Greater, only replace the loop header:

```java
for (int i = values.length - 1; i >= 0; i--) {
```

For either smaller query, change `<` to `>`. Equal values remain waiting in all
four strict templates. For greater-or-equal use `<=`; for smaller-or-equal use
`>=`, with the same direction rule. That equality change makes the corresponding
stack order strict.

Why is the answer nearest? An index is popped at the first qualifying value
encountered in the scan direction. Any closer qualifying value would already have
resolved it. Each index is pushed and popped at most once, giving O(n) time and
O(n) worst-case stack space per pass.

## Combining two boundaries

Use a reverse pass for `left` and a forward pass for `right`, clearing the stack
between them. For width/contribution calculations, initialize missing boundaries
to `-1` on the left and `n` on the right. Standalone templates instead use `-1`
for every missing answer.

| Exercise | Left boundary (reverse pass) | Right boundary (forward pass) | Calculation |
| --- | --- | --- | --- |
| Histogram / Maximal Rectangle | Previous strictly smaller; pop `>` | Next strictly smaller; pop `>` | `height[i] * (right[i] - left[i] - 1)` |
| Subarray Minimums | Previous strictly smaller; pop `>` | Next smaller-or-equal; pop `>=` | `(long) arr[i] * (i - left[i]) * (right[i] - i)` |

For histogram maxima, equal bars may evaluate the same rectangle without changing
the maximum. For subarray sums, each subarray must belong to exactly one minimum:
the asymmetric comparisons assign ties to the rightmost minimum. For `[2, 2]`,
the two-element subarray belongs to index 1. Two strict boundaries would count it
twice; two inclusive boundaries would omit it.

The separate passes make each boundary visible. An advanced optimization can fuse
them into one forward pass: current `i` resolves a right boundary while the
surviving top supplies a left boundary. Histogram area can then be computed at pop
time, with a final flush. The primary solutions use explicit arrays to show the
connection to the foundational templates.

## Adaptations used here

- **Answer values and distances:** Next Greater Element I maps `nums2[j]` to `nums2[i]`; Daily Temperatures records `i - j`.
- **Discounts:** Final Prices uses next smaller-or-equal. Subtract from a copy so comparisons use original prices.
- **Ocean views:** Find next greater-or-equal blockers, then collect indices with no blocker in ascending order.
- **Circular queries:** The second virtual pass only supplies possible answers; each original index is pushed once.
- **Streaming prices:** Future prices are unavailable. Online Stock Span removes invalid previous-greater candidates with `<=`, queries the surviving top, then pushes today's price/day pair. This is a candidate-query adaptation, rather than the reverse-scan waiting-list template.
- **Car Fleet:** Sort positions from farthest to nearest. A later slower car ahead absorbs faster fleets behind it with `<=` on arrival times. The alternative needs only a fleet count and last arrival time.
- **Digit removal:** Popping deletes a worse earlier digit. The extra `k > 0` guard limits deletion; once the budget is exhausted, retained digits need not remain monotonic. The character array acts as the digit stack.
- **Water layers:** Pop basin bottoms, inspect the surviving left wall, and add the newly enclosed layer. This calculation needs both walls, not just one recorded neighbor.
- **Visible people:** Count each popped person's visibility and the nearest surviving taller person's visibility. The implementation relies on distinct heights.
- **132 Pattern:** Compute previous-greater indices first. For each possible "2", its previous greater supplies "3"; a smaller prefix minimum supplies "1". The nearest greater suffices because its prefix includes any qualifying earlier small value.
- **Maximal Rectangle:** Build consecutive-one column heights, then apply a private copy of the histogram helper to every row. The file remains independently submittable.

## Relation to the source guide

The source guide describes six **left-to-right** variants, including previous
queries answered from the surviving top and merged boundary queries. Those guide
numbers are not the numbers of our four teaching templates. In particular, our
Previous Greater uses reverse scan / pop `<`, whereas the guide's forward
candidate-query version uses pop `<=` and answers the current index after popping.
Previous Smaller has the analogous distinction (`>` versus `>=`). Both approaches
return the same nearest strict predecessors; their stacks have different meanings.

Learn the shared resolve-on-pop structure first, then the candidate-query,
greedy, and combined-boundary adaptations.

Run these 18 implementations from the workspace root with
`bash leetcode-prep/tests/verify.sh 09a-monotonic-stacks-custom-template`.
They reuse the original section's test cases, including the strengthened matrix checks.

[Pattern explanations and follow-ups](../README.md) · [Local checks](../../tests/README.md)
