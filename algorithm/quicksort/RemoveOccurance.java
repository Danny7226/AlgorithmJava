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
        // have a removing cursor pointing to the last non-val element of the array
        // have another iterating cursor iterate through the array, as well as comparing nums[cursor] with val
        // If equls, swap with removing cursor and then move removing cursor one index forward

        int removingCursor = nums.length - 1, cursor;
        for (cursor = 0; cursor <= removingCursor; cursor++) {
            while (removingCursor>=0 && nums[removingCursor] == val) removingCursor--;
            if (cursor < removingCursor) {
                if (nums[cursor] == val) swap(nums, cursor, removingCursor);
            };
        }

        return removingCursor + 1; // removingCursor is the index, need to return the length
    }

    private void swap(int[] nums, int i1, int i2) {
        final int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}