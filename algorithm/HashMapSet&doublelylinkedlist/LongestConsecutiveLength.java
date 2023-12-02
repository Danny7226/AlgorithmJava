/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
class Solution {
    // sort in descending order first so that when we see a num, the consecutive length ofnum + 1 is already calculated if there is one - O(nlogn + n)
    // brute force: for a num, if num + 1 is seen, update consecutive length, also update num - 1 - O(n^2) worst case
    // Optimized brute force: only calculate lenght for num, when num-1 doesn't exist in input nums, to make sure duplicated calculation
    Map<Integer, Integer> valueToCounterMap;
    int longestLength;
    public int longestConsecutive(int[] nums) {
        Set<Integer> exists = new HashSet();
        for (int num: nums) {
            exists.add(num);
        }

        for (int num: nums) {
            if (!exists.contains(num - 1)) {
                int currentLength = 1;
                while (exists.contains(num + 1)) {
                    num = num + 1;
                    currentLength++;
                }
                longestLength = Math.max(currentLength, longestLength);
            }
        }

        return longestLength;
    }
}