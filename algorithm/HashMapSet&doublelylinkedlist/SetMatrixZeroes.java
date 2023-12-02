/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 *
 * https://leetcode.com/problems/73
 */
class Solution {
    // TODO: follow up is use matrix indices to indicate row/column to be zero instead of a set
    // for e.g. if matrix[i][j] == 0 => update matrix[i][0] = 0, matrix[0][j] = 0; and then the 2nd pass to check matrix[i][0] and matrix[0][j]
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowsToZero = new HashSet<>();
        Set<Integer> columnsToZero = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsToZero.add(i);
                    columnsToZero.add(j);
                }
            }
        }


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowsToZero.contains(i) || columnsToZero.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

        return;
    }
}