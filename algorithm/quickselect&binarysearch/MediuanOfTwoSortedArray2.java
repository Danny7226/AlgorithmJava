class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         [... partitionA-1] [partitionA ...] total of m elements
         [... partitionB-1] [partitionB ...] total of n elements
         */

        final int n = nums1.length;
        final int m = nums2.length;

        if (n > m) {
            // making sure won't result in out of index
            // binary search in longer array result in a larger scope of re-partition,
            // might result in out of index in shorter array
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0, right = n; // ! not n - 1 cuz possibly n = 0

        // we are looking for a partition A and B, so that
        // 1. nums1[partitionA-1] <= nums2[partitionB]
        // 2. nums1[partitionA] >= nums2[partitionB-1]
        // 3. partitionA + partition B = (n + m + 1) / 2
        while (left <= right) {
            int partitionA = (left + right) / 2;
            int partitionB = (n + m + 1) / 2 - partitionA;
            int leftA = partitionA - 1 < 0 ? Integer.MIN_VALUE : nums1[partitionA - 1];
            int leftB = partitionB - 1 < 0 ? Integer.MIN_VALUE : nums2[partitionB - 1];
            int rightB = partitionB >= m ? Integer.MAX_VALUE : nums2[partitionB];
            int rightA = partitionA >= n ? Integer.MAX_VALUE : nums1[partitionA];
            if (leftA > rightB) {
                // partitionA is too far to the right side
                right = partitionA - 1;
                continue;
            }

            if (rightA < leftB) {
                // partition A is too far to the left side
                left = partitionA + 1;
                continue;
            }

            if ((m+n) % 2 == 1) {
                // totally an odd number of elements, we need to find the middle most (n+m+1)/2th element
                return Math.max(leftA, leftB);
            } else {
                // totally an even number of elements, need to find the (n+m+1)/2 th and (n+m+1)/2 + 1  th element and do an arithmatic mean
                return (Math.max(leftA, leftB)
                        + Math.min(rightA, rightB)) / 2.0;
            }
        }
        return (double)1241231;
    }
}