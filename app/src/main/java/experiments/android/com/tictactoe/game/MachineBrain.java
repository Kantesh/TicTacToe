package experiments.android.com.tictactoe.game;

import android.graphics.Point;

/**
 * Created by kantesh on 10/26/16.
 */

public class MachineBrain implements IPlayerBrain {

    private IPlayer player;

    public MachineBrain() {
    }

    @Override
    public Point getMove(Cell[][] cells, IPlayer player) {
        //int size = cells.length;
        this.player = player;

        /*for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col].isEmpty()) {
                    return new Point(row, col);
                }
            }
        }*/

        Point point =  findBestMove(cells);
        return point;
    }

    private Point findBestMove(Cell[][] cells) {
        int bestVal = -1000;
        Point bestMove = new Point();
        bestMove.x = -1;
        bestMove.y = -1;

        // Traverse all cells, evalutae minimax function for
        // all empty cells. And return the cell with optimal
        // value.

        int size = cells.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Check if celll is empty
                if (cells[i][j].isEmpty()) {
                    // Make the move
                    cells[i][j].setValue(player.getSeed());

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(cells, 0, false, bestMove);

                    // Undo the move
                    cells[i][j].clearState();

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.x = i;
                        bestMove.y = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Cell[][] cells, int depth, boolean isMax,  Point point) {
        int score = evaluate(cells);
        int size = cells.length;

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and no winner then
        // it is a tie
        if (isMovesLeft(cells) == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // Check if cell is empty
                    if (cells[i][j].isEmpty()) {
                        // Make the move
                        cells[i][j].setValue(player.getSeed());

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(cells, depth + 1, !isMax, point));

                        // Undo the move
                        cells[i][j].clearState();
                    }
                }
            }
            return best;
        } else { // If this minimizer's move
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // Check if cell is empty
                    if (cells[i][j] .isEmpty()) {
                        // Make the move
                        cells[i][j].setValue(player.getOpponent().getSeed()); // = opponent;

                        // Call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(cells, depth + 1, !isMax, point));

                        // Undo the move
                        cells[i][j].clearState();
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(Cell[][] cells) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < cells.length; row++) {
            if (cells[row][0].getState() == cells[row][1].getState() && cells[row][1].getState() == cells[row][2].getState()) {
                if (cells[row][0].getState() == player.getSeed())
                    return +10;
                else if (cells[row][0].getState() == player.getOpponent().getSeed())
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (cells[0][col].getState() == cells[1][col].getState() &&
                    cells[1][col].getState() == cells[2][col].getState()) {
                if (cells[0][col].getState() == player.getSeed())
                    return +10;

                else if (cells[0][col].getState() == player.getOpponent().getSeed())
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (cells[0][0].getState() == cells[1][1].getState() && cells[1][1].getState() == cells[2][2].getState()) {
            if (cells[0][0].getState() == player.getSeed())
                return +10;
            else if (cells[0][0].getState() == player.getOpponent().getSeed())
                return -10;
        }

        if (cells[0][2].getState() == cells[1][1].getState() && cells[1][1].getState() == cells[2][0].getState()) {
            if (cells[0][2].getState() == player.getSeed())
                return +10;
            else if (cells[0][2].getState() == player.getOpponent().getSeed())
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    private boolean isMovesLeft(Cell[][] cells) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (cells[i][j].isEmpty())
                    return true;
        return false;
    }
}