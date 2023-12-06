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
class FreqStack {
    // frequency is continuous, if continuous, we could use int to keep track of the maxFrequency change
    Map<Integer, Integer> counter;
    Map<Integer, Stack<Integer>> frequencyStack; // <frequency, Stack<key>>
    int maxFrequency;
    public FreqStack() {
        this.counter = new HashMap<>();
        this.frequencyStack = new HashMap<>();
        this.maxFrequency = 0;
    }

    public void push(int val) {
        int newFreq = this.counter.getOrDefault(val, 0) +1;
        this.counter.put(val, this.counter.getOrDefault(val, 0) +1);
        Stack<Integer> freqStack = this.frequencyStack.getOrDefault(newFreq, new Stack<>());
        freqStack.push(val);
        this.frequencyStack.put(newFreq, freqStack);
        this.maxFrequency = Math.max(this.maxFrequency, newFreq);
    }

    public int pop() {
        int out = frequencyStack.get(maxFrequency).pop();
        counter.put(out, counter.get(out) -1);
        if (frequencyStack.get(maxFrequency).isEmpty()) {
            frequencyStack.remove(maxFrequency);
            this.maxFrequency--;
        }
        return out;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */