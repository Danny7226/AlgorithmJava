/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 *     FreqStack() constructs an empty frequency stack.
 *     void push(int val) pushes an integer val onto the top of the stack.
 *     int pop() removes and returns the most frequent element in the stack.
 *         If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 *
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 *
 * Explanation
 * FreqStack freqStack = new FreqStack();
 * freqStack.push(5); // The stack is [5]
 * freqStack.push(7); // The stack is [5,7]
 * freqStack.push(5); // The stack is [5,7,5]
 * freqStack.push(7); // The stack is [5,7,5,7]
 * freqStack.push(4); // The stack is [5,7,5,7,4]
 * freqStack.push(5); // The stack is [5,7,5,7,4,5]
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
 * freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
 */


// Followup, what if pop will pop out all occurance of the most frequent element, like LFU question (use TreeMap<Integer, Frequency>),
// what if to remove the oldest element when there is a tie (use Map<Frequency, Queue<Integer>>);
class FreqStack {
    // value; frequency; index
    int counter;
    PriorityQueue<int[]> pq; // int[] {value; frequency; index}
    Map<Integer, Integer> map;
    public FreqStack() {
        this.counter = 0;
        this.pq = new PriorityQueue<>((a,b) -> {
            return a[1] == b[1] ? b[2] - a[2] : b[1] - a[1];
        });
        this.map = new HashMap<>();
    }

    public void push(int val) {
        // O(logn)
        int freq = this.map.getOrDefault(val, 0);
        this.map.put(val, freq+1);
        this.pq.add(new int[] {val, freq+1, counter++});
    }

    public int pop() {
        int[] pop = this.pq.poll();
        this.map.put(pop[0], this.map.get(pop[0])-1); // lower frequency by 1;
        return pop[0];
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */