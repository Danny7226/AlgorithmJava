/**
 * You are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 *
 *  The number of "bulls", which are digits in the guess that are in the correct position.
 *  The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 *
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 *
 *
 * algorithm follow up
 If s == g, update bulls counter: bulls += 1.
 Otherwise, if s != g:
 Update cows by adding 1 if so far guess contains more s characters than secret: h[s] < 0.
 Update cows by adding 1 if so far secret contains more g characters than guess: h[g] > 0.
 Update the hashmap by marking the presence of s character in the string secret: h[s] += 1.
 Update the hashmap by marking the presence of g character in the string guess: h[g] -= 1.
 */
class Solution {
    public String getHint(String secret, String guess) {
        // A cow is the num appears in secret but not a bull;

        // use a Set to keep track of all bulls so that bulls can be skipped during guess iteration
        // use a Map to keep track of how many times an integer appears, reduce by 1 in guess iteration
        int cows = 0;
        Set<Integer> bulls = new HashSet<>(); // Set of indices of bulls;
        Map<Character, Integer> map = new HashMap<>(); // Map of value, count
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls.add(i);
            } else {
                char cur = secret.charAt(i);
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (bulls.contains(i)) {
                continue;
            }
            char cur = guess.charAt(i);
            if (map.containsKey(cur)) {
                cows++;
                int count = map.get(cur);
                if (count == 1) {
                    map.remove(cur);
                } else {
                    map.put(cur, count - 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bulls.size());
        sb.append('A');
        sb.append(cows);
        sb.append('B');
        return sb.toString();
    }
}