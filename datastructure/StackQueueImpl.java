class MyStack {
    Queue<Integer> internalQueue;

    public MyStack() {
        this.internalQueue = new LinkedList();
    }

    public void push(int x) {
        // O(n)
        this.internalQueue.add(x);
        for (int i = 0; i < this.internalQueue.size() - 1; i++) {
            this.internalQueue.add(this.internalQueue.poll());
        }

    }

    public int pop() {
        // O(1)
        return this.internalQueue.poll();
    }

    public int top() {
        // O(1)
        return this.internalQueue.peek();
    }

    public boolean empty() {
        // O(1)
        return this.internalQueue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */