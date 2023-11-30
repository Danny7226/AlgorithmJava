/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 *
 *     It is the empty string, contains only lowercase characters, or
 *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *     It can be written as (A), where A is a valid string.
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 */
class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> removalSet = new HashSet<>();
        char current;
        for (int i = 0; i< s.length(); i++) {
            current = s.charAt(i);
            if (current =='(') {
                stack.push(i);
                continue;
            }

            if (current == ')') {
                // stack empty, remove it
                if (stack.isEmpty()) {
                    removalSet.add(i);
                } else{
                    // stack non empty, pop up
                    stack.pop();
                }
                continue;
            }
        }
        while (!stack.isEmpty()) {
            removalSet.add(stack.pop());
        }

        return removeFromString(s, stack, removalSet);
    }

    private String removeFromString(
            final String s,
            final Stack<Integer> stack,
            final Set<Integer> removalSet) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i< s.length(); i++) {
            if (removalSet.contains(i)) continue;
            else sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}