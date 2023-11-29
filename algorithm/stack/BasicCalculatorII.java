/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 *     s consists of digits, '+', '-', '(', ')', and ' '.
 *     s represents a valid expression.
 *     '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 *     '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 *     There will be no two consecutive operators in the input.
 *     Every number and running calculation will fit in a signed 32-bit integer.
 */
class Solution {
    // 321 + 21 * 2 * 2 + 2;
    // stack 321 + 21 *
    // 1.result 2.operater 3.operand 4.operater, when encouters 2nd operater, calculater and update getResult
    // => 1.result 2.operater
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        // result operater operand, we should guarantee that operater's operands are always next to it;
        int result = 0;
        char operater = '+';
        int operand = 0;
        Set<Character> lowerSet = new HashSet<>();
        lowerSet.add('+');
        lowerSet.add('-');
        Set<Character> higherSet = new HashSet<>();
        higherSet.add('*');
        higherSet.add('/');
        for (int i = 0; i < s.length(); i++) {
            final char current = s.charAt(i);
            if (current == ' ') continue;
            if (Character.isDigit(current)) {
                operand = operand * 10 + (current - '0');
                continue;
            }

            if (lowerSet.contains(current)) {
                result = emptyStack(stack, result, operand, operater);
                operater = current; // update operater
                operand = 0;
                continue;
            }

            if (higherSet.contains(current)) {
                if (higherSet.contains(operater)) { // 15/3*2 case higher operations can process without stacking
                    result = getResult(result, operand, operater);
                } else {
                    stack.push(String.valueOf(result));
                    stack.push(String.valueOf(operater));
                    result = operand;
                }
                operater = current;
                operand = 0;
                continue;
            }
        }
        return emptyStack(stack, result, operand, operater);
    }

    private int getResult(final int result, final int operand, final char operater) {
        // System.out.println("result: " + result + " operand: " + operand + " operater: " + operater);
        if (operater == '+') return result + operand;
        if (operater == '-') return result - operand;
        if (operater == '*') return result * operand;
        if (operater == '/') return result / operand;
        throw new IllegalStateException();
    }

    private int emptyStack(final Stack<String> stack, int result, int operand, char operater) {
        result = getResult(result, operand, operater); // calculate left with stored states
        // empty stack
        while (!stack.isEmpty()) {
            operater = stack.pop().charAt(0);
            final int prevResult = Integer.valueOf(stack.pop()); // previous result context stored in stack
            result = getResult(prevResult, result, operater);
        }
        return result;
    }
}