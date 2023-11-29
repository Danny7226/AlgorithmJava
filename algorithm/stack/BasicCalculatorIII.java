/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * s consists of digits, '+', '-', '*', '/', '(', and ')'.
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 */
class Solution {
    // TLDR: each operation has couple of state variables, previous result, operator, operand
    // Operations has different priorities. higher priority tasks require a stack push of the current operation context and restore the most recent context after completion of higher priority tasks

    // open bracket, store result and operater in stack
    // close bracket, calculate and up result and pop stack till last opening bracket, then restore result operater operant context
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        int result = 0;
        char operator = '+';
        int operand = 0;

        Set<Character> lower = new HashSet<>();
        lower.add('+');
        lower.add('-');
        Set<Character> higher = new HashSet<>();
        higher.add('*');
        higher.add('/');

        for (int i = 0; i < s.length(); i++) {
            final char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                operand = operand * 10 + (curr - '0');
                continue;
            }

            if (lower.contains(curr)) {
                // perform operation to update result (left operand)
                // 1-1+1
                result = getResult(result, operand, operator);
                // TODO empty stack
                // (4-3*2+3)
                while (!stack.isEmpty() && stack.peek().charAt(0) != '(') {
                    operator = stack.pop().charAt(0);
                    final int prevResult = Integer.valueOf(stack.pop());
                    result = getResult(prevResult, result, operator);
                }
                operator = curr;
                operand = 0;
            }

            if (higher.contains(curr)) {
                // 2 * 2 * 2 or 1 + 2 * 2 * 2 +
                if (higher.contains(operator)) {
                    result = getResult(result, operand, operator);
                    operator = curr;
                    operand = 0;
                } else {
                    stack.push(String.valueOf(result));
                    stack.push(String.valueOf(operator));
                    result = operand;
                    operator = curr;
                    operand = 0;
                }

                continue;
            }

            if (curr == '(') {
                stack.push(String.valueOf(result));
                stack.push(String.valueOf(operator));
                stack.push(String.valueOf('('));
                result = 0;
                operand = 0;
                operator = '+';
                continue;
            }

            if (curr == ')') {
                // 1 + (2+3*4)
                result = getResult(result, operand, operator);
                while (stack.peek().charAt(0) != '(') {
                    operator = stack.pop().charAt(0);
                    final int prevResult = Integer.valueOf(stack.pop());
                    result = getResult(prevResult, result, operator);
                }
                stack.pop(); // pop out open bracket (
                if (!stack.isEmpty()) {
                    operand = result;
                    operator = stack.pop().charAt(0);
                    result = Integer.valueOf(stack.pop());
                } else {
                    // (2 + 3*4) +
                    operand = result;
                    result = 0;
                    operator = '+';
                }
            }
        }

        // 1 + (1 + (2+3*4) + 3) * 2;
        result = getResult(result, operand, operator);
        while (!stack.isEmpty()) {
            operator = stack.pop().charAt(0);
            final int prevResult = Integer.valueOf(stack.pop());
            result = getResult(prevResult, result, operator);
        }
        return result;
    }

    private int getResult(final int result, final int operand, final char operator) {
        if (operator == '+') return result + operand;
        if (operator == '-') return result - operand;
        if (operator == '*') return result * operand;
        if (operator == '/') return result / operand;
        throw new IllegalStateException();
    }

    // stack: 0 +
}