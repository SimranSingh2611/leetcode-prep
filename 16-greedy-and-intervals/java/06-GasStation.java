/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track both the total fuel surplus and the surplus from the current start candidate.
 * - A negative candidate surplus eliminates every start in that segment, so I'd restart after
 *   it; a nonnegative total makes the final candidate viable.
 * Time complexity: O(n) for n stations.
 * Space complexity: O(1) auxiliary space.
 */

// 134. Gas Station
// https://leetcode.com/problems/gas-station/
// Solution uses long balances and leaves gas/cost unchanged.
import java.util.*;

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        long total = 0, tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            long delta = (long) gas[i] - cost[i];
            total += delta;
            tank += delta;
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return total < 0 ? -1 : start;
    }
}
