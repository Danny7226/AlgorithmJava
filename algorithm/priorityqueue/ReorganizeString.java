/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Input: s = "aab"
 * Output: "aba"
 *
 * Input: s = "aaab"
 * Output: ""
 */
class Solution {
    public String reorganizeString(String s) {
        // O(n) + O(nlogn), where n denotes to the length of Strings
        Map<Character, Integer> counter = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> { // descending on frequency
                    return b.getValue() - a.getValue() ;
                }
        );

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            counter.put(cur, counter.getOrDefault(cur, 0) + 1);
        }; // after which, we have <char, frequency> map

        counter.entrySet().forEach(e -> pq.add(e));

        char[] res = new char[s.length()];

        int index = 0;
        while (!pq.isEmpty()) {
            // if first is not equal to res[prev] write first into and decrese frequency by 1 and put back, if is equal, poll from pq and write and decrease frequecy by one, and put both back
            Map.Entry<Character, Integer> first = pq.poll();
            if (index == 0 || res[index-1] != first.getKey()) {
                res[index++] = first.getKey();

                if (first.getValue() != 1) {
                    first.setValue(first.getValue() - 1);
                    pq.add(first);
                }
            } else { // inded != 0 && res[prev] == first.char;
                if (pq.isEmpty()) {
                    return "";
                } else {
                    Map.Entry<Character, Integer> second = pq.poll();
                    res[index++] = second.getKey();

                    if (second.getValue() != 1) {
                        second.setValue(second.getValue() - 1);
                        pq.add(second);
                    }
                    pq.add(first); // ! remember to add first back
                }
            }
        }

        return new String(res);
    }
}