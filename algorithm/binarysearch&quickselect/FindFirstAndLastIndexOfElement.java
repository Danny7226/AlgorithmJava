/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        final int left = binarySearchLeft(nums, 0, nums.length - 1, target);
        final int right = binarySearchRight(nums, 0, nums.length - 1, target);
        return new int[] {left, right};
    }

    // left, right are both inclusive
    private int binarySearchLeft(int[] nums, int left, int right, int target) {
        // find the mid element and compare with target, if less, search (mid + 1, right]
        // if greater search [left, mid - 1)
        // if equals search [left, mid]
        // if not found -1

        if (right == left) return nums[left] == target ? left : -1;
        if (right < left) return -1;
        final int mid = (right + left) / 2; // mid is leaning towards round down to left
        if (nums[mid] < target) {
            return binarySearchLeft(nums, mid + 1, right, target);
        } else if (nums[mid] > target) {
            return binarySearchLeft(nums, left, mid - 1, target);
        } else {
            return binarySearchLeft(nums, left, mid, target);
        }
    }

    private int binarySearchRight(int[] nums, int left, int right, int target) {
        // find the mid element and compare with target, if less, search (mid + 1, right]
        // if greater search [left, mid - 1)
        // if equals search [mid, right];
        // if not found -1
        if (right == left + 1) return nums[right] == target ? right : nums[left] == target ? left : -1;
        if (right == left) return nums[right] == target ? right : -1;
        if (right < left) return -1;
        final int mid = (right + left) / 2; // mid is leaning towards round down to left
        if (nums[mid] < target) {
            return binarySearchRight(nums, mid + 1, right, target);
        } else if (nums[mid] > target) {
            return binarySearchRight(nums, left, mid - 1, target);
        } else {
            // need to find a way to break the loop, as mid is leanding towards the previous left
            return binarySearchRight(nums, mid, right, target);
        }
    }
}