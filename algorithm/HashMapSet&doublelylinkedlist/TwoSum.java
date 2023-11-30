/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // {value: index}

        int current;
        // inverted index
        for (int i = 0; i < nums.length; i++) {
            current = nums[i];
            if (map.containsKey(current)) {
                return new int[] {map.get(current), i};
            } else {
                map.put(target - current, i);
            }
        }

        throw new IllegalStateException();
    }
}