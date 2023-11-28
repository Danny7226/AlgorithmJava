class MinStack {
    // int[] {value, minValue}
    Stack<int[]> impl;
    public MinStack() {
        this.impl = new Stack<>();
    }

    public void push(int val) {
        if (this.impl.size() == 0) {
            this.impl.push(new int[] {val, val});
        } else {
            final int prevSmallest = this.impl.peek()[1];
            this.impl.push(new int[] {val, Math.min(val, prevSmallest)});
        }
    }

    public void pop() {
        this.impl.pop();
    }

    public int top() {
        return this.impl.peek()[0];
    }

    public int getMin() {
        return this.impl.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */