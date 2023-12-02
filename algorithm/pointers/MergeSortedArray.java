/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 */
class Solution {
    // sort takes O((m+n)log(m+n))
    // 3 pointers 2 read pointers 1 write pointer from the beginning requires additional space for storing nums1 copy
    // 3 pointers 2 read pointers pointing to the end of the input (large) and 1 write pointer points to end of nums1 takes O(m+n)

    // might not be efficient for matrix, as metrix has k input streams, and comparing takes O(k)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int rptr1 = m - 1; // pointing to the last element of nums1;
        int rptr2 = n - 1; // pointing to the last element of nums2
        int wptr = m + n - 1; // pointing to the last index of nums1;

        // while wptr is not reaching till the beginning, compare rptr1 and rptr2 and write with the larger value
        // if either read pointer reaches -1 (out of index), write the other pointer
        while (rptr1 != -1 && rptr2 != -1) {
            if (nums1[rptr1] > nums2[rptr2]) {
                nums1[wptr] = nums1[rptr1--];
            } else {
                nums1[wptr] = nums2[rptr2--];
            }
            wptr--;
        } // after which, either rptr1 or rptr2 would be -1

        while (rptr1 == -1 && wptr != -1) {
            nums1[wptr] = nums2[rptr2--];
            wptr--;
        }

        while (rptr2 == -1 && wptr != -1) {
            nums1[wptr] = nums1[rptr1--];
            wptr--;
        }

        return;
    }
}