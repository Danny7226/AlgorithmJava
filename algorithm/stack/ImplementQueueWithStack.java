/**
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 *  void push(int x) Pushes element x to the back of the queue.
 *  int pop() Removes the element from the front of the queue and returns it.
 *  int peek() Returns the element at the front of the queue.
 *  boolean empty() Returns true if the queue is empty, false otherwise
 *
 * Notes:
 *
 *  You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 *  Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 */
class MyQueue {
    // Push - O(1) per operation, Pop - Amortized O(1) per operation.
    Stack<Integer> storageStack; // lazy load output stack when needed
    Stack<Integer> outputStack;
    public MyQueue() {
        this.storageStack = new Stack<>();
        this.outputStack = new Stack<>();
    }

    public void push(int x) {
        this.storageStack.add(x);
    }

    public int pop() {
        peek();
        return outputStack.pop();
    }

    public int peek() {
        if (!outputStack.isEmpty()) {
            return outputStack.peek();
        } else if (!storageStack.isEmpty()) {
            final int size = storageStack.size();
            for (int i = 0; i < size; i++) {
                outputStack.push(storageStack.pop());
            }
            return outputStack.peek();
        } return 0;
    }

    public boolean empty() {
        return outputStack.isEmpty() && storageStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */