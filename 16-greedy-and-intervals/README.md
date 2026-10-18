# 16 — Greedy and Intervals

For all six questions from the Intervals category together, see [21 — Intervals](../21-intervals/README.md).

Greedy reasoning is a proof technique: commit to a local choice only after showing it cannot hurt the best possible completion. Interval problems often provide the ordering that makes such a proof possible. Earlier sorting exercises are not repeated here.

## How to recognize this pattern

**Keywords / hints:** “minimum number of…,” “maximum reachable,” “assign resources,” “non-overlapping intervals,” “cover a query,” or a choice that leaves the most flexibility for later.

**Structural test:** Can you exchange an optimal solution's first choice for your local choice without making it worse, or prove your solution stays at least as far ahead after every step?

**Invariant / mental model:** After each commitment, some optimal completion is still possible; alternatively, maintain the furthest reachable frontier or exactly the currently eligible intervals.

**When not to use it:** A minimum/maximum objective alone is not evidence for greedy. If locally attractive choices block better combinations and no exchange/stays-ahead proof works, investigate dynamic programming or search.

**Prerequisites:** 03 two pointers, 05 sorting/interval basics, and 15 heaps for Minimum Interval to Include Each Query. The final two are stretch exercises.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Assign Cookies](https://leetcode.com/problems/assign-cookies/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Match small demands with the smallest adequate resources to preserve larger resources for harder demands.

**Talking points:**

- I'd sort children by greed and cookies by size, then serve the least demanding unsatisfied
  child first.
- Using the smallest adequate cookie preserves larger cookies for later children, making the
  greedy choice safe.

**Solution:** Sort greed factors and cookie sizes, then scan cookies in ascending order. Give a cookie to the least demanding unsatisfied child whenever it is large enough. Using a larger cookie for that child cannot improve the remaining opportunities.

**Complexity:**

**Time complexity:** `O(n log(n + 1) + m log(m + 1))` for `n` children and `m` cookies.

**Space complexity:** `O(n + m)` auxiliary cloned-array and sorting storage.

**Java:** [AssignCookies](./java/01-AssignCookies.java) — Solution preserves both arrays; sorting them in place would remove the clone storage.

**Potential interviewer follow-ups:**

- Why not satisfy the greediest child first? A large cookie may be wasted on a child when several smaller demands could still be satisfied; the smallest-adequate exchange argument is simpler.
- What if cookies can be combined? The original one-cookie-per-child greedy proof no longer applies.

## 2. [Lemonade Change](https://leetcode.com/problems/lemonade-change/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Process customers in fixed order while preserving the small bills needed by future customers.

**Talking points:**

- I'd track only the available five- and ten-dollar bills.
- For a twenty-dollar payment I'd prefer a ten plus a five, preserving fives needed for future
  ten-dollar payments.

**Solution:** Track $5 and $10 counts. A $10 payment needs one five. For $20, prefer one ten plus one five; otherwise use three fives. A ten can only help with a twenty, while fives are also needed for tens, so preserving fives is safe.

**Complexity:**

**Time complexity:** `O(n)` for `n` payments.

**Space complexity:** `O(1)` auxiliary counters.

**Java:** [LemonadeChange](./java/02-LemonadeChange.java) — Solution assumes the allowed bill denominations 5, 10, and 20 and does not mutate input.

**Potential interviewer follow-ups:**

- Why prefer 10+5 over 5+5+5? It retains two extra fives, which can make change in every case the retained ten could and in more cases.
- Does this prove greedy change-making for arbitrary denominations? No; the argument depends on these denominations and the fixed $5 price.

## 3. [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Build a palindrome from a multiset of characters; positions in the original string are irrelevant.

**Talking points:**

- I'd use every available character pair to build the two sides of a palindrome.
- If any count is odd, one leftover character can fill the center.

**Solution:** Use every available pair of each character. If any character has an odd count, one leftover character can occupy the center. All pairs are independently useful, so there is no conflicting choice to optimize.

**Complexity:**

**Time complexity:** `O(n)` for string length `n`.

**Space complexity:** `O(1)` auxiliary counts for the fixed English-letter alphabet.

**Java:** [LongestPalindrome](./java/03-LongestPalindrome.java) — Solution uses a fixed ASCII frequency array; this is not longest palindromic substring or subsequence.

**Potential interviewer follow-ups:**

- What if the actual palindrome is required? Construct a half from count/2 copies of each character, choose a center, then mirror the half.
- What changes for arbitrary Unicode? Count code points in a map and define the intended text-normalization rules.

## 4. [Jump Game](https://leetcode.com/problems/jump-game/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/jump-game/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Reachability in an array where each reachable position extends a forward range.

**Talking points:**

- I'd track the furthest reachable index while scanning.
- An index beyond that frontier proves failure; a reachable index can extend the frontier with
  its jump length.

**Solution:** Maintain the furthest reachable index. If the next index lies beyond that frontier, it can never be reached. Otherwise extend the frontier with i+nums[i]. Since every intermediate forward jump length is allowed, all indices up to the frontier remain reachable.

**Complexity:**

**Time complexity:** `O(n)` for `n` positions.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [JumpGame](./java/04-JumpGame.java) — Solution leaves nums unchanged; the problem's bounds keep index sums within int.

**Potential interviewer follow-ups:**

- Why is one frontier sufficient instead of a visited array? Reachable indices form a contiguous prefix when jumps may use any length up to nums[i].
- What if jumps must be exactly nums[i]? Reachability need not be a contiguous prefix, so graph traversal or another state representation is needed.

## 5. [Jump Game II](https://leetcode.com/problems/jump-game-ii/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/jump-game-ii/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Minimum jumps with a reachable-prefix structure; each jump defines the next layer of reachable indices.

**Talking points:**

- I'd group reachable positions into layers representing the current jump count.
- Scanning a whole layer finds the next frontier, and reaching its boundary commits one
  additional jump.

**Solution:** Scan all positions reachable with the current number of jumps and record the furthest next position. When the scan reaches the current layer's end, commit one jump and advance the layer boundary. This compresses BFS levels without explicitly storing edges.

**Complexity:**

**Time complexity:** `O(n)` for `n` positions.

**Space complexity:** `O(1)` auxiliary frontier state.

**Java:** [JumpGameII](./java/05-JumpGameII.java) — Solution assumes LeetCode's guarantee that the last index is reachable.

**Potential interviewer follow-ups:**

- How would you handle unreachable inputs? Detect a layer boundary where farthest does not advance and report failure.
- Can you reconstruct one optimal jump sequence? Record which position extends each next frontier, with care to emit one predecessor per completed layer.

## 6. [Gas Station](https://leetcode.com/problems/gas-station/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/gas-station/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Choose a circular starting point; a negative running balance eliminates a whole block of candidate starts.

**Talking points:**

- I'd track both the total fuel surplus and the surplus from the current start candidate.
- A negative candidate surplus eliminates every start in that segment, so I'd restart after it;
  a nonnegative total makes the final candidate viable.

**Solution:** Accumulate the overall surplus and the surplus since the current candidate start. If the latter becomes negative at i, no station between the candidate and i can succeed: the candidate's earlier prefix balances were nonnegative, so starting later cannot repair this failing segment. Restart at i+1. A nonnegative total makes the final candidate feasible.

**Complexity:**

**Time complexity:** `O(n)` for `n` stations.

**Space complexity:** `O(1)` auxiliary space.

**Java:** [GasStation](./java/06-GasStation.java) — Solution uses long balances and leaves gas/cost unchanged.

**Potential interviewer follow-ups:**

- Why is total gas at least total cost necessary? A full circuit consumes every edge's cost regardless of the starting station.
- What if the tank has a capacity limit? Positive surplus may be discarded, invalidating the unlimited-tank reset proof.

## 7. [Partition Labels](https://leetcode.com/problems/partition-labels/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/partition-labels/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Split into as many contiguous pieces as possible while each character appears in only one piece.

**Talking points:**

- I'd record each character's final occurrence and extend the current piece's end as I scan.
- Reaching that end is the earliest safe cut, maximizing the number of pieces.

**Solution:** Record each character's last occurrence. While scanning a piece, extend its required end to the latest last occurrence of any character seen. Cut as soon as the current index reaches that end: no character in the piece appears later, and waiting longer could only reduce the number of pieces.

**Complexity:**

**Time complexity:** `O(n)` for string length `n`.

**Space complexity:** `O(1)` auxiliary last-occurrence state for 26 letters, plus output
partition lengths.

**Java:** [PartitionLabels](./java/07-PartitionLabels.java) — Solution preserves s and returns partition lengths.

**Potential interviewer follow-ups:**

- Why can't you cut earlier? At least one character already seen still has an occurrence before the current required end.
- What changes for arbitrary tokens instead of letters? Replace the fixed last-position array with a map.

## 8. [Insert Interval](https://leetcode.com/problems/insert-interval/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/insert-new-interval/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Insert one interval into an already sorted, disjoint interval list; only a contiguous block can overlap it.

**Talking points:**

- I'd copy ranges before the new interval, merge its contiguous overlap block, then copy the
  suffix.
- Already sorted disjoint intervals make the scan linear, and touching closed endpoints count as
  overlap.

**Solution:** Copy intervals ending before the new start. Merge every interval starting at or before the evolving new end. Emit that merged interval, then copy the untouched suffix. Closed endpoints that touch count as overlap.

**Complexity:**

**Time complexity:** `O(n)` for `n` existing intervals.

**Space complexity:** `O(1)` scan state, plus `O(n)` result/buffer storage.

**Java:** [InsertInterval](./java/08-InsertInterval.java) — Solution does not mutate either input and copies emitted intervals to avoid output/input aliasing.

**Potential interviewer follow-ups:**

- What if intervals are unsorted or already overlapping? Normalize with sorting and merging first, or use a more general interval structure.
- What if intervals are half-open [start,end)? Touching endpoints need not overlap; change the comparison rules consistently.

## 9. [Candy](https://leetcode.com/problems/candy/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Minimize assignments under local comparisons with both neighbors; a one-directional greedy pass is insufficient.

**Talking points:**

- I'd first enforce each child's constraint relative to the left neighbor.
- A reverse pass enforces the right constraint using the maximum of the two requirements, giving
  the smallest valid candy count.

**Solution:** Give everyone one candy. Scan left to right to enforce increasing ratings relative to the left neighbor. Scan right to left to enforce the right-neighbor constraint, taking the maximum with the amount already required by the left. Each child receives the minimum amount satisfying both directional lower bounds.

**Complexity:**

**Time complexity:** `O(n)` for `n` children.

**Space complexity:** `O(n)` auxiliary candy-array storage.

**Java:** [Candy](./java/09-Candy.java) — Solution preserves ratings. A constant-space slope-counting variant is a follow-up, not needed for the clear two-pass proof.

**Potential interviewer follow-ups:**

- Why must the reverse pass use max rather than overwrite? The amount established by the left neighbor may be larger than the right-neighbor requirement.
- Can space be reduced to O(1)? Count ascending and descending run lengths, add triangular sums, and count the shared peak only once using the larger required peak height.

## 10. [Minimum Interval to Include Each Query](https://leetcode.com/problems/minimum-interval-to-include-each-query/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/minimum-interval-including-query/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Offline point queries over intervals: as queries increase, intervals become eligible by start and expire by end.

**Talking points:**

- I'd process queries in increasing order and add intervals whose starts have been reached.
- A heap ordered by inclusive length provides the smallest covering interval after expired tops
  are removed; original query indices restore answer order.

**Solution:** Sort intervals by start and queries with their original indices. For each query, insert intervals whose starts are no greater than the query into a min-heap by length. Remove expired intervals while they reach the root; any expired intervals buried deeper cannot affect the current minimum. The surviving root is the shortest covering interval.

**Complexity:**

**Time complexity:** `O(n log(n + 1) + q log(q + 1))` for `n` intervals and `q` queries; each
interval is inserted and removed at most once.

**Space complexity:** `O(n + q)` auxiliary sorted-copy/index/heap storage, plus `O(q)` output.

**Java:** [MinimumIntervalToIncludeEachQuery](./java/10-MinimumIntervalToIncludeEachQuery.java) — Solution clones the interval outer array, never edits its rows, and preserves query order in the returned answers. Requires heaps from 15.

**Potential interviewer follow-ups:**

- Why can expired intervals remain below the heap root? They are irrelevant until they become the candidate minimum, at which point they are removed.
- What if queries must be answered online? Sorting the queries is unavailable; use an interval-search data structure or coordinate-compressed preprocessing for a known query domain.

