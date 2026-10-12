/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track only the available five- and ten-dollar bills.
 * - For a twenty-dollar payment I'd prefer a ten plus a five, preserving fives needed for
 *   future ten-dollar payments.
 * Time complexity: O(n) for n payments.
 * Space complexity: O(1) auxiliary counters.
 */

// 860. Lemonade Change
// https://leetcode.com/problems/lemonade-change/
// Solution assumes the allowed bill denominations 5, 10, and 20 and does not mutate input.
import java.util.*;

class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
