# 09a — Monotonic Stacks — Custom Template

This alternate version preserves the shared resolve-on-pop templates and explicit
boundary passes. Compare with the [original forward-template version](../09-monotonic-stacks/README.md).
Both directories contain the same 18 exercises; this is additional implementation
practice alongside step 09.

A monotonic stack is a stack with an ordering invariant. Start with indices waiting for an answer: the current index resolves qualifying tops, then joins the stack. Learn one boundary, adapt the recorded answer, then combine boundaries. Sorting is an explicit prerequisite for Car Fleet.

## How to recognize this pattern

**Keywords / hints:** “next greater”, “first smaller to the right”, “previous smaller”, “consecutive span”, “largest histogram rectangle”, “sum of subarray minima”.

**Structural test:** Can a new element resolve pending queries or permanently dominate older candidates, so popped candidates will never be needed again?

**Invariant / mental model:** Indices remain ordered by position, and their values obey a chosen increasing/decreasing order. Choose strict versus non-strict comparisons deliberately for equal values.

**When not to use it:** “Maximum” alone does not imply a monotonic stack. Fixed-window expiration often needs a monotonic deque; arbitrary updates need a different structure.

**Prerequisites:** 08-stacks, array indices, amortized analysis; sorting for Car Fleet. Matrix-to-histogram reduction is introduced at the end.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

18 exercises: 14 LeetCode problems (2 Easy / 8 Medium / 4 Hard) and 4 standalone templates.
Follow the numbered learning progression: the four basic templates first, then direct
applications, followed by greedy adaptations, boundary calculations, and combined techniques.
All seven problems from the [monotonic-stack guide](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/)
are retained, with overlaps included once. The order reflects prerequisites and added
reasoning rather than a strict Easy/Medium/Hard ranking.

- **01–04:** one resolve-on-pop structure; direction chooses next/previous, comparison chooses greater/smaller.
- **05–10:** direct applications, equality, distances, survivors, streaming, and circular traversal.
- **11–12:** greedy digit removal and sorted fleet merging.
- **13–16:** histogram boundaries, water layers, visibility, and subarray contributions.
- **17–18:** prefix-minimum reasoning for 132 Pattern and the matrix-to-histogram reduction.

See the [Java template reference](./java/README.md#template-reference) for the
operator table and per-problem adaptations. Next-element queries scan left to right;
previous-element queries scan right to left. Both resolve the popped index inside
the while loop. Streaming, greedy, water-layer, and visibility exercises explain
their additional rules explicitly. The source guide's numbered variants use a
different, all-forward presentation; our four teaching templates use semantic names.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Next Greater](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/) — Template

**Source:** Monotonic-stack guide · **Practice role:** Foundational template exercise

**Recognize it:** For every index, find the nearest strictly greater value to its right in a non-circular array.

**Contract:** `nextGreaterIndexes(int[] values)` returns **indices**, with `-1` when absent. This is a standalone exercise, not a separately numbered LeetCode problem. Empty input returns an empty array; the input is preserved.

**Example:** `[2, 2, 3, 1]` → `[2, 2, -1, -1]`.

**Talking points:**

- I'd keep unresolved indices in non-increasing value order.
- A larger incoming value resolves each smaller top as its next greater answer; equal values
  stay unresolved.

**Solution:** Scan left to right. Pop strictly smaller values and assign the current index to each popped index inside the while loop. Equal values stay unresolved. Push the current index last, maintaining non-increasing values from bottom to top.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output indices.

**Java:** [NextGreater](./java/01-NextGreater.java) — `nextGreaterIndexes`; comments identify the comparison and processing position.

**Potential interviewer follow-ups:**

- Why keep equal values? An equal value cannot answer a strictly greater query.
- How does a circular array change this? Scan twice and push only on the first pass, as in exercise 10.

## 2. [Previous Greater](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/) — Template

**Source:** Monotonic-stack guide · **Practice role:** Foundational template exercise

**Recognize it:** For every index, find the nearest strictly greater value to its left.

**Contract:** `previousGreaterIndexes(int[] values)` returns **indices**, with `-1` when absent. This is a standalone exercise, not a separately numbered LeetCode problem. Empty input returns an empty array; the input is preserved.

**Example:** `[2, 2, 3, 1]` → `[-1, -1, -1, 2]`.

**Talking points:**

- I'd scan right to left, keeping indices waiting for a greater value on their left.
- A larger current value resolves each smaller top; equal values keep waiting.

**Solution:** Scan right to left. When the current value is greater than the top's value, pop index j and set answer[j] = i. This is the same pop < comparison and inside-while assignment as Next Greater; only the scan direction changes. Equal values remain pending. Stack values are non-increasing from bottom to top.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output indices.

**Java:** [PreviousGreater](./java/02-PreviousGreater.java) — `previousGreaterIndexes`; comments identify the comparison and processing position.

**Potential interviewer follow-ups:**

- Why return indices? They distinguish duplicates and let callers recover either values or distances.
- Why keep equal values? Equal values cannot resolve a strictly greater query, so both keep waiting.

## 3. [Next Smaller](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/) — Template

**Source:** Monotonic-stack guide · **Practice role:** Foundational template exercise

**Recognize it:** For every index, find the nearest strictly smaller value to its right.

**Contract:** `nextSmallerIndexes(int[] values)` returns **indices**, with `-1` when absent. This is a standalone exercise, not a separately numbered LeetCode problem. Empty input returns an empty array; the input is preserved.

**Example:** `[2, 2, 3, 1]` → `[3, 3, 3, -1]`.

**Talking points:**

- I'd keep unresolved indices in non-decreasing value order.
- A smaller incoming value resolves each larger top as its next smaller answer; equal values
  remain pending.

**Solution:** Scan left to right. Pop strictly larger values. Inside the while loop, assign the current index as the popped index's next smaller answer. Equal values stay unresolved. Push the current index last. The stack's values are non-decreasing from bottom to top.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output indices.

**Java:** [NextSmaller](./java/03-NextSmaller.java) — `nextSmallerIndexes`; comments identify the comparison and processing position.

**Potential interviewer follow-ups:**

- Why return indices? They distinguish duplicates and let callers recover either values or distances.
- Why keep equal values? Equal values are not strictly smaller answers; both indices may resolve later.

## 4. [Previous Smaller](https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/) — Template

**Source:** Monotonic-stack guide · **Practice role:** Foundational template exercise

**Recognize it:** For every index, find the nearest strictly smaller value to its left.

**Contract:** `previousSmallerIndexes(int[] values)` returns **indices**, with `-1` when absent. This is a standalone exercise, not a separately numbered LeetCode problem. Empty input returns an empty array; the input is preserved.

**Example:** `[2, 2, 3, 1]` → `[-1, -1, 1, -1]`.

**Talking points:**

- I'd scan right to left, keeping indices waiting for a smaller value on their left.
- A smaller current value resolves each larger top; equal values keep waiting.

**Solution:** Scan right to left. When the current value is smaller than the top's value, pop index j and set answer[j] = i. This is the same pop > comparison and inside-while assignment as Next Smaller; only the scan direction changes. Equal values remain pending. Stack values are non-decreasing from bottom to top.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output indices.

**Java:** [PreviousSmaller](./java/04-PreviousSmaller.java) — `previousSmallerIndexes`; comments identify the comparison and processing position.

**Potential interviewer follow-ups:**

- Why return indices? They distinguish duplicates and let callers recover either values or distances.
- Why keep equal values? Equal values cannot resolve a strictly smaller query, so both keep waiting.

## 5. [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** For each value, find the first larger value strictly to its right.

**Talking points:**

- I'd scan the second array with indices awaiting their next greater value.
- Each larger arrival resolves smaller tops, and a map answers the first array's queries
  afterward.

**Solution:** Scan nums2 with a decreasing stack of unresolved indices. A larger incoming value resolves every smaller top. Record those answers in a map; unanswered values map to -1 when queried from nums1.

**Complexity:**

**Time complexity:** `O(n + m)` expected for second-array length `n` and first-array length `m`.

**Space complexity:** `O(n)` auxiliary stack/map storage, plus `O(m)` output.

**Java:** [NextGreaterElementI](./java/05-NextGreaterElementI.java) — Solution; relies on the challenge's distinct values.

**Potential interviewer follow-ups:**

- If duplicates were allowed? Store indices rather than value-only keys.
- Why pop repeatedly? One new value may be the first greater element for many waiting entries.

## 6. [Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** The discount is the first later value less than or equal to the current price.

**Talking points:**

- I'd keep indices whose first qualifying discount has not arrived yet.
- An incoming price resolves all previous prices at least as large; comparisons use the original
  array while discounts go into a copy.

**Solution:** Keep indices whose discounts are unresolved in a strictly increasing-price stack. An incoming price resolves tops with price>=incoming. Subtract from a copy so stack comparisons continue to use original prices.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` prices.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output copy.

**Java:** [FinalPrices](./java/06-FinalPrices.java) — Solution; preserves input.

**Potential interviewer follow-ups:**

- Why >= rather than >? Equal prices qualify for the discount.
- Can it be done in place? Yes, but ensure comparisons never use already-discounted values.

## 7. [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/daily-temperatures/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** Included in the guide; placed here in the learning progression.

**Recognize it:** Find how far away the first strictly warmer day is.

**Talking points:**

- I'd stack unresolved day indices in non-increasing temperature order.
- A warmer day resolves colder tops, and its index difference gives their waiting times.

**Solution:** Keep unresolved day indices with nonincreasing temperatures. When a warmer day arrives, pop colder days and record the index difference. Equal temperatures stay unresolved. Zero remains for days with no warmer future day.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` days.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output.

**Java:** [DailyTemperatures](./java/07-DailyTemperatures.java) — Solution; output is a distance, not a temperature.

**Potential interviewer follow-ups:**

- Why store indices? Distances and duplicate temperatures require positions.
- How would the comparison change for warmer-or-equal? Pop equal values too.

## 8. [Buildings With an Ocean View](https://leetcode.com/problems/buildings-with-an-ocean-view/) — Medium

**Source:** Monotonic-stack guide · **Practice role:** Additional pattern practice

**Access:** LeetCode Premium

**Recognize it:** A building survives only if every building to its right is shorter.

**Talking points:**

- I'd find each building's next greater-or-equal blocker to its right.
- Buildings with no blocker have ocean views; collect their indices in original order.

**Solution:** Find next greater-or-equal blockers with a left-to-right scan and pop <=. Record nextBlocker[j] = i for each popped index. Buildings whose blocker remains -1 have ocean views; collect them by increasing index.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` buildings.

**Space complexity:** `O(n)` auxiliary stack, plus up to `O(n)` output.

**Java:** [BuildingsWithOceanView](./java/08-BuildingsWithOceanView.java) — Next Greater-or-Equal adaptation; input preserved.

**Potential interviewer follow-ups:**

- Why pop equal heights? An equal building blocks the ocean too.
- Why collect unblocked buildings in a separate forward scan? It returns the required increasing index order.

## 9. [Online Stock Span](https://leetcode.com/problems/online-stock-span/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** For each arriving price, count consecutive previous days whose prices are <= it.

**Talking points:**

- I'd keep decreasing prices paired with their day indices.
- After removing prices at most today's price, the previous greater day's index determines the
  span.

**Solution:** Keep decreasing prices paired with day indices. Pop prices <= today, then subtract the surviving previous-greater day from today's index. The implementation numbers days from 1, using 0 when no greater predecessor exists. This online API cannot scan backward over future prices, so it prunes candidates and queries the top after popping.

**Complexity:**

**Time complexity:** `O(1)` amortized per `next`; one call can take `O(n)` after `n` prior
prices.

**Space complexity:** `O(n)` persistent stack space in the worst case.

**Java:** [OnlineStockSpan](./java/09-OnlineStockSpan.java) — StockSpanner design class.

**Potential interviewer follow-ups:**

- Why subtract indices? The previous greater day bounds the entire qualifying consecutive suffix.
- Is one operation always constant time? No; a single large price may pop many entries.

## 10. [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Selection:** Included in the guide; placed here in the learning progression.

**Recognize it:** Next-greater queries wrap around a circular array, but an element cannot answer itself.

**Talking points:**

- I'd scan two virtual copies of the array to cover wraparound.
- Only the first pass pushes indices; both passes can resolve a pending index with a larger
  value.

**Solution:** Scan two virtual passes using i%n. Push indices only in the first pass and resolve smaller tops in either pass. Every original index is pushed once; a second pass provides wraparound candidates.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output.

**Java:** [NextGreaterElementII](./java/10-NextGreaterElementII.java) — Solution; duplicates require strict greater-than.

**Potential interviewer follow-ups:**

- Why not push in both passes? It creates redundant pending entries.
- What happens to a global maximum? It remains unanswered, including when another equal maximum exists.

## 11. [Remove K Digits](https://leetcode.com/problems/remove-k-digits/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Remove a budgeted number of digits to minimize the earliest differing position.

**Talking points:**

- I'd remove a larger earlier digit whenever a smaller new digit arrives and deletions remain.
- Any leftover deletions come from the end, then I'd strip leading zeros and return zero if
  nothing remains.

**Solution:** Maintain a nondecreasing digit stack while deletions remain. Pop a larger previous digit before appending a smaller one. If deletions remain after the scan, remove trailing digits. Strip leading zeros from the retained result.

**Complexity:**

**Time complexity:** `O(n)` amortized for `n` digits.

**Space complexity:** `O(n)` stack/buffer/output storage.

**Java:** [RemoveKDigits](./java/11-RemoveKDigits.java) — Solution; monotonic-stack plus greedy proof.

**Potential interviewer follow-ups:**

- Why remove from the end if k remains? The retained digits are nondecreasing, so trailing ones are least valuable.
- Why is >= not needed? Removing equal digits early provides no lexicographic improvement.

## 12. [Car Fleet](https://leetcode.com/problems/car-fleet/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/car-fleet/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Cars cannot pass; a later car with an earlier arrival time merges into a fleet ahead.

**Talking points:**

- I'd sort cars from farthest to nearest and compare their arrival times.
- A car ahead absorbs faster fleets behind it; unresolved fleet leaders stay on the stack.

**Solution:** The primary solution sorts farthest to nearest, then applies pop <= to arrival times: faster fleets behind merge into the current slower car ahead. Push its index; surviving indices count fleets. The alternative scans nearest to farthest and stores only the last fleet time and count.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` cars, for both supplied solutions.

**Space complexity:** `O(n)` sorting/copy/stack storage for the primary solution; the
alternative uses `O(1)` scan state after `O(n)` sorting storage.

**Java:** [CarFleet](./java/12-CarFleet.java) — Solution uses an explicit arrival stack; CompressedSolution reduces scan state.

**Potential interviewer follow-ups:**

- Does catching exactly at the target count? Yes; equality merges.
- Why sort farthest first in the primary solution? A slower car ahead can absorb several faster fleets already on the stack.

## 13. [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/largest-rectangle-in-histogram/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Selection:** Included in the guide; placed here in the learning progression.

**Recognize it:** Each bar can extend until a smaller bar blocks it on either side.

**Talking points:**

- I'd find each bar's previous and next strictly smaller indices in separate passes.
- The bar spans the space between those boundaries; equal heights may share a rectangle.

**Solution:** Combine Previous Smaller (right to left, pop >) and Next Smaller (left to right, pop >). In each pass assign the boundary of the popped index. Initialize missing left/right boundaries to -1/n, clear the stack between passes, then maximize heights[i] * (right[i] - left[i] - 1). Both boundaries are strict: repeated evaluation of an equal-height rectangle is harmless for a maximum.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` bars.

**Space complexity:** `O(n)` auxiliary boundary arrays and stack space.

**Java:** [LargestRectangleHistogram](./java/13-LargestRectangleHistogram.java) — Solution; two explicit boundary passes; input preserved.

**Potential interviewer follow-ups:**

- Can the two passes be fused? Yes; compute area on pop using the current right boundary and surviving left boundary, then flush pending bars at the end.
- How can you return rectangle coordinates? Save the winning height and computed boundaries.

## 14. [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/trapping-rain-water/question?list=neetcode150)

**Source:** Monotonic-stack guide · **Practice role:** Advanced stretch

**Access:** Free problem

**Recognize it:** A taller incoming wall closes horizontal water layers above shorter bars.

**Talking points:**

- I'd use a stack to recognize basin bottoms as a right wall arrives.
- Popping a bottom exposes its left wall, letting me add the newly enclosed water layer.

**Solution:** Pop basin bottoms with <=. If a left wall remains, add (min(left height, right height) - bottom height) times the interior width. Each pop accounts for a new layer.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` heights.

**Space complexity:** `O(n)` auxiliary stack; the two-pointer solution in pattern 03 uses
`O(1)`.

**Java:** [TrappingRainWater](./java/14-TrappingRainWater.java) — Water-layer adaptation documented in the code; input preserved.

**Potential interviewer follow-ups:**

- What if the stack empties? There is no left wall to contain water.
- Can auxiliary space be constant? Yes; compare the two-pointer version linked below.

**Related:** [Two-pointer treatment](../03-two-pointers/README.md#9-trapping-rain-water--hard). This intentional cross-pattern repeat preserves both approaches.

## 15. [Number of Visible People in a Queue](https://leetcode.com/problems/number-of-visible-people-in-a-queue/) — Hard

**Source:** Monotonic-stack guide · **Practice role:** Advanced stretch

**Access:** Free problem

**Recognize it:** An incoming person is visible to the shorter people popped and to the nearest surviving taller person.

**Talking points:**

- I'd keep a decreasing-height stack while scanning people from left to right.
- Each popped person sees the new person, and the nearest taller survivor sees them too while
  blocking everyone behind it.

**Solution:** Use both processing positions: increment each popped person inside while, then increment the surviving top after while. Push the current index. The problem guarantees distinct heights.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` people.

**Space complexity:** `O(n)` auxiliary stack, plus `O(n)` output counts.

**Java:** [NumberOfVisiblePeopleInQueue](./java/15-NumberOfVisiblePeopleInQueue.java) — Visibility-counting adaptation documented in the code; input preserved.

**Potential interviewer follow-ups:**

- Why only increment one surviving person? That taller person hides the current person from the other survivors.
- Can this be used unchanged with duplicate heights? No; equal-height blockers require additional handling.

## 16. [Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Instead of enumerating subarrays, count how many choose each element as their minimum.

**Talking points:**

- I'd count how many subarrays choose each value as their minimum using its left and right
  boundaries.
- One boundary must be strict and the other non-strict so equal minima are assigned exactly
  once.

**Solution:** Find previous strictly smaller boundaries with a reverse scan and pop >, then next smaller-or-equal boundaries with a forward scan and pop >=. Both passes resolve popped indices inside while; clear the stack between them. Missing boundaries are -1/n. Element i owns (i-left[i])*(right[i]-i) subarrays. The asymmetric equality rule assigns tied minima to the rightmost occurrence exactly once. Multiply contributions in long and reduce modulo 1e9+7.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary boundary/stack space.

**Java:** [SumSubarrayMinimums](./java/16-SumSubarrayMinimums.java) — Solution; contribution-counting stretch within Medium.

**Potential interviewer follow-ups:**

- Why use different strictness on the two sides? Symmetric ties either double-count or omit subarrays.
- How would you sum maxima? Reverse the comparisons.

## 17. [132 Pattern](https://leetcode.com/problems/132-pattern/) — Medium

**Source:** Monotonic-stack guide · **Practice role:** Additional pattern practice

**Access:** Free problem

**Recognize it:** Use the current value as the final 2, an earlier greater value as 3, and an earlier minimum as 1.

**Talking points:**

- I'd find previous-greater indices with a reverse scan, then compute prefix minima.
- For each possible "2", its previous greater supplies "3"; a smaller prefix supplies "1".

**Solution:** First compute previousGreater with the reverse-scan Previous Greater template: pop < and assign previousGreater[j] = i. Then build prefix minima. For each current index i (the "2"), its previous greater index peak supplies the "3"; prefixMin[peak] < nums[i] supplies an earlier "1". The nearest greater suffices: if a valid earlier peak exists, any closer greater peak's prefix still contains that earlier small value.

**Complexity:**

**Time complexity:** `O(n)` amortized overall for `n` values.

**Space complexity:** `O(n)` auxiliary previous-greater/prefix-minimum arrays and stack space.

**Java:** [Find132Pattern](./java/17-Find132Pattern.java) — Previous Greater plus prefix minima; input preserved.

**Potential interviewer follow-ups:**

- Why are all comparisons strict? Equal values cannot fill two roles in a 132 pattern.
- Why can the prefix minimum include the peak? A qualifying minimum is below the current value, which is below the peak, so it necessarily occurs earlier.

## 18. [Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** An all-ones rectangle can be viewed as a histogram of consecutive ones ending at a row.

**Talking points:**

- I'd turn each row into histogram heights of consecutive ones ending there.
- A histogram helper combines previous/next strictly smaller boundaries for each row.

**Solution:** Update each column's height with consecutive ones up to the current row, resetting on zero. Call a private histogram helper that repeats exercise 13's two explicit boundary passes: previous strictly smaller backward, next strictly smaller forward, both pop >. Maximize height * (right-left-1). Every all-ones rectangle is considered at its bottom row.

**Complexity:**

**Time complexity:** `O(RC)` for `R` rows and `C` columns.

**Space complexity:** `O(C)` auxiliary histogram, boundary arrays, and stack space.

**Java:** [MaximalRectangle](./java/18-MaximalRectangle.java) — Solution; does not mutate the matrix.

**Potential interviewer follow-ups:**

- Why isn't largest square logic sufficient? Rectangle width and height can differ.
- Can this work row-by-row from a stream? Yes; only heights and current row are needed.
