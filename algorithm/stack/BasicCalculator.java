/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * s consists of digits, '+', '-', '(', ')', and ' '.
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
class Solution {
    public int calculate(String s) {
        // [result operator operand] operator => result operator
        Stack<String> stack = new Stack<>();
        int result = 0;
        char operator = '+';
        int operand = 0;

        for (int i = 0; i < s.length(); i++) {
            final char curr = s.charAt(i);
            if (curr == ' ') continue;
            if (curr == '+' || curr == '-') {
                result = getResult(result, operand, operator);
                // reset operand and update operator;
                operand = 0;
                operator = curr;
                continue;
            }

            if (Character.isDigit(curr)) {
                operand = operand * 10 + (curr - '0');
                continue;
            }

            if (curr == '(') {
                stack.push(String.valueOf(result));
                stack.push(String.valueOf(operator));
                result = 0;
                operator = '+';
            }

            if (curr == ')') {
                result = getResult(result, operand, operator);
                // restore context
                operand = result;
                operator = stack.pop().charAt(0);
                result = Integer.valueOf(stack.pop());
            }
        }
        return getResult(result, operand, operator);
    }

    private int getResult(final int result, final int operand, final char operator) {
        if (operator == '+') return result + operand;
        if (operator == '-') return result - operand;
        throw new IllegalStateException();
    }
}