# 15 — Heaps and Priority Queues

Use a heap when the next useful item is always the smallest/largest among changing candidates. The heap exposes one extreme; it does not keep its entire iteration order sorted.

## How to recognize this pattern

**Keywords / hints:** “kth largest,” “top k in a stream,” “repeatedly take the smallest,” “merge sorted streams,” “running median,” or “next available/highest-priority task.”

**Structural test:** After consuming or adding one item, do you need the best candidate again without sorting the whole collection? Can the candidate frontier or retained top-k set remain small?

**Invariant / mental model:** The heap root is the best currently eligible candidate. Define exactly which items belong in the heap and when they enter or leave.

**When not to use it:** Sorting is often simpler for a single static ordering; quickselect is useful for one static rank; a monotonic deque is better for ordered sliding-window extrema.

**Prerequisites:** 05 sorting and comparators; 08 stacks/queues; 10 linked-list merging helps with Twitter. Java PriorityQueue is a min-heap by default.

Keywords suggest a candidate technique; the structural test and constraints decide whether it actually works.

## Practice progression

Solve in numbered order. 3 Easy / 5 Medium / 2 Hard. The mix reflects genuine pattern fit rather than a forced quota. Hard exercises are stretch work; revisit them after the required supporting topics.

[Java solution index](./java/README.md) · [Full roadmap](../README.md) · [Recognition guide](../PATTERN_RECOGNITION.md)

## 1. [Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/kth-largest-integer-in-a-stream/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Repeated insertions followed by the kth-largest query; only the k largest values matter.

**Talking points:**

- I'd keep only the largest `k` values seen so far in a min-heap.
- After an insertion, removing any excess smallest value leaves the kth largest at the root.

**Solution:** Retain at most k elements in a min-heap. After insertion, remove the smallest if size exceeds k. Once at least k values have been seen, the root is exactly the kth largest, including duplicates.

**Complexity:**

**Time complexity:** Construction: `O(n log(k + 1))` for `n` initial values; each `add`:
`O(log(k + 1))`.

**Space complexity:** `O(k)` persistent heap storage.

**Java:** [KthLargestInAStream](./java/01-KthLargestInAStream.java) — KthLargest is the judge's required design class; the input array is unchanged.

**Potential interviewer follow-ups:**

- What if k changes during the stream? Discarded values may become relevant; retain more history or use an order-statistics structure.
- Why a min-heap for a largest-elements problem? It exposes the smallest retained candidate, which is the next one to evict.

## 2. [Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) — Easy

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/last-stone-weight/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Repeatedly remove the two current largest stones, then possibly insert a replacement.

**Talking points:**

- I'd repeatedly remove the two heaviest stones from a max-heap.
- Equal weights vanish; otherwise their difference returns to the heap for the next round.

**Solution:** Use a max-heap. Poll the heaviest two stones; if their weights differ, push the difference back. Continue until at most one remains.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` stones.

**Space complexity:** `O(n)` auxiliary heap space.

**Java:** [LastStoneWeight](./java/02-LastStoneWeight.java) — Solution uses reverseOrder rather than subtraction in its comparator and preserves stones.

**Potential interviewer follow-ups:**

- Can bounded weights help? A frequency array can replace the heap when the maximum weight is small.
- Why not just sort once? Smashing inserts new weights that can change the remaining order.

## 3. [Relative Ranks](https://leetcode.com/problems/relative-ranks/) — Easy

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Assign descending ranks while preserving each athlete's original index: a simple heap warm-up.

**Talking points:**

- I'd heap athlete indices by score and extract them in descending order.
- Writing each medal or rank at its original index preserves the requested answer order.

**Solution:** Put athlete indices into a max-heap ordered by score. Pop them in rank order and write the medal/rank into the original index. A sorting solution is equally appropriate here; the heap is for learning repeated extraction.

**Complexity:**

**Time complexity:** `O(n log(n + 1))` for `n` athletes.

**Space complexity:** `O(n)` auxiliary heap; `O(n)` result references plus rank-label
characters.

**Java:** [RelativeRanks](./java/03-RelativeRanks.java) — Solution leaves scores unchanged. This is a warm-up, not a case where a heap asymptotically beats sorting.

**Potential interviewer follow-ups:**

- Would a heap be necessary for all ranks offline? No; sorting indexed scores is often simpler.
- What if only the top three matter? Keep a size-three min-heap, reducing retained ranking state to O(1).

## 4. [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/kth-largest-element-in-an-array/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Find one rank rather than a full ordering; contrast a top-k heap with selection.

**Talking points:**

- I'd retain the largest `k` values in a min-heap, whose root is the required rank.
- Quickselect is a follow-up that partitions around index `n - k` and avoids maintaining an
  ordered candidate set.

**Solution:** The main solution retains the k largest values in a min-heap. Quickselect instead partitions in place around a randomized pivot and continues only into the side containing sorted index n-k. A three-way partition groups duplicate values, avoiding repeated work on an all-equal array.

**Complexity:**

**Time complexity:** Primary heap: `O(n log(k + 1))`; randomized quickselect: expected `O(n)`,
worst-case `O(n^2)`.

**Space complexity:** Primary: `O(k)` auxiliary heap; iterative quickselect: `O(1)` auxiliary
space and mutates the input.

**Java:** [KthLargestElementInAnArray](./java/04-KthLargestElementInAnArray.java) — Solution preserves nums. QuickselectSolution is the expected-time and auxiliary-space improvement, with a quadratic worst-case caveat.

**Potential interviewer follow-ups:**

- Does kth largest mean kth distinct value? No; duplicates occupy ranks unless the specification says distinct.
- When would the heap be preferable? Streaming input, immutable data, or predictable O(n log k) worst-case time may matter more than quickselect's expected linear time.

## 5. [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Each element of the first sorted array defines a sorted stream of pair sums with the second array.

**Talking points:**

- I'd treat each first-array value as the start of a sorted row of pair sums.
- A min-heap merges those rows by emitting a pair and advancing only its row; only the first
  `min(k, n)` rows need seeds.

**Solution:** Seed a min-heap with (i,0) for the first min(k,n) rows. Pop the smallest pair, output it, and push (i,j+1) from that same row. Later rows need not be seeded: their first values cannot outrank all first k seeded row heads.

**Complexity:**

**Time complexity:** `O((h + p) log(h + 1))`, where `h = min(k, n)`, `p = min(k, nm)`, and array
lengths are `n`, `m`.

**Space complexity:** `O(h)` auxiliary heap, plus `O(p)` output pairs.

**Java:** [FindKPairsWithSmallestSums](./java/05-FindKPairsWithSmallestSums.java) — Solution uses long pair sums in comparisons and leaves both arrays unchanged.

**Potential interviewer follow-ups:**

- Why is no visited set needed? Each pair has only one generation path: the preceding column in its row.
- What if the arrays are unsorted? Sort copies first or use another selection strategy; the stream-order proof depends on sorted inputs.

## 6. [Task Scheduler](https://leetcode.com/problems/task-scheduler/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/task-scheduling/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Repeatedly schedule the most frequent currently eligible task under a cooldown constraint.

**Talking points:**

- I'd schedule blocks of cooldown plus one slots, selecting distinct tasks with the largest
  remaining counts.
- Unfinished tasks return only after the block; the final block counts actual tasks rather than
  trailing idle slots.

**Solution:** Process blocks of n+1 slots. Take up to n+1 different tasks with the highest remaining counts, decrement them, then reinsert unfinished tasks only after the block. A non-final block costs n+1 slots; the final block costs its actual task count. The optimized formula uses the maximum frequency f and the number m of tasks tied at f: max(total, (f-1)(n+1)+m).

**Complexity:**

**Time complexity:** `O(N log(U + 1))` for `N` tasks and `U` distinct types; the formula
alternative takes `O(N + U)`. Both are `O(N)` when `U <= 26`.

**Space complexity:** `O(U)` auxiliary heap/counts/pending storage; `O(1)` under the fixed
26-letter alphabet.

**Java:** [TaskScheduler](./java/06-TaskScheduler.java) — Solution demonstrates heap scheduling. FormulaSolution is a simpler counting follow-up, not an asymptotic improvement under a fixed 26-letter alphabet.

**Potential interviewer follow-ups:**

- Why take max with the total number of tasks? Enough other tasks fill all cooldown gaps, so no idle slots remain.
- Would the formula survive task-specific cooldowns? No; the uniform-cooldown frame argument no longer applies.

## 7. [Design Twitter](https://leetcode.com/problems/design-twitter/) — Medium

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/design-twitter-feed/question?list=neetcode150)

**Source:** NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Merge a small prefix of multiple already time-ordered user feeds, rather than sorting every historical tweet.

**Talking points:**

- I'd store each user's tweets newest first and merge those lists when building a feed.
- A heap contains one current tweet per candidate user, and each extraction advances only that
  user's list.

**Solution:** Store each user's tweets as a newest-first linked list. For a news-feed query, put the head of the user's list and each followed user's list into a max-heap by timestamp. Pop up to ten tweets and advance only the list just consumed. Follow relationships use sets.

**Complexity:**

**Time complexity:** Expected `O(1)` for `postTweet`, `follow`, `unfollow`; `getNewsFeed` takes
`O((F + 10) log(F + 2))` for `F` candidate users.

**Space complexity:** `O(T + R + U)` persistent storage for tweets `T`, follow relationships
`R`, and stored user records `U`; `O(F)` feed workspace plus at most ten answers.

**Java:** [DesignTwitter](./java/07-DesignTwitter.java) — Twitter is the design class. Self-follow is ignored to avoid duplicate feed entries; historical tweets are retained.

**Potential interviewer follow-ups:**

- Can retained tweets be bounded for this exact API? Only the newest ten tweets per user can ever appear in a top-ten feed; older ones are always dominated by that user's newer ten.
- How would pagination change the design? Add a cursor and retain enough history; the top-ten-only storage argument no longer suffices.

## 8. [Reorganize String](https://leetcode.com/problems/reorganize-string/) — Medium

**Source:** Supplemental · **Practice role:** Additional pattern practice

**Recognize it:** Choose the most frequent remaining character without repeating the immediately previous character.

**Talking points:**

- I'd choose the most frequent available character from a max-heap.
- Holding the previous character outside the heap for one step prevents adjacent duplicates;
  leftover held copies with no alternative mean failure.

**Solution:** Keep remaining character counts in a max-heap. After using a character, hold it outside the heap for one iteration. Choose the next character, then reinsert the previous one if it still has copies. If a held character remains when the heap empties, no valid arrangement exists.

**Complexity:**

**Time complexity:** `O(n log(U + 1))` for length `n` and `U` distinct characters; `O(n)` with
`U <= 26`.

**Space complexity:** `O(U)` auxiliary heap/counts, plus `O(n)` output buffer; auxiliary space
is `O(1)` for 26 letters.

**Java:** [ReorganizeString](./java/08-ReorganizeString.java) — Solution returns any valid arrangement or an empty string. Heap entries are mutated only after removal.

**Potential interviewer follow-ups:**

- What is the impossibility condition? The maximum count exceeds ceil(n/2), so other characters cannot separate all its copies.
- Can fixed-alphabet counting avoid the heap? Place the most frequent letter at even indices, then fill the remaining positions, after checking feasibility.

## 9. [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) — Hard

**NeetCode 150:** [Practice problem](https://neetcode.io/problems/find-median-in-a-data-stream/question?list=neetcode150)

**Source:** Blind 75 + NeetCode 150 · **Practice role:** Core list selection

**Recognize it:** Maintain the middle boundary while values arrive in arbitrary order.

**Talking points:**

- I'd split the stream between a max-heap for the lower half and a min-heap for the upper half.
- Rebalancing keeps their sizes equal or leaves one extra lower value, so their roots determine
  the median.

**Solution:** Use a max-heap for the lower half and a min-heap for the upper half. Insert into the appropriate half, then rebalance so the lower half has either equal size or one extra element. The median is the lower root or the mean of both roots.

**Complexity:**

**Time complexity:** Each `addNum`: `O(log(n + 1))`; `findMedian`: `O(1)` for `n` stored values.

**Space complexity:** `O(n)` persistent heap storage.

**Java:** [FindMedianFromDataStream](./java/09-FindMedianFromDataStream.java) — MedianFinder is the required design class. The average promotes to long before addition to prevent int overflow.

**Potential interviewer follow-ups:**

- What if values are bounded to a tiny range? Frequency counts can trade heap operations for scanning that bounded range.
- What if old values expire from a sliding window? Heaps need deletion support, commonly lazy deletion plus logical-size tracking, or balanced multisets.

## 10. [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) — Hard

**Source:** Supplemental · **Practice role:** Advanced stretch

**Recognize it:** Maintain one candidate from each sorted list; improve the covered range by advancing its minimum endpoint.

**Talking points:**

- I'd keep one current element per list in a min-heap and track their maximum.
- The current minimum and maximum define a covering range; advancing the minimum's list is the
  only move that can raise the lower endpoint.

**Solution:** A min-heap holds one element per list and a separate variable tracks their maximum. Evaluate [minimum, maximum], then advance the list that supplied the minimum. Advancing any other list cannot raise the lower endpoint and may only raise the upper endpoint. Stop when one list is exhausted.

**Complexity:**

**Time complexity:** `O(N log(k + 1))` for `N` total elements across `k` lists.

**Space complexity:** `O(k)` auxiliary heap space.

**Java:** [SmallestRangeCoveringElementsFromKLists](./java/10-SmallestRangeCoveringElementsFromKLists.java) — Solution compares widths as long and breaks equal-width ties by smaller left endpoint; lists are unchanged.

**Potential interviewer follow-ups:**

- Why stop when a list is exhausted? Every future candidate must still cover that list, but no later value from it can replace the current minimum.
- Is there a sliding-window formulation? Sort all (value,listId) pairs and find the smallest window covering all list IDs, using more memory and a global sort.

