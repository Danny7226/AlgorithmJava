/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *     MovingAverage(int size) Initializes the object with the size of the window size.
 *     double next(int val) Returns the moving average of the last size values of the stream.
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 *     At most 104 calls will be made to next.
 */

class MovingAverage {
    Queue<Integer> queue; // sliding window is essentially a FIFO data structure, therefore using a queue
    int size;
    int sum;
    public MovingAverage(int size) {
        this.queue = new LinkedList();
        this.size = size;
        this.sum = 0;
    }

    public double next(int val) {
        if (this.queue.size() >= this.size) {
            sum -= this.queue.poll(); // poll first element out and substract it from sum
        }
        this.queue.add(val);
        sum += val;
        return (double) sum / this.queue.size();
    }
}

// Implementation with a customized array Queue
class MovingAverage {
    private int size;
    private int sum;
    private QueueDuo slidingWindow; // use a queue to maintain a sliding winder FIFO

    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
        this.slidingWindow = new QueueDuo();
    }

    public double next(int val) {
        final int actualSize = this.slidingWindow.size();
        if (actualSize >= this.size) {
            this.sum -= this.slidingWindow.poll();
        }

        this.sum += this.slidingWindow.push(val);
        return (double) this.sum/this.slidingWindow.size();
    }

    class QueueDuo {
        private int[] array;
        private int queueHead;
        private int queueTail;
        public QueueDuo() {
            this.array = new int[10000]; // cuz the next operation might be invoked 10000 times
            this.queueHead = 0;
            this.queueTail = 0;
        }

        public int push(final int input) {
            array[queueTail++] = input;
            return input;
        }

        public int poll() {
            if (queueHead < queueTail) {
                return array[queueHead++];
            } else {
                return 0; // customized for this use-case
            }
        }

        public int size() {
            final int size = (this.queueTail - this.queueHead);
            return size;
        }
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */