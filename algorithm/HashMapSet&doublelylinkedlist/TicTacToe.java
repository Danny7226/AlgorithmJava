/**
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 *   A move is guaranteed to be valid and is placed on an empty block.
 *   Once a winning condition is reached, no more moves are allowed.
 *   A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 *
 * Implement the TicTacToe class:
 *
 *   TicTacToe(int n) Initializes the object the size of the board n.
 *   int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
 *       0 if there is no winner after the move,
 *       1 if player 1 is the winner after the move, or
 *       2 if player 2 is the winner after the move.
 */
class TicTacToe {
    // row1 [2, 1]/[3, 0], update int[2], update index player-1 with value being original value + 1
    // [m*n + 2][2]

    // or , player1Rows int[n], player1Cols int[n], player1Diag int[2];
    final int n;
    final int[][] tracker;
    public TicTacToe(int n) {
        this.n = n;
        this.tracker = new int[n*n + 2][2];
    }

    public int move(int row, int col, int player) {
        final int rowIndexInTracker = row;
        final int colIndexInTracker = n + col;

        if (row == col) {
            final int diagonalIndexInTracker = 2 * n;
            int diagCount = tracker[diagonalIndexInTracker][player-1];
            if (diagCount == n-1) return player;
            tracker[diagonalIndexInTracker][player-1] = diagCount + 1;
        }

        if (col + row + 1 == n) {
            final int diagonalIndexInTracker = 2 * n + 1;
            int diagCount = tracker[diagonalIndexInTracker][player-1];
            if (diagCount == n-1) return player;
            tracker[diagonalIndexInTracker][player-1] = diagCount + 1;
        }

        int rowCount = tracker[rowIndexInTracker][player-1];
        if (rowCount == n-1) return player;
        tracker[rowIndexInTracker][player-1] = rowCount + 1;

        int colCount = tracker[colIndexInTracker][player-1];
        if (colCount == n-1) return player;
        tracker[colIndexInTracker][player-1] = colCount + 1;

        return 0;
    }

}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */