# 02a — Prefix Sum with HashMap

Seven problems from your pasted **Prefix Sum + HashMap Patterns** guide, ordered
as counting → maximum length → existence. This focused practice set follows
[02 — Prefix Sums](../02-prefix-sums/README.md).

## Template first: count subarrays with sum k

This is your LC 560 template. LC 930 reuses it without changing the algorithm.

```java
Map<Integer, Integer> prefixSumFreqMap = new HashMap<>();
prefixSumFreqMap.put(0, 1);

int n = nums.length;
int prefixSum = 0;
int ctr = 0;

for (int i = 0; i < n; i++) {
    prefixSum += nums[i];

    // Count earlier prefixes that complete a valid subarray ending here.
    if (prefixSumFreqMap.containsKey(prefixSum - k)) {
        ctr += prefixSumFreqMap.get(prefixSum - k);
    }

    // Record this prefix for future iterations.
    prefixSumFreqMap.put(
        prefixSum, prefixSumFreqMap.getOrDefault(prefixSum, 0) + 1
    );
}

return ctr;
```

The invariant: before recording the current prefix, the map describes only
earlier prefixes, including the empty prefix. Query before inserting so that
the current prefix cannot form an empty subarray with itself.

## Why it works

Let `P[i]` be the sum through index `i`, with the empty prefix `P[-1] = 0`.
For an earlier index `j`, the subarray starts at **j + 1** and ends at **i**:

```text
sum(nums[j + 1 ... i]) = P[i] - P[j]

For a target sum k:
P[i] - P[j] = k
       P[j] = P[i] - k
```

The question at each index is: **which earlier prefix would make a valid
subarray ending here?** The map retrieves its frequency or earliest index.

For `nums = [1, -1, 1]`, `k = 1`:

| Index | Value | Current prefix | Needed prefix | Earlier matches | Running count |
| --- | ---: | ---: | ---: | ---: | ---: |
| 0 | 1 | 1 | 0 | 1 | 1 |
| 1 | -1 | 0 | -1 | 0 | 1 |
| 2 | 1 | 1 | 0 | 2 | 3 |

At index 2, the empty prefix and the prefix ending at index 1 both have sum 0.
They produce two different subarrays: the entire array and the final `[1]`.

## How the template changes

Choose the state from the condition, and the map value from the requested answer.

| Requested answer | Map value | Initial entry | On a match | Record current state |
| --- | --- | --- | --- | --- |
| Count | Frequency | `0 → 1` | Add frequency | Increment frequency |
| Maximum length | Earliest index | `0 → -1` | Maximize `i - earlierIndex` | `putIfAbsent` |
| Existence with minimum length | Earliest index | `0 → -1` | Check the index gap | `putIfAbsent` |

The empty prefix exists once, so its **frequency is 1**. It ends before index 0,
so its **index is -1**. For a match with that index, the length is `i - (-1) = i + 1`.

### Maximum-length template

Keep the same `prefixSum - k` lookup, but replace frequencies with earliest indices.
Use `long` for prefix sums when accumulation can exceed the `int` range.

```java
Map<Long, Integer> firstIndexMap = new HashMap<>();
firstIndexMap.put(0L, -1);

long prefixSum = 0;
int longest = 0;

for (int i = 0; i < nums.length; i++) {
    prefixSum += nums[i];

    Integer earlierIndex = firstIndexMap.get(prefixSum - k);
    if (earlierIndex != null) {
        longest = Math.max(longest, i - earlierIndex);
    }

    firstIndexMap.putIfAbsent(prefixSum, i);
}

return longest;
```

Do not overwrite the earliest index: at current index 8, a matching prefix at
index 2 gives length 6, while the same prefix at index 5 gives only length 3.

### Existence template

For LC 523, use a remainder as the state and return as soon as the index gap
meets the minimum length:

```java
Integer earlierIndex = firstIndexMap.get(remainder);
if (earlierIndex != null && i - earlierIndex >= 2) {
    return true;
}
firstIndexMap.putIfAbsent(remainder, i);
```

Keeping the earliest index maximizes the gap. With no index or length constraint,
some existence problems only need a set of earlier states.

## Recognizing the pattern

**Keywords / hints:** Count subarrays, longest subarray, equal counts, exact sum,
or sum divisible by k.

**Structural test:** Can the condition be expressed as a relation between two
prefix states? Exact sums use subtraction; divisibility uses equal remainders;
equal zero/one counts use equal balances.

**Invariant:** At each lookup, the map contains earlier states. Count problems
store multiplicities; length problems preserve first occurrences.

**Prerequisites:** Running sums and basic HashMap operations from
[01 — Hash Maps and Sets](../01-hash-maps-and-sets/README.md).

**When not to use it:** An equality lookup alone does not answer inequalities
such as shortest sum at least k with negative values; those need additional
structure. Frequent updates also require more than a static prefix array.

An ordinary sum-based sliding window relies on predictable changes when a window
expands or shrinks. Negative numbers break that property for exact-sum problems.
Nonnegative input can support sliding windows, but divisibility still does not
provide a simple rule for which boundary to move.

## Problem map

| Order | Problem | State | Lookup | Map value |
| --- | --- | --- | --- | --- |
| 1 | 560 — Subarray Sum Equals K | Sum | `prefixSum - k` | Frequency |
| 2 | 974 — Subarray Sums Divisible by K | Normalized remainder | Same remainder | Frequency |
| 3 | 930 — Binary Subarrays With Sum | Sum | `prefixSum - goal` | Frequency |
| 4 | 525 — Contiguous Array | Ones minus zeros | Same balance | Earliest index |
| 5 | 325 — Maximum Size Subarray Sum Equals k | Sum | `prefixSum - k` | Earliest index |
| 6 | 1658 — Minimum Operations to Reduce X to Zero | Sum | `prefixSum - (total - x)` | Earliest index |
| 7 | 523 — Continuous Subarray Sum | Remainder | Same remainder | Earliest index |

[Java solution index](./java/README.md)

## 1. [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) — LC 560

**Talking points:**

- Count earlier prefixes equal to `prefixSum - k`.
- Seed zero once, and query before updating the frequency.

**Solution:** Apply the counting template directly. Multiple earlier occurrences
of the needed prefix mean multiple valid subarrays ending at the current index.
Negative values require no special handling because this lookup uses exact sums.

**Template change:** None; this is the base counting implementation.

**Time complexity:** `O(n)` expected.

**Space complexity:** `O(n)`.

**Java:** [01-SubarraySumEqualsK.java](./java/01-SubarraySumEqualsK.java)

**Potential interviewer follow-ups:**

- Why must the current prefix be recorded after the lookup when k is zero?
- How would you return one matching subarray's indices?

## 2. [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) — LC 974

**Talking points:**

- Two prefixes with the same remainder differ by a multiple of k.
- Normalize negative remainders without changing the actual prefix sum.

**Solution:** Count frequencies of remainders instead of sums. If the current
prefix is 22 and an earlier prefix is 7, both have remainder 2 modulo 5;
their difference, 15, is divisible by 5.

**Template change:** Replace the `prefixSum - k` lookup with the current normalized
remainder. Keep `{0: 1}`, query first, and increment the frequency afterward.

```java
int div = prefixSum % k;
if (div < 0) {
    div += k;
}
ctr += prefixSumModKFreqMap.getOrDefault(div, 0);
```

Java gives `-1 % 5 == -1` and `4 % 5 == 4`. Normalization maps both to 4,
so mathematically equivalent remainder classes share one key. This assumes
positive k, as in this problem.

Seeding `{0: 1}` already counts valid subarrays starting at index 0. Do not also
increment the answer separately when the remainder is zero.

**Time complexity:** `O(n)` expected.

**Space complexity:** `O(min(n + 1, k))`.

**Java:** [02-SubarraySumsDivisibleByK.java](./java/02-SubarraySumsDivisibleByK.java)

**Potential interviewer follow-ups:**

- Why is a remainder-frequency array another option for positive k?
- How would the map change if the question asked for the longest divisible subarray?

## 3. [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/) — LC 930

**Talking points:**

- This is LC 560 restricted to binary input.
- Zeros create repeated prefix sums, so frequencies still matter.

**Solution:** Reuse `subarraySum(nums, goal)`. Binary values do not change the
prefix-difference equation, including when the goal is zero.

**Template change:** Only rename the target to `goal`; the implementation is the same.

**Time complexity:** `O(n)` expected.

**Space complexity:** `O(n)`.

**Java:** [03-BinarySubarraysWithSum.java](./java/03-BinarySubarraysWithSum.java)

**Potential interviewer follow-ups:**

- How does `[0, 0, 0]` produce six subarrays when goal is zero?
- How does `atMost(goal) - atMost(goal - 1)` give a sliding-window alternative?

## 4. [Contiguous Array](https://leetcode.com/problems/contiguous-array/) — LC 525

**Talking points:**

- Convert zero to -1 and one to +1, so equal counts give sum zero.
- A repeated balance identifies a valid range; its earliest occurrence gives the longest.

**Solution:** Use your two-step implementation: first replace every zero in
`nums` with -1, then call `maxSubArrayLen(nums, 0)` using your LC 325 helper.
The transformed prefix sum is `ones - zeros`. Equal prefix sums identify a
zero-sum range, which corresponds to equal counts in the original binary array.
This implementation modifies the input array.

**Template change:** Convert the input and set the helper's target to zero;
the maximum-length helper itself is unchanged.

```java
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == 0) {
        nums[i] = -1;
    }
}
return maxSubArrayLen(nums, 0);
```

**Time complexity:** `O(n)` expected.

**Space complexity:** `O(n)`.

**Java:** [04-ContiguousArray.java](./java/04-ContiguousArray.java)

**Potential interviewer follow-ups:**

- Why does keeping the latest balance index lose the longest answer?
- How would you count all balanced subarrays instead of finding the longest?

## 5. [Maximum Size Subarray Sum Equals k](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/) — LC 325

**Talking points:**

- Keep LC 560's lookup, but replace frequencies with earliest indices.
- Seed zero at -1 so ranges starting at index zero use the same length formula.

**Solution:** For each prefix, look up `prefixSum - k`. If it appeared at index j,
the matching subarray has length `i - j`. Maximize this length and preserve each
prefix's first occurrence. Return zero if there is no match.

**Template change:** Change `{0: 1}` to `{0: -1}`, maximize the length instead
of accumulating counts, and use `putIfAbsent` instead of frequency increments.

**Time complexity:** `O(n)` expected.

**Space complexity:** `O(n)`.

**Java:** [05-MaximumSizeSubarraySumEqualsK.java](./java/05-MaximumSizeSubarraySumEqualsK.java)

**Dry run of your prefix sequence:** For `nums = [1, -1, 5, -2]` and `k = 3`,
the prefix sums are `1 → 0 → 5 → 3`. The map starts with `{0: -1}`.

| Index | Prefix sum | Needed prefix | Earlier index | Longest so far | Map after recording |
| --- | ---: | ---: | --- | ---: | --- |
| 0 | 1 | -2 | Absent | 0 | `{0: -1, 1: 0}` |
| 1 | 0 | -3 | Absent | 0 | `{0: -1, 1: 0}` |
| 2 | 5 | 2 | Absent | 0 | `{0: -1, 1: 0, 5: 2}` |
| 3 | 3 | 0 | -1 | 4 | `{0: -1, 1: 0, 5: 2, 3: 3}` |

At index 1, `putIfAbsent(0L, 1)` preserves the existing index -1. At index 3,
the length is `3 - (-1) = 4`, covering the whole array. Storing `0 → 1` instead
would lose that earliest prefix and yield only length 2 at the last index.

**Potential interviewer follow-ups:**

- How would you also return the start and end indices of the longest match?
- What changes if the shortest nonempty exact-sum subarray is requested instead?

## 6. [Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/) — LC 1658

**Talking points:**

- Removing the left and right ends leaves one contiguous middle.
- Minimize removals by maximizing the kept middle with sum `total - x`.

**Solution:** Reduce the problem to LC 325:

```text
[ removed left | kept middle | removed right ]

removed sum = x
kept sum = total - x
minimum operations = n - longest kept length
```

For `[1, 1, 4, 2, 3]` and x = 5, keep `[1, 1, 4]` with sum 6;
removing the final two elements takes two operations.

**Template change:** Set the target to `total - x`, run the maximum-length
template, and return `n - longest`. Because values are strictly positive:

- Target below zero means impossible: return -1.
- Target zero means keep the empty middle: return n.
- Positive target with no matching middle means impossible: return -1.

The helper is included in this problem's Java file, so the solution is standalone.

**Time complexity:** `O(n)` expected, including the total-sum pass.

**Space complexity:** `O(n)`.

**Java:** [06-MinimumOperationsToReduceXToZero.java](./java/06-MinimumOperationsToReduceXToZero.java)

**Potential interviewer follow-ups:**

- Why can the positive-input version use an O(1)-space sliding window?
- Which special cases need reconsideration if zero or negative values are allowed?

## 7. [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) — LC 523

**Talking points:**

- Use equal prefix remainders, as in LC 974.
- Keep indices instead of frequencies so you can check length at least two.

**Solution:** Map each remainder to its earliest index, initially `{0: -1}`.
A repeated remainder forms a sum divisible by k; accept it only if the index
gap is at least two. Return false after the scan if none qualifies.

**Template change:** Starting from 974, replace frequencies with earliest indices,
replace the answer increment with `return true` when `i - earlierIndex >= 2`,
and record with `putIfAbsent`. Unlike 974, this problem's nonnegative values
and positive k need no negative-remainder normalization.

**Time complexity:** `O(n)` expected.

**Space complexity:** `O(min(n + 1, k))`.

**Java:** [07-ContinuousSubarraySum.java](./java/07-ContinuousSubarraySum.java)

**Potential interviewer follow-ups:**

- Why does overwriting an early remainder index risk missing a valid length?
- How would you extend the method to negative values or k = 0?

[Full roadmap](../README.md) · [General prefix-sum problems](../02-prefix-sums/README.md)
