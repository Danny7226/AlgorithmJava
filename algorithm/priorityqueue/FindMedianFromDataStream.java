/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 *  For example, for arr = [2,3,4], the median is 3.
 *  For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 *  MedianFinder() initializes the MedianFinder object.
 *  void addNum(int num) adds the integer num from the data stream to the data structure.
 *  ouble findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 */
class MedianFinder {
    // insertion sort takes O(logn) to find position O(n) to insert, total O(n)

    // max heap with min heap, balance size so that max.size() ~= min.size()

    // Q, how does it different from find Kth smallest?
    // A, Kth smallest, k is certain so that we could maintain a k sized max heap,
    // medium is not size fixed, it is a percentail, therefore need to maintain 2 heaps
    PriorityQueue<Integer> maxHeap; // smaller half
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // larger half
    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a; // so that large number will stay to front
        });
        this.minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        this.minHeap.add(num);
        this.maxHeap.add(this.minHeap.poll()); // swap to make sure the newly added is in the right bucket
        if (this.maxHeap.size() > this.minHeap.size()) { // minheap always has more elements
            this.minHeap.add(this.maxHeap.poll());
        }
    }

    public double findMedian() {
        if ((this.maxHeap.size() + this.minHeap.size()) % 2 == 0) {
            return (this.maxHeap.peek() + this.minHeap.peek()) * 0.5;
        } else {
            return this.minHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */