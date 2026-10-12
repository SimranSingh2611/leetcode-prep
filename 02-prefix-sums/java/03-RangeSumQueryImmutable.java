// LeetCode's required class. O(n) construction, O(1) query, O(n) space.
class NumArray {
    private final long[] prefix;

    public NumArray(int[] nums) {
        prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return (int) (prefix[right + 1] - prefix[left]);
    }
}

// Follow-up for point updates: O(n) construction and O(log n) per update/query.
class MutableNumArray {
    private final int[] values;
    private final long[] fenwick;

    public MutableNumArray(int[] nums) {
        values = nums.clone();
        fenwick = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int value) {
        long delta = (long) value - values[index];
        values[index] = value;
        add(index + 1, delta);
    }

    public long sumRange(int left, int right) {
        return prefix(right + 1) - prefix(left);
    }

    private void add(int index, long delta) {
        for (int i = index; i < fenwick.length; i += i & -i) {
            fenwick[i] += delta;
        }
    }

    private long prefix(int length) {
        long sum = 0;
        for (int i = length; i > 0; i -= i & -i) {
            sum += fenwick[i];
        }
        return sum;
    }
}

