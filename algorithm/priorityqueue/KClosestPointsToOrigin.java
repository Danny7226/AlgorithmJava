/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // maintain a priority queue / tree set of size k, ! descending order so to pop out large points
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]);
        });

        for (int[] point: points) {
            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] res = new int[pq.size()][2];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }
}