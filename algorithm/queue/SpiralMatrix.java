/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * https://leetcode.com/problems/spiral-matrix/description/
 * ->->|
 * ->-> |
 * | <-<-
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        final Set<String> visited = new HashSet<>();
        final List<Integer> res = new ArrayList<>();

        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[] dm = new int[] {0, 1, 0, -1};
        final int[] dn = new int[] {1, 0, -1, 0};
        int direction = 0;
        int i = 0, j = 0;

        while (visited.size() < matrix.length * matrix[0].length) {
            visited.add(i + "" +j);
            res.add(matrix[i][j]);
            // if hitting the visited, or hitting the border, change directions
            int ni = i + dm[direction % 4];
            int nj = j + dn[direction % 4];
            if (visited.contains(ni+""+nj) || (ni == 0 && nj == n) || ni == m && nj == n-1 || ni == m-1 && nj == -1) {
                // // only when next is overflowing, change direcition. use current i, j might result in edge cases where this is only one dimension in matrix
                direction++;
                ni = i + dm[direction % 4];
                nj = j + dn[direction % 4];
            }
            i = ni;
            j = nj;
        }
        return res;
    }
}