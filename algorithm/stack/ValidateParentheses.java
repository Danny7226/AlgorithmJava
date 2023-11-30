/**
 * Input: s = "()"
 * Output: true
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Input: s = "(]"
 * Output: false
 */
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> openSet = new HashSet<>();
        openSet.add('(');
        openSet.add('[');
        openSet.add('{');
        for (int i = 0; i < s.length(); i++) {
            final char curr = s.charAt(i);
            if (openSet.contains(curr)) {
                stack.push(curr);
            } else {
                if (stack.isEmpty()) return false;
                if (isMatching(stack.pop(), curr)) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isMatching(final char open, final char close) {
        if (open == '(') return close == ')';
        if (open == '[') return close == ']';
        if (open == '{') return close == '}';
        throw new IllegalStateException();
    }
}