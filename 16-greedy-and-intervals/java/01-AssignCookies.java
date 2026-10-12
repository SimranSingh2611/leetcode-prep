// 455. Assign Cookies
// https://leetcode.com/problems/assign-cookies/
// Solution preserves both arrays; sorting them in place would remove the clone storage.
import java.util.*;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int[] greed = g.clone(), cookies = s.clone();
        Arrays.sort(greed);
        Arrays.sort(cookies);
        int child = 0;
        for (int cookie : cookies) {
            if (child < greed.length && cookie >= greed[child]) {
                child++;
            }
        }
        return child;
    }
}
