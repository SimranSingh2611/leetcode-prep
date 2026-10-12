// 739. Daily Temperatures
// https://leetcode.com/problems/daily-temperatures/
// Guide template 1, next strictly greater: pop <; non-increasing temperatures.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // next greater
        Stack<Integer> s = new Stack<>();
        int n = temperatures.length;
        int[] out = new int[n];
       
       for(int i=0; i<n; i++) {
        while(!s.isEmpty() && temperatures[s.peek()] < temperatures[i]) {
            int ans = s.pop();
            out[ans] = i - ans;
        }
        s.push(i);
       }

       return out;
    }
}
