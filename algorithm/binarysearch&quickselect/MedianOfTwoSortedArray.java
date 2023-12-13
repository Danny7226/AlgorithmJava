/**
 * log(m*n) = logm + logn
 * log(m/n) = logm - logn
 * log(m) / log(e) = lnm // change base
 *
 * Can be generalized into Kth smallest element in n Sorted Arrays
 */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int l1 = nums1.length; // e.g. 4
        final int l2 = nums2.length; // e.g 5
        final int totalL = l1 + l2;
        if (totalL % 2 == 1) {
            // odd number in total, find the middle most element
            return findKIndexSmallest(nums1, nums2, totalL/2, 0, l1 - 1, 0, l2 - 1);
        } else {
            // even number in total, do an arithmatic mean of moddle 2 elements
            return ((double) (findKIndexSmallest(nums1, nums2, totalL/2 - 1, 0, l1 - 1, 0, l2 - 1)
                    + findKIndexSmallest(nums1, nums2, totalL/2, 0, l1 - 1, 0, l2 - 1))) / 2;
        }
    }

    private int findKIndexSmallest(final int[] nums1, final int[] nums2, final int k, final int start1, final int end1, final int start2, final int end2) {
        // [start1 ... ] m1 [ ... end1]
        // [start2 ... ] m2 [ ... end2]

        if (start1 > end1) {
            // all elements before index start1 is smaller than the wanting K-index (cuz nums1 and nums2 are sorted ascendingly) and that is start1 number of elements
            return nums2[k - start1]; // k + 1 - start1 - 1
        }

        if (start2 > end2) {
            // all elements before start2 is smaller than the wanting K-index (cuz nums1 and nums2 are sorted ascendingly)
            return nums1[k - start2];
        }

        final int m1 = (start1 + end1) / 2;
        final int m2 = (start2 + end2) / 2;
        if (nums1[m1] <= nums2[m2]) {
            // 1_left <= 1_right && 1_left <= 2_right
            // 2_right >= 2_left && 2_right >= 1_left
            // in short, there is a ranged percentail where l1 and r2 fall into
            if (k <= (m1 + m2)) {
                /**
                 k + 1 <= m1 + m2 + 1
                 all elements in [start1 ... ] m1, [start2 ... ] <= m2 [ ... end2], can safely remove m2 [ ... end2]
                 */
                // k is in the smaller half of the 2 arrays, discard 2_right
                return findKIndexSmallest(nums1, nums2, k, start1, end1, start2, m2 - 1);
            } else {
                /** k > m1 + m2
                 k+1th smallest = 2(n + m + 1) - (k+1) + 1 = 2(n+m+1) - k largest
                 2(n+m+1) - k < n + m + 2 <= n + m + 1. so safely remove [start1 ... ] m1
                 k + 1 <= m1 + m2 + 1
                 all elements in [start1 ... ] m1, [start2 ... ] <= m2 [ ... end2], can safely remove m2 [ ... end2]
                 */
                // k is in the larger half of the 2 arrays, discard 1_left
                return findKIndexSmallest(nums1, nums2, k, m1 + 1, end1, start2, end2);

            }
        } else {
            // 1_right >= m1 >= 2_left && 1_right >= 1_left
            // 2_left <= 2_right && 2_left <= 1_right
            if (k <= (m1 + m2)) {
                // k is in the smaller half of the 2 arrays, discard 1_right
                return findKIndexSmallest(nums1, nums2, k, start1, m1 - 1, start2, end2);
            } else {
                // k is in the larger half of the 2 arrays, discard 2_left
                return findKIndexSmallest(nums1, nums2, k, start1, end1, m2 + 1, end2);
            }
        }
    }
}