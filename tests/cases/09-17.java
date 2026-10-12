// Exhaustive small arrays compare 132 detection with all index triples.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(true, s.find132pattern(new int[]{3,1,4,2}));
        Checks.eq(false, s.find132pattern(new int[]{1,2,3,4}));
        Checks.eq(true, s.find132pattern(new int[]{-1000000000,1000000000,0}));
        for (int length = 1; length <= 7; length++) {
            int combinations = 1;
            for (int i = 0; i < length; i++) combinations *= 4;
            for (int code = 0; code < combinations; code++) {
                int[] nums = new int[length];
                int value = code;
                for (int i = 0; i < length; i++) { nums[i] = value % 4 - 2; value /= 4; }
                boolean expected = false;
                for (int i = 0; i < length; i++)
                    for (int j = i + 1; j < length; j++)
                        for (int k = j + 1; k < length; k++)
                            if (nums[i] < nums[k] && nums[k] < nums[j]) expected = true;
                int[] original = nums.clone();
                Checks.eq(expected, s.find132pattern(nums));
                Checks.eq(original, nums);
            }
        }
        return Checks.count;
    }
}
