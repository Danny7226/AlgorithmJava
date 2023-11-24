/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that
 * objects of the same color are adjacent with the colors in the order red, white, and blue.
 */
class Solution {
    public void sortColors(int[] nums) {
        // quick sort, assign weight for different colors, red 0, white 1, blue 2
        sortInPlace(nums, 0, nums.length -1);
        return;
    }

    private void sortInPlace(final int[] nums, final int start, final int end) {
        if (start >= end) return;
        final int pivot = nums[end];
        int sortedIndex = 0;
        for (int cursor = 0; cursor < end; cursor++) {
            if (nums[cursor] <= pivot) {
                swap(nums, cursor, sortedIndex);
                sortedIndex++;
            }
        }
        swap(nums, sortedIndex, end);
        sortInPlace(nums, start, sortedIndex - 1);
        sortInPlace(nums, sortedIndex + 1, end);
    }

    private void swap(final int[] nums, final int i1, final int i2) {
        final int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}