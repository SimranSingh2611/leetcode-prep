/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd insert numbers into a binary trie from their highest bit downward.
 * - For each query bit I'd prefer the opposite branch, since setting a higher XOR bit outweighs
 *   every lower-bit choice.
 * Time complexity: O(31n) = O(n) for n nonnegative fixed-width integers.
 * Space complexity: O(31n) = O(n) auxiliary trie space.
 */

// 421. Maximum XOR of Two Numbers in an Array
// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
// Solution is a bitwise-trie extension, not a string trie; inputs are nonnegative and unchanged.
import java.util.*;

class Solution {
    private static class BitNode {
        BitNode[] child = new BitNode[2];
    }

    public int findMaximumXOR(int[] nums) {
        BitNode root = new BitNode();
        for (int x : nums) {
            BitNode node = root;
            for (int b = 30; b >= 0; b--) {
                int bit = (x >>> b) & 1;
                if (node.child[bit] == null) {
                    node.child[bit] = new BitNode();
                }
                node = node.child[bit];
            }
        }
        int best = 0;
        for (int x : nums) {
            BitNode node = root;
            int value = 0;
            for (int b = 30; b >= 0; b--) {
                int bit = (x >>> b) & 1;
                if (node.child[bit ^ 1] != null) {
                    value |= 1 << b;
                    node = node.child[bit ^ 1];
                } else {
                    node = node.child[bit];
                }
            }
            best = Math.max(best, value);
        }
        return best;
    }
}
