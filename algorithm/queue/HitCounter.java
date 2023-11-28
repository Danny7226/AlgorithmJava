/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 *
 * Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 *
 *     HitCounter() Initializes the object of the hit counter system.
 *     void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
 *     int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 *
 * Example 1:
 *
 * Input
 * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
 * [[], [1], [2], [3], [4], [300], [300], [301]]
 * Output
 * [null, null, null, null, 3, null, 4, 3]
 *
 * Explanation
 * HitCounter hitCounter = new HitCounter();
 * hitCounter.hit(1);       // hit at timestamp 1.
 * hitCounter.hit(2);       // hit at timestamp 2.
 * hitCounter.hit(3);       // hit at timestamp 3.
 * hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
 * hitCounter.hit(300);     // hit at timestamp 300.
 * hitCounter.getHits(300); // get hits at timestamp 300, return 4.
 * hitCounter.getHits(301); // get hits at timestamp 301, return 3.
 */
class HitCounter {
    // sliding window, after 5 min, data will be disposed
    Queue<Integer> queue; // since data is FIFO, first in data will be disposed, we use a queue
    public HitCounter() {
        this.queue = new LinkedList<>();
    }

    public void hit(int timestamp) {
        this.queue.add(timestamp); // write heavy, append-on-file only
    }

    public int getHits(int timestamp) {
        while (!this.queue.isEmpty() && this.queue.peek() <= timestamp - 300) {
            this.queue.poll();
        }
        return this.queue.size();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */