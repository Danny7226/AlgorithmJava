/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Input: nums = [1], target = 0
 * Output: -1
 */
class Solution {
    // find pivot O(logn), search both side O(logn-k) + O(logk) == O(logn(n-k)) ~= O(logn);
    // Optimization nums[length - 1] < nums[0]
    public int search(int[] nums, int target) {
        final int rightBound = nums.length - 1;
        final int pivotIndex = findPivot(nums, 0, rightBound);

        final int lastElement = nums[rightBound];

        return lastElement == target
                ? rightBound
                : lastElement > target
                ? binarySearch(nums, pivotIndex, rightBound - 1, target)
                : binarySearch(nums, 0, pivotIndex - 1, target);

    }

    // if not find return -1
    private int binarySearch(final int[] nums, final int left, final int right, final int target) {
        if (left > right) return -1;
        if (left == right) return nums[left] == target ? left : -1;

        final int mid = (left + right) / 2;
        if (nums[mid] == target) return mid;
        return nums[mid] < target ? binarySearch(nums, mid + 1, right, target) : binarySearch(nums, left, mid - 1, target);
    }

    // [2, 1], pivotIndex == 1;
    // [1, 2], pivotIndex == 0; in case of not rotated as the question says it's only POSSIBLY rotated
    private int findPivot(final int[] nums, final int left, final int right) {
        // if there is only one element, pivot is 0;
        // find an element, if this element is larget than last element, pivot is to its right, otherwise to its left;
        if (left == right) return right;
        final int mid = (left + right) / 2;
        final int lastValue = nums[nums.length - 1];
        if (nums[mid] == lastValue) return mid;
        return nums[mid] > lastValue ?  findPivot(nums, mid + 1, right) : findPivot(nums, left, mid) ;
    }
}