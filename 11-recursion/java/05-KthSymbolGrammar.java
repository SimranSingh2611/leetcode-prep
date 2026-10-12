// 779. K-th Symbol in Grammar
// https://leetcode.com/problems/k-th-symbol-in-grammar/
// Solution; k is a valid 1-based position.
import java.util.*;

class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int parent = kthGrammar(n - 1, (k + 1) / 2);
        return k % 2 == 1 ? parent : 1 - parent;
    }
}
