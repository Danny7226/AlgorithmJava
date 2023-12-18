/**
 * You may recall that an array arr is a mountain array if and only if:
 *
 *     arr.length >= 3
 *     There exists some i with 0 < i < arr.length - 1 such that:
 *         arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *         arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 *
 *     MountainArray.get(k) returns the element of the array at index k (0-indexed).
 *     MountainArray.length() returns the length of the array.
 *
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 */

// log(10^4) = 4log(10) ~= (12, 16)
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        final int length = mountainArr.length();
        final int peakIndex = findPeak(mountainArr, 0, length - 1);
        final int leftSearch = binarySearch(mountainArr, 0, peakIndex, target, true);
        return leftSearch != -1
                ? leftSearch
                : binarySearch(mountainArr, peakIndex + 1, length - 1, target, false); // if left search == -1, return right search
    }

    // if no element find, return -1;
    // left to right needs to be monoton for sure
    private int binarySearch(final MountainArray mountainArr, final int left, final int right, final int target, final boolean isAscending) {
        if (left > right) return -1;

        final int midIndex = (left + right) / 2;
        final int midValue = mountainArr.get(midIndex);
        if (midValue == target) return midIndex;
        return midValue < target
                ? isAscending
                ? binarySearch(mountainArr, midIndex+1, right, target, isAscending)
                : binarySearch(mountainArr, left, midIndex-1, target, isAscending)
                : isAscending
                ? binarySearch(mountainArr, left, midIndex-1, target, isAscending)
                : binarySearch(mountainArr, midIndex+1, right, target, isAscending);
    }

    // if element id larger than its previous, peak to its right or itself, otherwise to its left
    private int findPeak(final MountainArray mountainArr, final int left, final int right) {
        // 3 * O(logn)
        if (left == right) return right;
        int mid = (left + right) / 2;
        if (mid == 0) mid = 1; // peak can never be the first element (index 0)

        final int prev = mountainArr.get(mid - 1);
        final int curr = mountainArr.get(mid);
        final int next = mountainArr.get(mid + 1);

        if (isPeak(prev, curr, next)) return mid;
        return curr > prev
                ? findPeak(mountainArr, mid + 1, right)
                : findPeak(mountainArr, left, mid - 1);
    }

    private boolean isPeak(final int prev, final int curr, final int next) {
        return (prev < curr && next < curr);
    }
}