/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
class Solution {
    // eat => a1e1t1; aab => a2b2
    // use an array of size 26, with indices representing letter, value representing requency
    // transform array into string such as "a1b4" and use it as the hash key in the map;

    // we don't need to know the sorted result, we only need to know how many times a letter appears and be able to generate a hash code that doesn't  distinguish a2b2 and b2a2;
    public List<List<String>> groupAnagrams(String[] strs) {
        // O(N*K) compared to O(N*KLogK) when sorting
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            int[] counter = new int[26];
            // calculate counter
            for (int i = 0; i < str.length(); i++) {
                int curr = str.charAt(i);
                counter[curr - 'a']++;
            }
            StringBuilder hash = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counter[i] != 0) {
                    hash.append((char) (i+'a'));
                    hash.append(counter[i]);
                }
            }
            String key = hash.toString();
            List<String> anagramList = map.getOrDefault(key, new ArrayList<>());
            anagramList.add(str);
            map.put(key, anagramList);
        };

        List<List<String>> res = new ArrayList<>();
        map.entrySet().forEach(entry -> res.add(entry.getValue()));
        return res;
    }
}