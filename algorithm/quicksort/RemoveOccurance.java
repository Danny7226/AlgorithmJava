/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 *
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
 *
 *     Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
 *     Return k.
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        // similar to quick sort
        // have a traverse cursor and a sortedIndex.
        // if traverse cursor points to an element of not value 'val', load it upfront (swap)

        int sortedIndex = 0;
        for(int cursor = 0; cursor < nums.length; cursor++) {
            if (nums[cursor] != val) {
                swap(nums, sortedIndex, cursor);
                sortedIndex++;
            }
        }
        return sortedIndex;
    }

    private void swap(int[] nums, int i1, int i2) {
        final int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}