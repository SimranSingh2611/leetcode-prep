// O(m + n) time and O(1) space.
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int first = m - 1;
        int second = n - 1;
        int write = m + n - 1;

        while (second >= 0) {
            if (first >= 0 && nums1[first] > nums2[second]) {
                nums1[write--] = nums1[first--];
            } else {
                nums1[write--] = nums2[second--];
            }
        }
    }
}

