/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        final ArrayList<int[]> res = new ArrayList<>();
        // sort the intervals based on the first element of the interval
        quickSort(intervals, 0, intervals.length - 1);
        // have a cursor to iterate and merge intervals
        int sortedIndex = 0;
        for (int cursor = 1; cursor < intervals.length; cursor++) {
            if (intervals[cursor][0] <= intervals[sortedIndex][1]) {
                // if first element of cursor smaller or equal to sortedIndex, merge

                // for cases [[1,4], [2,3]]
                final int max = Math.max(intervals[cursor][1], intervals[sortedIndex][1]);
                intervals[sortedIndex][1] = max;
            } else {
                // if not, add sortedIndex to res and then move sortedIndex to cursor
                final int[] temp = new int[]{intervals[sortedIndex][0], intervals[sortedIndex][1]};
                res.add(temp);
                sortedIndex = cursor;
            }
        }
        res.add(new int[]{intervals[sortedIndex][0], intervals[sortedIndex][1]});

        final int[][] resArray = new int[res.size()][2];
        for (int i = 0; i<resArray.length; i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }

    private void quickSort(final int[][] intervals, final int start, final int end) {
        // in place quick sort, time O(NlogN), worst case O(N**2), space O(1)
        if (start >= end) return;
        // choose last element as pivot
        int sortedIndex = start;
        for (int cursor = start; cursor < end; cursor++) {
            if (isSmaller(intervals[cursor], intervals[end])) {
                swap(intervals, cursor, sortedIndex);
                sortedIndex++;
            }
        }
        swap(intervals, sortedIndex, end);
        quickSort(intervals, start, sortedIndex-1);
        quickSort(intervals, sortedIndex+1, end);
    }

    private boolean isSmaller(final int[] a, final int[] b) {
        // for cases [[1,5], [1,4]]
        return (a[0] < b[0] || a[0] == b[0] && a[1] < b[1]);
    }

    private void swap(final int[][] intervals, final int i1, final int i2) {
        final int[] temp = new int[]{intervals[i1][0], intervals[i1][1]};
        intervals[i1][0] = intervals[i2][0];
        intervals[i1][1] = intervals[i2][1];
        intervals[i2][0] = temp[0];
        intervals[i2][1] = temp[1];
    }
}