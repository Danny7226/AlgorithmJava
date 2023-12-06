/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 */
class Solution {

    /**
     Use K-V map to store word - frequency object. Use a pq to maintain K most frequent object, where the object being, word and frequency. Need both word and frequency in the queue because comparator needs to compare frequency first and word lexicographical order if there is a tie
     Put single word takes O(1) for update k-v map, O(k + logk) for remove and O(logk) add in pq, total complexity is O(k) for single word input, and O(nk) for n words.
     This is actually a lot heavy as pq is not good at removing elements. Since we don't need to present data in real time (at any time), we could simply calculate the k most frequent, after reading the entire stream
     count the frequency in O(N), add them in the PQ takes O(Nlogk) vs O(NlogN) if maintaining whole words list
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordsCounter = new HashMap<>();
        for (String word: words) {
            wordsCounter.put(word, wordsCounter.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> {
            // {"i", 2} {"love", 2}
            return a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue();
        });
        wordsCounter.entrySet().forEach(entry -> {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        });

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }
}