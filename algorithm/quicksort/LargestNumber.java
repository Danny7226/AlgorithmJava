/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 *
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 */
class Solution {
    public String largestNumber(int[] nums) {
        // sort in a way so that '30' '3' will be comparing as '303' compareTo '330'
        sort(nums, 0, nums.length - 1);
        if (nums[0] == 0) return "0"; // [0, 0]
        StringBuilder sb = new StringBuilder();
        for(int i: nums) {
            sb.append(String.valueOf(i));
        }
        return sb.toString();

    }

    private void sort(int[] nums, final int start, final int end) {
        if (start >= end) return;
        final int pivot = nums[end];
        int sortedIndex = start;
        for (int i = start; i < end; i++) {
            if (isLarger(nums[i], pivot)) {
                swap(nums, sortedIndex, i);
                sortedIndex++;
            }
        }
        swap(nums, sortedIndex, end);
        sort(nums, start, sortedIndex - 1);
        sort(nums, sortedIndex + 1, end);
    }

    private boolean isLarger(final int i1, final int i2) {
        final String a = String.valueOf(i1) + String.valueOf(i2);
        final String b = String.valueOf(i2) + String.valueOf(i1);
        final int res = a.compareTo(b);
        return res > 0;
    }

    private void swap(final int[] nums, final int i1, final int i2) {
        final int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;

    }
}