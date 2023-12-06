/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 *  For examples, if arr = [2,3,4], the median is 3.
 *  For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 *
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 */
class Solution {
    // use two treeset (red/black tree) so that we can remove element in O(logk)
    // treeset cannot handle duplicates, either use int[] {index, value} ot simple store index as workaround
    // priority queue can have duplicates, but element in pq is not strictly sorted, removal takes O(n) complexity
    public double[] medianSlidingWindow(int[] nums, int k) {
        // sort each time O(nklogk)
        // red/black tree O(nlogk)
        // for each num, add to maxTree and then shuffle one element to make sure newly added element in the right. tree set.

        // compare a-b matters here, as '0 compared result' will be treated as the same in treeset
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> minTree = new TreeSet<>(comparator);
        TreeSet<Integer> maxTree = new TreeSet<>(comparator.reversed());

        Runnable balance = () -> { // after each balance, max tree equal or has one more lement to min tree
            while (minTree.size() > maxTree.size()) {
                maxTree.add(minTree.pollFirst());
            }
        };

        Supplier<Double> median = k % 2 == 0 ?
                () -> { return ( (double) nums[maxTree.first()] + nums[minTree.first()] ) * 0.5; } // ! need to convert to double cuz int + int might exceed 32 bits
                : () -> { return nums[maxTree.first()] * 1.0; };

        for (int i = 0; i < k; i++) {
            minTree.add(i);
        }
        balance.run();

        double[] res = new double[nums.length - k + 1];
        res[0] = median.get();

        for (int i = k; i < nums.length; i++) {
            maxTree.add(i);
            minTree.add(maxTree.pollFirst());
            if(!minTree.remove(i-k)) maxTree.remove(i-k);
            balance.run();
            res[i-k+1] = median.get();
        }

        return res;
    }
}