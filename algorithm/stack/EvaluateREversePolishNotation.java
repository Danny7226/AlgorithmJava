/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 *     The valid operators are '+', '-', '*', and '/'.
 *     Each operand may be an integer or another expression.
 *     The division between two integers always truncates toward zero.
 *     There will not be any division by zero.
 *     The input represents a valid arithmetic expression in a reverse polish notation.
 *     The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Examples:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * While there are operators remaining in the list, find the left-most operator.
 * Apply it to the 2 numbers immediately before it, and replace all 3 tokens (the operator and 2 numbers) with the result.
 */

class Solution {
    public int evalRPN(String[] tokens) {
        // tracking back is natively supported by stack, thus using a stack data structure
        Stack<Integer> trackingStack = new Stack<>();
        Set<String> supportedOperator = new HashSet<>();
        supportedOperator.add("+");
        supportedOperator.add("-");
        supportedOperator.add("*");
        supportedOperator.add("/");

        for (String token: tokens) {
            if (supportedOperator.contains(token)) {
                final Integer operant1 = trackingStack.pop();
                final Integer operant2 = trackingStack.pop();

                // reverse the operant order here is important
                trackingStack.push(predicate(operant2, operant1, token));
            } else {
                trackingStack.push(Integer.valueOf(token));
            }
        }

        return Integer.valueOf(trackingStack.pop());
    }

    private Integer predicate(final Integer operant1, final Integer operant2, final String operator) {
        if (operator.equals("*")) {
            return operant1 * operant2;
        } else if (operator.equals("/")) {
            return operant1 / operant2;
        } else if (operator.equals("+")) {
            return operant1 + operant2;
        } else if (operator.equals("-")) {
            return operant1 - operant2;
        } else {
            throw new IllegalStateException();
        }
    }
}