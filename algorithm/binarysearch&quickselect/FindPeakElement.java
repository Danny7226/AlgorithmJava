/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 */
class Solution {
    // find a random element, if its prev is ascending, there must be a peak to its right
    public int findPeakElement(int[] nums) {
        return findPeak(nums, 0, nums.length-1);
    }

    private int findPeak(final int[] nums, final int left, final int right) {
        final int midIndex = (left + right) / 2;
        if (isPeak(nums, midIndex)) return midIndex;
        if (midIndex == 0) return 1; // midIndex - 1 might be -1, if midIndex == 0, and its not peak, it has to be left 0, right 1;
        return nums[midIndex] > nums[midIndex-1]
                ? findPeak(nums, midIndex+1, right)
                : findPeak(nums, left, midIndex-1);
    }

    private boolean isPeak(final int[] nums, final int index){
        if (nums.length == 1) return true;
        if (index == 0) return nums[index] > nums[index + 1];
        if (index == nums.length - 1) return nums[index] > nums[index - 1];
        return (nums[index] > nums[index-1] && nums[index] > nums[index+1]);
    }
}