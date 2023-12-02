/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> numberCounter = new HashMap<>();
        // for nums1, each num, counter how many times it appears in a hashmap
        for (int num: nums1) {
            numberCounter.put(num, numberCounter.getOrDefault(num, 0) + 1);
        }

        // for nums2, each num, check if num exists in num1. If yes, add to res list and reduce counter by one, and remove entry if counter reaches 0;
        for (int num: nums2) {
            if (numberCounter.containsKey(num)) {
                res.add(num);
                int count = numberCounter.get(num);
                if (count == 1) numberCounter.remove(num);
                else numberCounter.put(num, count - 1);
            }
        }

        int[] resInt = new int[res.size()];
        for (int i = 0; i < resInt.length; i++) {
            resInt[i] = res.get(i);
        }
        return resInt;

    }
}