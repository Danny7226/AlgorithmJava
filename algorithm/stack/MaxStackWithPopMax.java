/**
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 *
 * Implement the MaxStack class:
 *
 *     MaxStack() Initializes the stack object.
 *     void push(int x) Pushes element x onto the stack.
 *     int pop() Removes the element on top of the stack and returns it.
 *     int top() Gets the element on the top of the stack without removing it.
 *     int peekMax() Retrieves the maximum element in the stack without removing it.
 *     int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 *
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 *
 * Example 1:
 *
 * Input
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * Output
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 */
class MaxStack {
    // popMax requires remove an element potentially deep in the stack, which requires at least O(n)
    // use a data structure (heap) to store the max value,
    // and use an hashset of ids to keep track of the removed elements to lazy update
    int id;
    PriorityQueue<int[]> heap;
    Stack<int[]> stack;
    Set<Integer> removed;
    public MaxStack() {
        this.heap = new PriorityQueue<>((a,b) ->
                a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        this.id = 0;
        this.stack = new Stack<>();
        this.removed = new HashSet<>();
    }

    public void push(int x) {
        this.stack.push(new int[] {x, id});
        this.heap.add(new int[] {x, id});
        id++;
    }

    public int pop() {
        int[] pop = this.stack.pop();
        while (removed.contains(pop[1])) {
            pop = this.stack.pop();
        }
        removed.add(pop[1]); // add id into removed so that when
        return pop[0];
    }

    public int top() {
        // peek
        int[] peek = this.stack.peek();
        while (removed.contains(peek[1])) {
            this.stack.pop();
            peek = this.stack.peek();
        }
        return peek[0];
    }

    public int peekMax() {
        int[] peek = this.heap.peek();
        while (removed.contains(peek[1])) {
            this.heap.poll();
            peek = this.heap.peek();
        }
        return peek[0];
    }

    public int popMax() {
        int[] pop = this.heap.poll();
        while (removed.contains(pop[1])) {
            pop = this.heap.poll();
        }
        removed.add(pop[1]);
        return pop[0];
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */