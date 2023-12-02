/**
 *
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Followup thinking: doing one pass by maintaining a Map of num-node pair, where the node points to an element in PriorityQueue
 */
class Solution {
    // TLDR; use a countermap and a size K pq O(n + nlogk)
    // use a countermap and quick select O(n + nlogn) on average
    public int[] topKFrequent(int[] nums, int k) {
        // O(N + Nlogk)
        Map<Integer, Integer> numCounter = new HashMap<>();

        for (int num: nums) {
            numCounter.put(num, numCounter.getOrDefault(num, 0) + 1);
        }

        // int[2] {num, frequency}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1]; // high frequency stay back of the queue
        });

        numCounter.entrySet().forEach(entry -> {
            pq.add(new int[] {entry.getKey(), entry.getValue()});
            if (pq.size() > k) {
                pq.poll();
            }
        });

        final int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }
        return res;
    }
}