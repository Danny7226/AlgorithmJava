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
        // merge sort with O(nlogn) complexity, O(n) space cost
        final int[] sorted = mergeSort(nums);
        return sorted[k - 1];
    }

    private int[] mergeSort(final int[] nums) {
        if (nums.length == 1) return nums;
        final int middle = (nums.length - 1) / 2; // 2 for 6 elements array
        final int[] firstHalf = Arrays.copyOfRange(nums, 0, middle + 1);
        final int[] secondHalf = Arrays.copyOfRange(nums, middle + 1, nums.length);
        final int[] sortedFirstHalf = mergeSort(firstHalf);
        final int[] sortedSecondHalf = mergeSort(secondHalf);
        return merge(sortedFirstHalf, sortedSecondHalf);
    }

    private int[] merge(final int[] a1, final int[] a2) {
        final int[] res = new int[a1.length + a2.length];
        int a1Cursor = 0;
        int a2Cursor = 0;
        // merge
        for (int i = 0; i < res.length; i++) {
            if (a1Cursor < a1.length && a2Cursor < a2.length) {
                if (a1[a1Cursor] > a2[a2Cursor]) {
                    res[i] = a1[a1Cursor];
                    a1Cursor++;
                } else {
                    res[i] = a2[a2Cursor];
                    a2Cursor++;
                }
                continue;
            }

            if (a1Cursor >= a1.length) {
                res[i] = a2[a2Cursor];
                a2Cursor++;
                continue;
            }

            if (a2Cursor >= a2.length) {
                res[i] = a1[a1Cursor];
                a1Cursor++;
                continue;
            }
        }
        return res;
    }
}