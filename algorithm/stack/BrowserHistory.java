/**
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 *
 * Implement the BrowserHistory class:
 *
 *     BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 *     void visit(string url) Visits url from the current page. It clears up all the forward history.
 *     string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 *     string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 */
class BrowserHistory {
    Stack<String> backward;
    Stack<String> forward;

    // benefitial to have a curr state to distinguish what's current and what's pass
    // keep things simply, keep boundary clear, separation of concerns
    String curr;

    public BrowserHistory(String homepage) {
        this.backward = new Stack<>();
        this.forward = new Stack<>();
        this.curr = homepage;
    }

    public void visit(String url) {
        this.backward.push(curr);
        this.curr = url;
        this.forward.clear();
    }

    public String back(int steps) {
        for (int i = 0; i < steps; i++) {
            if (this.backward.isEmpty()) {
                break;
            } else {
                this.forward.push(curr);
                this.curr = this.backward.pop();
            }
        }
        return this.curr;
    }

    public String forward(int steps) {
        for (int i = 0; i < steps; i++) {
            if (this.forward.isEmpty()) {
                break;
            } else {
                this.backward.push(this.curr);
                this.curr = this.forward.pop();
            }
        }
        return this.curr;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */