/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // use a stack to read position and size from left to right
        // if positive, it might potentially collide with something comeing later
        // if negative, it might collide with what's already read

        Deque<Integer> stack = new ArrayDeque<>();
        int mostRecent;
        for (int a: asteroids) {
            if (a>0) {
                stack.addLast(a);
            } else {
                // asteroid != 0, here it's the negative case
                if (stack.isEmpty()) {
                    // [] -5
                    stack.addLast(a);
                } else {
                    mostRecent = stack.peekLast();
                    if (mostRecent < 0) {
                        // [-2] -5
                        stack.addLast(a);
                    } else {
                        // [5] -1
                        while (!stack.isEmpty() && stack.peekLast() > 0 && a < 0) {
                            a = collide(a, stack.removeLast());
                        }
                        if (a != 0) stack.addLast(a);
                    }
                }
            }
        }

        return dequeueFIFO(stack);
    }

    private int collide(final int a, final int b) {
        if (-a == b) return 0;
        return Math.abs(a) > Math.abs(b) ? a : b;
    }

    private int[] dequeueFIFO(final Deque<Integer> dequeue) {
        final int[] res = new int[dequeue.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dequeue.removeFirst();
        }
        return res;
    }
}