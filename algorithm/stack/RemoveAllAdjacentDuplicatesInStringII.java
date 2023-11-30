/**
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 * remove once it counts to k (if exactly k, removal needs to happen when reaching next char)
 * {111123} k = 2 => {23}
 */
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        char current;
        int[] prev;
        for (int i = 0; i < s.length(); i++) {
            current = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(new int[] {current, 1});
                continue;
            }

            // stack is not empyt;
            prev = stack.peek();
            if (prev[0] != (int) current) {
                // not equal, put current in stack
                stack.push(new int[] {current, 1});
            } else {
                // prev times + 1 == k, stack pop, else, put in {current, prev times + 1}
                if (prev[1] + 1 == k) {
                    for (int j = 0; j < k -1; j++) {
                        stack.pop();
                    }
                } else {
                    stack.push(new int[] {current, prev[1] + 1});
                }
            }
        }

        return inOrder(stack);
    }

    private String inOrder(final Stack<int[]> stack) {
        // empty stack
        final StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append( (char) stack.pop()[0]);
        }
        return sb.reverse().toString();
    }
}