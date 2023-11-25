/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // can use either quick select or priority queue or merge sort(comes with additional memory cost)
        // kth largest is okay with both quick select or priority queue
        // kth distinct is easier with priority queue cuz we can do a quick check up for existence O(logn) before insert O(logn)

        // quick select has O(n**2) worst case if pivot is chosen not in the middle (last element as pivot but array was nealy sorted)
        // priority queue and merge sort is more stable with O(nlogn),
        return quickSelectKLargest(nums, 0, nums.length - 1, k);
    }

    private int quickSelectKLargest(final int[] nums, final int start, final int end, final int kth) {
        if (start > end) return 0;
        final int pivot = nums[end];
        int sortedIndex = start;

        // after for loop, everything before sortedIndex will be larger or equal to pivot
        // everything after sortedIndex will be smaller than pivot
        for (int cursor = start; cursor < end; cursor++) {
            if (nums[cursor] > pivot) {
                swap(nums, sortedIndex, cursor);
                sortedIndex++;
            }
        }
        swap(nums, sortedIndex, end);
        if (sortedIndex + 1 == kth) {
            return nums[sortedIndex];
        } else if (sortedIndex + 1 < kth) {
            return quickSelectKLargest(nums, sortedIndex + 1, end, kth);
        } else {
            return quickSelectKLargest(nums, start, sortedIndex -1, kth);
        }
    }

    private void swap(final int[] nums, final int i1, final int i2) {
        final int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}