# Java Solutions — Prefix Sum with HashMap

Study in the pasted guide's order: counting, maximum length, then existence.
Each file is independent and contains its own `Solution` class and imports.
Compile or submit one problem at a time; do not compile all seven into one package.

1. [Subarray Sum Equals K](./01-SubarraySumEqualsK.java) — LC 560 · [LeetCode](https://leetcode.com/problems/subarray-sum-equals-k/)
2. [Subarray Sums Divisible by K](./02-SubarraySumsDivisibleByK.java) — LC 974 · [LeetCode](https://leetcode.com/problems/subarray-sums-divisible-by-k/)
3. [Binary Subarrays With Sum](./03-BinarySubarraysWithSum.java) — LC 930 · [LeetCode](https://leetcode.com/problems/binary-subarrays-with-sum/)
4. [Contiguous Array](./04-ContiguousArray.java) — LC 525 · [LeetCode](https://leetcode.com/problems/contiguous-array/)
5. [Maximum Size Subarray Sum Equals k](./05-MaximumSizeSubarraySumEqualsK.java) — LC 325 · [LeetCode](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/)
6. [Minimum Operations to Reduce X to Zero](./06-MinimumOperationsToReduceXToZero.java) — LC 1658 · [LeetCode](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/)
7. [Continuous Subarray Sum](./07-ContinuousSubarraySum.java) — LC 523 · [LeetCode](https://leetcode.com/problems/continuous-subarray-sum/)

The first three files use your supplied LC 560/930 solutions and revised LC 974
solution, including your comments and `containsKey`/`get` checks for 560/930.
LC 325 uses your earliest-index helper. LC 525 (Contiguous Array) uses your
zero-to-minus-one conversion followed by the same helper with target zero;
this version modifies the input array.
The prefix sums use `long` in 525, 325, 1658, and 523 to avoid overflow in accumulation.
The other implementations use `int`, sufficient for their problem constraints.

[Templates and explanations](../README.md) · [Full roadmap](../../README.md)
