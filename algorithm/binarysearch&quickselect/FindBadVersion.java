/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Input: n = 1, bad = 1
 * Output: 1
 *
 *     1 <= bad <= n <= 231 - 1
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
public class Solution extends VersionControl {
    // IMPORTANT: Make sure there is no integer over flow as n could be 2^31 - 1
    // it's guaranteed there is a bad version

    public int firstBadVersion(int n) {
        // if mid element is bad, the first bad is either itself or to its left
        // if mid element is not bad, the first bad is to its right
        return findFirstBad(0, n);
    }

    private int findFirstBad(final int left, final int right) {
        // its guaranteed the first bad version is within the range of [left, right] inclusive
        if (left == right) return right;
        final int mid = left + (right - left) / 2; // so that there is no integer over flow as n could be 2^31 - 1
        return this.isBadVersion(mid) ? findFirstBad(left, mid) : findFirstBad(mid + 1, right);
    }
}