# 06 — Binary Search

All sixteen primary Java solutions use one adjacent-endpoint loop convention. The
organizing idea follows [darkalarm's guide](https://leetcode.com/discuss/post/2371234/an-opinionated-guide-to-binary-search-co-1yfw/):
find either the earliest qualifying candidate or the latest qualifying candidate,
keep opposite-truth endpoints, and stop when those endpoints become adjacent.
The implementations and problem-specific proofs below adapt that idea to Java.
Find Peak Element uses the same loop with a local slope invariant and returns
any peak; its slope condition is not a monotonic first-true predicate.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) ·
[All-pattern recognition guide](../PATTERN_RECOGNITION.md)

## How to recognize this pattern

**Keywords / hints:** sorted, first/last occurrence, minimum feasible, maximum
allowed, predecessor timestamp, or a partition boundary.

**Structural test:** On an ordered candidate domain, can you prove the predicate
has one transition: false to true, or true to false? The input itself need not
be sorted if the candidate answers have monotonic feasibility.

**Invariant / mental model:** For first-true search, lo is false and hi is true.
For last-true search, lo is true and hi is false. Endpoints can be real candidates
with known truth or virtual sentinels with assigned truth. Search only strictly
between them; never evaluate an out-of-domain sentinel.

**When not to use it:** A minimum/maximum objective alone is insufficient.
The predicate and endpoint truth must be justified for this problem. An equality
test on a sorted array is generally not a monotonic boundary predicate.

**Prerequisites:** Sorted-order reasoning, index bounds, integer-overflow
awareness, and a correct feasibility checker for answer-space problems.

**Difficulty mix:** 6 Easy / 8 Medium / 2 Hard

## The two loop forms

These Java-shaped snippets show the convention, not a standalone submission:
choose lo, hi, and condition for the particular problem.

```java
// First true: false ... false | true ... true
while (lo + 1 < hi) {
    int mid = lo + (hi - lo) / 2;
    if (condition(mid)) hi = mid;
    else lo = mid;
}
return hi;
```

```java
// Last true: true ... true | false ... false
while (lo + 1 < hi) {
    int mid = lo + (hi - lo) / 2;
    if (condition(mid)) lo = mid;
    else hi = mid;
}
return lo;
```

The int-bound solutions use `int mid = lo + (hi - lo) / 2`. This assumes the
nonnegative difference `hi - lo` fits in int; with lo=-1 and hi=n, that requires
n<Integer.MAX_VALUE, as the array/list size limits in these exercises ensure.
When adapting the template to a wider range, use long bounds and a long midpoint.
The matrix, split-array, and coin solutions already use long arithmetic for their
larger ranges or intermediate calculations.

A midpoint always lies strictly between nonadjacent endpoints, so assigning
either endpoint to it makes progress without mid+1/mid-1 updates.

## Choosing lo and hi correctly

For this template, **minimization** means finding the first true in a
`false ... false | true ... true` sequence. Keep `condition(lo) = false` and
`condition(hi) = true`, with the first true in `(lo, hi]`. During the loop, hi
is a known-true upper bound; it is guaranteed to be the answer when `lo + 1 == hi`.

Choose any known-false lower bound and known-true upper bound that bracket the
transition. The smallest invalid value and largest valid value are sufficient
when they exist, but are not required. For example, Split Array uses
`lo = max(nums)-1` and `hi = sum(nums)`; the lower bound is a tight known-invalid
limit rather than an absolute smallest invalid value.

For **maximization**, find the last true in a
`true ... true | false ... false` sequence. Keep `condition(lo) = true` and
`condition(hi) = false`, with the last true in `[lo, hi)`. Return lo when the
bounds become adjacent.

Out-of-domain endpoints can be virtual sentinels with assigned truth. For
example, lower bound uses `lo = -1` (false) and `hi = n` (true); never read
`nums[-1]` or `nums[n]`. A final `hi == n` means no real element qualifies.

The labels below describe the **candidate searched by each implementation**.
Exact lookup finds a qualifying index before checking equality; Median finds a
qualifying cut before calculating its result. Classification depends on the
chosen predicate, so another implementation can use a different search form.

## Set up every problem the same way

1. Define exactly what a candidate x means: index, speed, sum limit, or cut count.
2. Write condition(x) and prove its truth sequence is monotonic.
3. Choose first true or last true.
4. Initialize opposite-truth endpoints. Explain whether each is real or virtual.
5. Run the unchanged loop form.
6. Translate the boundary into the requested output and handle no-match sentinels.

For Find Peak Element, replace the monotonic-predicate proof with the local
rising/falling-edge proof in problem 12.

Do not assume every numeric hi is a valid array index. For a lower bound, hi=n
means there is no qualifying array element; for insertion, that is a valid answer.
Similarly, last-true can return lo=-1 when no timestamp qualifies.

## Mapping all sixteen problems to the convention

| Problem | Search / predicate | Initial (lo, hi) | Boundary / result |
| --- | --- | --- | --- |
| 1. Binary Search | First index with value >= target | (-1, n) | hi, then bounds + equality check |
| 2. Search Insert Position | First index with value >= target | (-1, n) | hi, including n |
| 3. First Bad Version | First version for which API is true | (0, n) | hi |
| 4. Search a 2D Matrix | First flat index with value >= target | (-1, rows·cols) | hi, then bounds + equality check |
| 5. Koko Eating Bananas | First speed that finishes within h | (0, max pile) | hi |
| 6. Find Minimum in Rotated Sorted Array | First index with value <= fixed last value | (-1, n-1) | nums[hi] |
| 7. Search in Rotated Sorted Array | Pivot search, then lower bound in selected segment | (-1, n-1), then (start-1, endExclusive) | second hi, then bounds + equality check |
| 8. Time Based Key-Value Store | Last history index with timestamp <= query | (-1, history size) | lo, or empty string |
| 9. Median of Two Sorted Arrays | First cut with B-left <= A-right | (-1, smaller length) | use hi as the cut; compute median |
| 10. Split Array Largest Sum | First limit needing at most k parts | (max-1, sum) | hi |
| 11. Find First and Last Position | First >= target, then last <= target | (-1, n) in both loops | [first hi, last lo], or [-1,-1] |
| 12. Find Peak Element | Any peak; preserve rising/falling endpoint edges | (-1, n-1) | hi |
| 13. Arranging Coins | Last row count with k*(k+1)/2 <= n | (0, n+1), both long | lo |
| 14. Single Element in a Sorted Array | First broken even/odd pair alignment | (-1, n-1) | nums[hi] |
| 15. Find Smallest Letter Greater Than Target | First index with letter > target | (-1, n) | letters[hi], or letters[0] if hi=n |
| 16. Check If a Number Is Majority Element | First >= target, then last <= target | (-1, n) in both loops | present and last-first+1 > n/2 |

## One small trace

For nums=[2,5,8,11] and target=7, condition(i) is nums[i]>=7:

| lo | hi | mid | condition(mid) | Update |
| ---: | ---: | ---: | --- | --- |
| -1 | 4 | 1 | false | lo=1 |
| 1 | 4 | 2 | true | hi=2 |

Now lo+1==hi, so the boundary is index 2. Search Insert Position returns 2;
Binary Search checks nums[2]==7, finds it false, and returns -1.

## Practice problems

## 1. [Binary Search](https://leetcode.com/problems/binary-search/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/binary-search/question?list=neetcode150)

**Source:** NeetCode 150

**Search space:** Array indices 0…n−1.

**Template:** First true (minimization).
**Predicate:** nums[i] >= target.
**Initial bounds:** lo = -1, hi = n.
**Return:** hi if in range and equal; otherwise -1.

**Why monotonic:** Sorted order makes the predicate false below the target's lower bound and true thereafter. Equality alone is not monotonic.

**Talking points:**

- I'd find the first index whose value is at least the target using false/true endpoints.
- Then I'd verify equality and check the virtual `n` endpoint before indexing.

**Solution:** Find the first index whose value is at least target, then check equality. If every value is smaller, hi stays at the virtual endpoint n; check the bound before indexing.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [01-BinarySearch.java](./java/01-BinarySearch.java)

**Potential interviewer follow-ups:**

- How do you find the first duplicate? This lower bound already returns it; retain the equality check.
- How do you find the last duplicate? Use last-true with nums[i] <= target, then verify nums[lo] == target.

## 2. [Search Insert Position](https://leetcode.com/problems/search-insert-position/) — Easy

**Source:** Supplemental

**Search space:** Insertion positions 0…n.

**Template:** First true (minimization).
**Predicate:** nums[i] >= target for real indices.
**Initial bounds:** lo = -1, hi = n.
**Return:** hi directly, including n.

**Why monotonic:** Once an element is large enough, every later sorted element is large enough. The virtual n endpoint represents insertion after the array.

**Talking points:**

- I'd use a lower-bound search for the first value at least the target.
- Its boundary is the insertion position even when the target is absent, including position `n`.

**Solution:** Run the same lower-bound search as problem 1. Here a missing exact match is not failure: the boundary is precisely where the target should be inserted.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [02-SearchInsertPosition.java](./java/02-SearchInsertPosition.java)

**Potential interviewer follow-ups:**

- What changes for insertion after existing equals? Change the predicate to nums[i] > target.
- How can two boundaries give the number of occurrences? upperBound(target) - lowerBound(target); do not compute target+1, which can overflow.

## 3. [First Bad Version](https://leetcode.com/problems/first-bad-version/) — Easy

**Source:** Supplemental

**Search space:** Versions 1…n.

**Template:** First true (minimization).
**Predicate:** isBadVersion(version).
**Initial bounds:** lo = 0, hi = n.
**Return:** hi.

**Why monotonic:** Good versions precede bad versions. The statement guarantees a bad version exists, so n is a real known-true endpoint; 0 is only a virtual good endpoint.

**Talking points:**

- Good versions followed by bad versions form a monotonic predicate.
- I'd keep version zero as a virtual good endpoint and `n` as a known bad endpoint, querying
  only interior versions.

**Solution:** Ask the API only about interior versions. A bad midpoint becomes hi; a good midpoint becomes lo. Adjacent endpoints isolate the first bad version without querying version 0.

**Complexity:**

**Time complexity:** `O(log(n + 1))` API calls; the same time bound assumes each call costs
`O(1)`.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [03-FirstBadVersion.java](./java/03-FirstBadVersion.java)

**Potential interviewer follow-ups:**

- What if no bad version is guaranteed? Use a virtual n+1 endpoint (long when necessary) and return a no-answer result if it remains there.
- What if the API is noisy? The deterministic monotonic model no longer directly applies; retries or a probabilistic error model need separate justification.

## 4. [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/search-2d-matrix/question?list=neetcode150)

**Source:** NeetCode 150

**Search space:** Flat indices 0…rows·cols−1.

**Template:** First true (minimization).
**Predicate:** matrix[i / cols][i % cols] >= target.
**Initial bounds:** lo = -1, hi = rows·cols (long).
**Return:** hi is in range and its mapped value equals target.

**Why monotonic:** The last value of each row is smaller than the first value of the next row, so row-major order is globally sorted.

**Talking points:**

- The row-major ordering lets me treat the matrix as one sorted array.
- I'd binary-search virtual indices and map each midpoint to a row and column without flattening
  the data.

**Solution:** Apply the lower-bound template to virtual flattened indices, translating each midpoint to row and column without allocating a flattened array. The implementation uses long for the total and flat bounds, then checks equality.

**Complexity:**

**Time complexity:** `O(log(RC + 1))` for `R` rows and `C` columns.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [04-SearchA2DMatrix.java](./java/04-SearchA2DMatrix.java)

**Potential interviewer follow-ups:**

- What if rows and columns are sorted but row-major order is not? This predicate is not monotonic in flat index; use staircase search or another appropriate method.
- Why promote before multiplication? Casting after rows*cols cannot repair an int overflow that already happened.

## 5. [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/eating-bananas/question?list=neetcode150)

**Source:** NeetCode 150

**Search space:** Integer speeds 1…M, M=max(piles).

**Template:** First true (minimization).
**Predicate:** sum(ceil(pile / speed)) <= h.
**Initial bounds:** lo = 0, hi = M.
**Return:** hi.

**Why monotonic:** Increasing speed cannot increase the required hours. The guarantee h >= piles.length makes M feasible.

**Talking points:**

- I'd search eating speeds and test each speed by summing the required hours.
- Higher speeds cannot require more hours, so binary search finds the smallest feasible speed.

**Solution:** Search speeds, not pile indices. Zero is an infeasible sentinel and is never passed to the checker. For a positive candidate, sum integer ceiling divisions using long and stop early when hours exceed h.

**Complexity:**

**Time complexity:** `O(n log(M + 1))` for `n` piles and maximum pile size `M`.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [05-KokoEatingBananas.java](./java/05-KokoEatingBananas.java)

**Potential interviewer follow-ups:**

- How do you compute the ceiling safely? (pile + (long)speed - 1) / speed avoids floating point and int addition overflow.
- Why is h < number of piles impossible under these eating rules? Each nonempty pile still takes at least one hour, regardless of speed.

## 6. [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/find-minimum-in-rotated-sorted-array/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Search space:** Indices 0…n−1.

**Template:** First true (minimization).
**Predicate:** nums[i] <= nums[n-1] using the fixed last value.
**Initial bounds:** lo = -1, hi = n-1.
**Return:** nums[hi], not hi.

**Why monotonic:** With distinct values, the high segment is greater than the last value and the low segment is at most it. An unrotated array gives all true, so the first true is index 0.

**Talking points:**

- I'd compare every candidate with the fixed final array value.
- With distinct values, the first value no greater than that reference starts the low segment
  and is the minimum.

**Solution:** Find the start of the low segment. Store the original last value once; do not change the predicate's reference to nums[hi] as hi moves. The final index is the pivot, whose value the judge requests.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [06-FindMinimumInRotatedSortedArray.java](./java/06-FindMinimumInRotatedSortedArray.java)

**Potential interviewer follow-ups:**

- Why must the final element be a fixed reference for this proof? It defines one boolean sequence independently of the evolving bounds.
- Why do duplicates require a different algorithm? For [1,0,1,1,1], the predicate is all true although the minimum is not first; duplicate-aware shrinking may degrade to O(n).

## 7. [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/find-target-in-rotated-sorted-array/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150

**Search space:** First pivot indices; then one sorted segment [start,endExclusive).

**Template:** First true (minimization).
**Predicate:** Pivot: nums[i] <= last. Segment: nums[i] >= target.
**Initial bounds:** Pivot: (-1,n-1). Segment: (start-1,endExclusive).
**Return:** Segment hi if inside that segment and equal; otherwise -1.

**Why monotonic:** The full rotated array is not monotonic for nums[i] >= target. Finding the pivot exposes two genuinely sorted segments, each suitable for lower bound.

**Talking points:**

- I'd first find the rotation pivot using the fixed last value.
- The target's relation to that last value selects a sorted segment, where the same lower-bound
  loop can check for a match.

**Solution:** First find the pivot using problem 6's predicate. If target <= the fixed last value, search [pivot,n); otherwise search [0,pivot). A segment can be empty, particularly when an unrotated array is queried above its largest element. Virtual endpoints and the final range check handle this safely. Two sequential logarithmic searches are still O(log n).

**Complexity:**

**Time complexity:** `O(log(n + 1))`; two sequential logarithmic searches keep the same bound.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [07-SearchInRotatedSortedArray.java](./java/07-SearchInRotatedSortedArray.java)

**Potential interviewer follow-ups:**

- Could you search a virtual sorted view instead? Yes: logical index i maps to (pivot+i)%n; use a lower bound on that view.
- What is traded against the original one-pass solution? This version repeats a logarithmic search but uses the same boundary invariant in both phases; asymptotic complexity is unchanged.

## 8. [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/time-based-key-value-store/question?list=neetcode150)

**Source:** NeetCode 150

**Search space:** Indices 0…m−1 in one key's chronological history.

**Template:** Last true (maximization).
**Predicate:** entries[i].timestamp <= queryTimestamp.
**Initial bounds:** lo = -1, hi = m.
**Return:** entries[lo].value if lo >= 0; otherwise empty string.

**Why monotonic:** Timestamps increase, so qualifying writes form a true prefix. The desired write is the last true, not the first true.

**Talking points:**

- I'd append each key's writes to a chronological list.
- A query searches for the last timestamp no greater than the requested time; no qualifying
  timestamp returns an empty string.

**Solution:** Keep append-only per-key lists. get uses the maximization variant: a qualifying midpoint becomes lo, otherwise it becomes hi. A missing key or a query earlier than all writes returns an empty string. The existing OutOfOrderTimeMap remains a separate TreeMap follow-up; it is not a hand-written binary search and is not forced into this template.

**Complexity:**

**Time complexity:** For `m` writes to a key: expected amortized `O(1)` per `set`, `O(log(m +
1))` per `get`. The TreeMap alternative uses `O(log(m + 1))` for both.

**Space complexity:** `O(W + C)` persistent storage for `W` entries and `C` stored string
characters; `O(1)` search state.

**Java:** [08-TimeBasedKeyValueStore.java](./java/08-TimeBasedKeyValueStore.java)

**Potential interviewer follow-ups:**

- Why does a list need ordered writes? An out-of-order append destroys the true-prefix predicate; TreeMap.floorEntry handles arbitrary insertion order.
- Is the TreeMap alternative more optimized? It supports a different requirement, but sacrifices constant-amortized appends and has larger node overhead.

## 9. [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/median-of-two-sorted-arrays/question?list=neetcode150)

**Source:** NeetCode 150

**Search space:** Cut count i in smaller array A: 0…m; j = H-i in B.

**Template:** First true (minimization).
**Predicate:** B-left <= A-right, with empty-side sentinels.
**Initial bounds:** lo = -1, hi = m.
**Return:** Use cut i=hi, j=H-i to calculate the median.

**Why monotonic:** Increasing i raises A-right while reducing j, which lowers B-left. Thus this one inequality is false then true. Testing both cross-inequalities together does not give that monotonic sequence.

**Talking points:**

- I'd binary-search the partition in the smaller array so both left halves contain half the
  values.
- The valid boundary makes every left value no greater than every right value, letting the
  median come from the partition edges.

**Solution:** Let A be the smaller array, m<=n, and H=(m+n+1)/2. Search for the first cut with B[j-1] <= A[i], interpreting an empty B-left as -infinity and an empty A-right as +infinity. hi=m is known true, and the loop never evaluates A[m]. At the boundary, minimality supplies the other required inequality. For odd length return the larger left boundary; for even length average it with the smaller right boundary, promoting before addition.

**Complexity:**

**Time complexity:** `O(1 + log(min(m, n) + 1))` for array lengths `m` and `n`.

**Space complexity:** `O(1)` auxiliary space, including at most one argument-swap call.

**Java:** [09-MedianOfTwoSortedArrays.java](./java/09-MedianOfTwoSortedArrays.java)

**Potential interviewer follow-ups:**

- Why not use 'both sides are valid' as the predicate? It can be false, then true, then false as i grows; see the example and proof below.
- How would you generalize to the kth smallest? Set H=k and restrict i to max(0,k-n)…min(k,m) so j remains valid, then apply the same one-sided boundary test.

## 10. [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) — Hard

**Source:** Supplemental

**Search space:** Maximum allowed part sum from max(nums) to sum(nums).

**Template:** First true (minimization).
**Predicate:** Greedy requiredParts(nums, limit) <= k.
**Initial bounds:** lo = max(nums)-1, hi = sum(nums), both long.
**Return:** hi (cast to int under the problem's bounds).

**Why monotonic:** Increasing the limit cannot increase the minimum number of required parts. For nonnegative inputs, greedily extending each part as far as possible uses the fewest parts.

**Talking points:**

- I'd search a candidate maximum subarray sum and greedily count how many parts it needs.
- Nonnegative values make feasibility monotonic, and a split into at most `k` parts can be
  refined to exactly `k`.

**Solution:** Search the smallest feasible maximum sum. The checker starts a new part only when the next value would exceed the candidate limit. At most k parts suffices: with nonnegative numbers, subdividing parts until exactly k are present cannot raise any part's sum, and k<=n ensures enough elements. The bounds also work for all-zero input: lo=-1 and hi=0 are already adjacent.

**Complexity:**

**Time complexity:** `O(n(1 + log(S - M + 1)))` for `S = sum(nums)`, `M = max(nums)`, including
the initial scan.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [10-SplitArrayLargestSum.java](./java/10-SplitArrayLargestSum.java)

**Potential interviewer follow-ups:**

- Why is the greedy checker optimal here? It makes each part's endpoint as far right as possible, so no competing valid partition can get ahead using fewer parts.
- What precisely fails with negative values? Feasibility as a function of the limit remains monotonic, but this greedy checker, max-element lower bound, and at-most-k to exactly-k argument are no longer valid.

## 11. [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) — Medium

**Source:** User screenshot

**Search space:** Array indices 0…n−1.

**Template:** Minimization for the first occurrence, maximization for the last.
**Predicate:** First: nums[i] >= target. Last: nums[i] <= target.
**Initial bounds:** lo = -1, hi = n in both searches; opposite virtual truths.
**Return:** [first, last], or [-1,-1] if the first boundary is not an exact match.

**Why monotonic:** The first predicate has a false prefix and true suffix;
the second has a true prefix and false suffix. Equality alone would not work.

**Talking points:**

- I'd search separately for the first value at least the target and the last value at most it.
- After verifying presence, those boundaries give the inclusive range without computing
  overflow-prone `target + 1`.

**Solution:** Find the lower bound and verify presence before searching for
the final index at most target. Both loops use lo+1<hi and assign mid directly
to an endpoint. Avoid target+1 so integer-limit targets remain safe.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [11-FindFirstAndLastPosition.java](./java/11-FindFirstAndLastPosition.java)

**Potential interviewer follow-ups:**

- How do you count occurrences? If present, last-first+1; otherwise zero.
- Could two minimization searches work? Yes: first >= target and first > target, subtracting one from the second boundary.

## 12. [Find Peak Element](https://leetcode.com/problems/find-peak-element/) — Medium

**Source:** User screenshot

**Search space:** Array indices 0…n−1, with conceptual negative-infinity neighbors.

**Template:** Any-peak search using minimization-style updates; no minimum-index objective.
**Condition:** nums[i] > nums[i+1] (a falling edge).
**Initial bounds:** lo = -1, hi = n-1.
**Return:** hi, which is a peak when the bounds become adjacent.

**Invariant:** The edge from lo to lo+1 rises, and the edge from hi to hi+1
falls. Both initially hold using the problem's negative-infinity neighbors.
A falling midpoint becomes hi; a rising midpoint becomes lo. Adjacent values
must differ, so these are the only cases. At termination, hi=lo+1 is greater
than both its neighbors. No out-of-range neighbor is actually read.

**Talking points:**

- I'd use the local rising or falling slope to keep an interval that contains a peak.
- The endpoint-slope invariant justifies discarding half; the goal is any peak, and the slope
  predicate need not be globally monotonic.

**Solution:** Keep the guide's loop and direct midpoint assignments, proving
correctness from the two endpoint slopes. The condition is not globally
monotonic: for [1,2,1,3,5,6,4], it changes false/true/false before becoming
true again. This implementation returns index 5, although index 1 is also a
peak. Calling it a first-peak minimization search would therefore be incorrect.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [12-FindPeakElement.java](./java/12-FindPeakElement.java)

**Potential interviewer follow-ups:**

- What if the first peak is required? The slope predicate is not monotonic; a general first-peak search can require a linear scan.
- What if adjacent values can be equal? The strict rising/falling invariant fails; clarify whether plateaus qualify before changing the algorithm.

## 13. [Arranging Coins](https://leetcode.com/problems/arranging-coins/) — Easy

**Source:** User screenshot

**Search space:** Complete-row counts 0…n.

**Template:** Last true (maximization).
**Predicate:** k*(k+1)/2 <= n.
**Initial bounds:** lo = 0 (feasible), hi = (long)n+1 (infeasible).
**Return:** lo.

**Why monotonic:** The coins required increase with the row count, so feasible
counts form a true prefix. Zero rows require zero coins; n+1 rows exceed n coins.

**Talking points:**

- I'd search the largest row count whose triangular number fits the coin budget.
- Using long arithmetic keeps the midpoint and `k(k + 1)/2` safe near integer limits.

**Solution:** Search the largest feasible row count. Use long bounds, midpoint,
and triangular-number arithmetic to avoid int overflow even when n is
Integer.MAX_VALUE. The implementation also handles n=0.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` coins.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [13-ArrangingCoins.java](./java/13-ArrangingCoins.java)

**Potential interviewer follow-ups:**

- Why not use a floating-point square-root formula directly? Rounding near a triangular-number boundary needs an integer correction.
- How would an unrestricted long input change the checker? Avoid overflow in k*(k+1), for example by dividing one factor by two and comparing through division.

## 14. [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) — Medium

**Source:** User screenshot

**Search space:** Array indices 0…n−1 in an odd-length sorted array.

**Template:** First true (minimization of the singleton index).
**Predicate:** i == n-1 || nums[i] != nums[i ^ 1].
**Initial bounds:** lo = -1 (virtual false), hi = n-1 (true endpoint).
**Return:** nums[hi].

**Why monotonic:** Before the singleton, equal pairs occupy (even, odd)
indices. From the singleton onward that alignment breaks, giving false then
true. The final index is defined true; the loop never evaluates its partner.

**Talking points:**

- Before the unique element, duplicate pairs align at even/odd positions; after it, that
  alignment breaks.
- I'd locate the first broken pair with binary search, using `mid ^ 1` to find the partner
  index.

**Solution:** XOR with 1 switches an even index to the following odd index and
an odd index to the previous even index. Find the first mismatch. Every tested
midpoint is below n-1, so both compared indices are valid.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [14-SingleElementInSortedArray.java](./java/14-SingleElementInSortedArray.java)

**Potential interviewer follow-ups:**

- What if the input is unsorted? XOR all values in O(n) time; the pairing predicate no longer has sorted alignment.
- Can you search pair numbers instead? Compare nums[2*p] and nums[2*p+1], with a virtual true endpoint for the missing final pair.

## 15. [Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) — Easy

**Source:** User screenshot

**Search space:** Indices 0…n−1 in a nonempty sorted character array.

**Template:** First true (minimization).
**Predicate:** letters[i] > target.
**Initial bounds:** lo = -1 (virtual false), hi = n (virtual true).
**Return:** letters[hi], wrapping to letters[0] when hi == n.

**Why monotonic:** Once a sorted letter is strictly greater than target, all
later letters also qualify. Duplicates preserve this ordering.

**Talking points:**

- I'd find the first letter strictly greater than the target.
- If that upper bound is the virtual endpoint `n`, I'd return the first letter for wraparound.

**Solution:** Find the strict upper bound. Equality stays on the false side;
if no real index qualifies, apply the required wraparound after the search.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` letters.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [15-FindSmallestLetterGreaterThanTarget.java](./java/15-FindSmallestLetterGreaterThanTarget.java)

**Potential interviewer follow-ups:**

- What changes for the smallest letter at least target? Use >= instead of >.
- Why search the first qualifying index? Sorted order makes its value the smallest qualifying letter, including with duplicates.

## 16. [Check If a Number Is Majority Element in a Sorted Array](https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/) — Easy

**Source:** User screenshot

**Access:** LeetCode Premium

**Search space:** Array indices 0…n−1.

**Template:** Minimization for the first occurrence, maximization for the last.
**Predicate:** First: nums[i] >= target. Last: nums[i] <= target.
**Initial bounds:** lo = -1, hi = n in both searches; opposite virtual truths.
**Return:** True only when target is present and last-first+1 > n/2.

**Why monotonic:** Sorted order makes both occurrence predicates monotonic,
as in problem 11. All occurrences of target form one contiguous block.

**Talking points:**

- I'd find the target's first and last occurrences in the sorted array.
- Their inclusive count must be strictly greater than half the length; exactly half is
  insufficient.

**Solution:** Verify presence at the lower bound, then find the last occurrence
with the maximization loop. Compare the inclusive count with half the array
length; exactly half is not a majority. The submission method returns boolean.

**Complexity:**

**Time complexity:** `O(log(n + 1))` for `n` values.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [16-CheckIfNumberIsMajorityElement.java](./java/16-CheckIfNumberIsMajorityElement.java)

**Potential interviewer follow-ups:**

- Could you use only one binary search? After finding the first occurrence, check whether first+n/2 is in range and still equals target.
- What changes for unsorted input? Count target in O(n); binary searching its occurrence boundaries is no longer valid.

## Median: why the one-sided test is enough

This is the least obvious adaptation, so derive it instead of memorizing it.

Let A be the shorter array, m<=n, H=(m+n+1)/2, and j=H-i. For every i in 0…m,
j stays in 0…n. Our search asks only whether B-left<=A-right. As i increases,
A-right does not decrease and B-left does not increase, so this is first-true.

At the smallest true i:

- If i=0, A-left is empty, so A-left<=B-right automatically.
- If i>0, the previous cut i-1 was false. At that cut, the compared values were
  B[j] and A[i-1], so false means B[j]>A[i-1]. Therefore the missing inequality
  A-left<=B-right also holds at i.

Both inequalities now hold, and exactly H elements are on the left.
A cut at i=m is a known-true endpoint because A-right is empty; it is never
dereferenced in the loop. Boundary values are reconstructed afterward using
Integer.MIN_VALUE/MAX_VALUE for missing sides.

For A=[3,4] and B=[1,2,5,6], H=3:

| i | j | B-left<=A-right (searched predicate) | Both cross-inequalities valid |
| ---: | ---: | --- | --- |
| 0 | 3 | false: 5<=3 | false |
| 1 | 2 | true: 2<=4 | true |
| 2 | 1 | true: 1<=+infinity | false: 4>2 |

The “both valid” column is false/true/false: it is not suitable for first-true
search. The one-sided column has the required monotonic shape.

## Common mistakes to avoid

- Moving bounds using a different loop convention halfway through the solution.
- Reading nums[n], nums[-1], or a selected segment's virtual endpoint.
- Using equality instead of a threshold predicate, then omitting the final match check.
- Comparing against a moving last-value reference in the fixed-predicate rotation proof.
- Assuming duplicate rotated arrays satisfy the distinct-value pivot argument.
- Claiming signed-array feasibility is nonmonotonic when the actual failure is the
  chosen greedy checker or search bounds.
- Treating a TreeMap follow-up as a faster implementation of the same append-only API.

## Local verification

From the workspace root:

```bash
bash Leetcode/tests/verify.sh 06-binary-search
ruby -EUTF-8 Leetcode/tests/check_catalog.rb
```

Tests cover examples, absent targets, sentinel boundaries, all small rotation
offsets, timestamp predecessor queries, median comparisons against sorted merges,
and split-array comparisons against exhaustive cuts. First Bad Version checks
API-call bounds and integer-limit versions. These are local tests, not a hidden-judge
acceptance claim.
