/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd anchor a line at each point and count matching slopes to the other points.
 * - Reduced integer slope pairs with consistent signs avoid floating-point grouping errors.
 * Time complexity: O(n^2) expected with fixed-width coordinates; GCD arithmetic is treated as
 *   bounded.
 * Space complexity: O(n) auxiliary slope-map space, reused for each anchor.
 */

import java.util.HashMap;
import java.util.Map;

// O(n^2) time and O(n) space for each anchor.
class Solution {
    public int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }

        int answer = 2;
        for (int anchor = 0; anchor < points.length; anchor++) {
            Map<String, Integer> slopeCounts = new HashMap<>();
            int bestFromAnchor = 0;

            for (int other = anchor + 1; other < points.length; other++) {
                int dx = points[other][0] - points[anchor][0];
                int dy = points[other][1] - points[anchor][1];

                if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                } else {
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }
                    int divisor = gcd(Math.abs(dx), Math.abs(dy));
                    dx /= divisor;
                    dy /= divisor;
                }

                String slope = dy + "/" + dx;
                int count = slopeCounts.merge(slope, 1, Integer::sum);
                bestFromAnchor = Math.max(bestFromAnchor, count);
            }
            answer = Math.max(answer, bestFromAnchor + 1);
        }
        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}

