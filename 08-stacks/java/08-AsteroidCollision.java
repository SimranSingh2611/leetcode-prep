// 735. Asteroid Collision
// https://leetcode.com/problems/asteroid-collision/
// Solution; survivor stack, not a monotonic stack.
import java.util.*;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int[] stack = new int[asteroids.length];
        int size = 0;
        for (int x : asteroids) {
            boolean alive = true;
            while (alive && x < 0 && size > 0 && stack[size - 1] > 0) {
                if (stack[size - 1] < -x) {
                    size--;
                } else {
                    if (stack[size - 1] == -x) {
                        size--;
                    }
                    alive = false;
                }
            }
            if (alive) {
                stack[size++] = x;
            }
        }
        return Arrays.copyOf(stack, size);
    }
}
