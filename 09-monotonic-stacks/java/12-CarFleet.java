// 853. Car Fleet
// https://leetcode.com/problems/car-fleet/
// Guide template 1, next >= adaptation on arrival times: pop <=, then push index.
// Sort farthest-to-nearest first; stack times are strictly decreasing bottom-to-top.
// O(n log n) time and O(n) auxiliary space; inputs preserved.
import java.util.*;

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new int[] {position[i], speed[i]};
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(a[0], b[0]));
        double[] times = new double[cars.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < cars.length; i++) {
            times[i] = (double) (target - cars[i][0]) / cars[i][1];
            while (!stack.isEmpty() && times[stack.peek()] <= times[i]) {
                // This faster fleet behind catches the current car ahead.
                // Equal arrival times merge at the target as well.
                stack.pop();
            }
            stack.push(i);
        }
        return stack.size();
    }
}

// Alternative: nearest-to-farthest scan needs only the last fleet's arrival time.
// This intentionally compresses away the template's stack and while loop.
// O(n log n) time, O(n) copy/sort space; O(1) scan workspace.
class CompressedSolution {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new int[] {position[i], speed[i]};
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));
        int fleets = 0;
        double last = -1;
        for (int[] car : cars) {
            double time = (double) (target - car[0]) / car[1];
            if (time > last) {
                fleets++;
                last = time;
            }
        }
        return fleets;
    }
}
