/**
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 *
 */
class Solution {
    // the size of such a subarray depends on the max and min element in the subarray, as long as max - min > limit, we can expand the subarray both directions theoretically

    // Use min max heap to keep track of the max, min element in the current sliding window. Move right bound 1 by 1, and if subtraction above limit, move left bound and poll elements from heap till the peek is within left bound
    public int longestSubarray(int[] nums, int limit) {
        // O(NlogN)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0]; // int[2] {value, index}
        });

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> {
            return b[0] - a[0]; // int[2] {value, index}
        });

        int left = 0, right;
        int res = 0;
        for (right = 0; right < nums.length; right++) {
            minHeap.add(new int[] {nums[right], right});
            maxHeap.add(new int[] {nums[right], right});

            // adjust right and left bound
            while (maxHeap.peek()[0] - minHeap.peek()[0] > limit) { // use while here to make sure the new head of both heap are within limit range
                left = Math.min(maxHeap.peek()[1], minHeap.peek()[1]) + 1;
                while (maxHeap.peek()[1] < left) maxHeap.poll();
                while (minHeap.peek()[1] < left) minHeap.poll();
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}