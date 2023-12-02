/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * You must find a solution with a memory complexity better than O(n2).
 *
 * Example 1:
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Example 2:
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 */
class Solution {
    // compare head of each data set and pop the smallest one, increase counter by 1; O(n), with pq O(logn)
    // till counter == k, return smallest head O(k), total O(nk), with pq O(klogn)

    // binary search takes O(nlogn); binary search is optimum when n == 2 or n is constant
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0]; // ascending order so that smallest element are put till the beginning
        }); // int[] {number, arrayIndex, dataIndex}

        final int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.add(new int[] {matrix[i][0], i, 0});
        }

        int[] curr = new int[] {0, 0, 0};
        for (int i = 0; i < k; i++) {
            curr = pq.poll();
            final int arrayIndex = curr[1];
            final int dataIndex = curr[2];
            if (dataIndex != n - 1) {
                pq.add(new int[] {matrix[arrayIndex][dataIndex+1], arrayIndex, dataIndex+1});
            }
        }

        return curr[0];
    }
}