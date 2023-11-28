class MyStack {
    private int[] arrayImpl;
    private int cursorIndex; // not thread safe
    private boolean isSizeImmutable;
    public static final int DEFAULT_SIZE = 100;

    public MyStack() {
        this.arrayImpl = new int[DEFAULT_SIZE];
        this.cursorIndex = -1;
    }

    public MyStack(final int size) {
        this.arrayImpl = new int[size];
        this.isSizeImmutable = true;
        this.cursorIndex = -1;
    }

    public void push(int x) {
        // O(1) when not expanding size
        // TODO auto scale
        this.arrayImpl[++cursorIndex] = x;
    }

    public int pop() {
        // O(1)
        if (this.cursorIndex == -1) {
            throw new IllegalStateException();
        }
        return this.arrayImpl[cursorIndex--];
    }

    public int top() {
        // O(1)
        if (this.cursorIndex == -1) {
            throw new IllegalStateException();
        }
        return this.arrayImpl[cursorIndex];
    }

    public boolean empty() {
        // O(1)
        return this.cursorIndex == -1;
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