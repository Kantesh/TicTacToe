package experiments.android.com.tictactoe.game;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by kantesh on 10/26/16.
 */

public class MachineBrain implements IPlayerBrain {
    private static final int EVALUATION_SCORE = 10;
    private static final int BEST_SCORE = 1000;

    private IPlayer player;

    public MachineBrain() {
    }

    @Override
    public Point getMove(Cell[][] cells, IPlayer player) {
        this.player = player;
        return findBestMove(cells);
    }

    private Point findBestMove(Cell[][] cells) {
        int bestVal = -BEST_SCORE;
        Point bestMove = new Point();
        bestMove.x = -1;
        bestMove.y = -1;

        // Traverse all cells, evalutae minMax function for
        // all empty cells. And return the cell with optimal
        // value.

        int size = cells.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Check if cell is empty
                if (cells[i][j].isEmpty()) {
                    // Make the move
                    cells[i][j].setValue(player.getSeed());

                    // compute evaluation function for this move.
                    int moveVal = minMax(cells, 0, false);

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

    private int minMax(Cell[][] cells, int depth, boolean isMax) {
        int size = cells.length;
        int score = evaluate(cells);

        // If Maximizer or Minimizer has won the game return his/her
        // evaluated score
        if (score == EVALUATION_SCORE || score == -EVALUATION_SCORE)
            return score;

        // If there are no more moves and no winner then
        // it is a tie
        if (!isMovesLeft(cells) || depth == size - 1)
            return 0;

        if (isMax) { // If this maximizer's move
            int best = -BEST_SCORE;

            // Traverse all cells
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // Check if cell is empty
                    if (cells[i][j].isEmpty()) {
                        // Make the move
                        cells[i][j].setValue(player.getSeed());

                        // Call minMax recursively and choose
                        // the maximum value
                        best = Math.max(best, minMax(cells, depth + 1, !isMax));

                        // Undo the move
                        cells[i][j].clearState();
                    }
                }
            }
            return best;
        } else { // If this minimizer's move
            int best = BEST_SCORE;

            // Traverse all cells
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // Check if cell is empty
                    if (cells[i][j].isEmpty()) {
                        // Make the move
                        cells[i][j].setValue(player.getOpponent().getSeed());

                        // Call minMax recursively and choose the minimum value
                        best = Math.min(best, minMax(cells, depth + 1, !isMax));

                        // Undo the move
                        cells[i][j].clearState();
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(Cell[][] cells) {
        int size = cells.length;
        int xsum;
        int ysum;
        int totalPlayerWinVlaue = player.getSeed().value() * size;
        int totalOpponentWinVlaue = player.getOpponent().getSeed().value() * size;

        // Checking for Rows for X or O victory.
        for (int row = 0; row < size; row++) {
            xsum = 0;
            for (int col = 0; col < size; col++) {
                xsum += cells[row][col].getState().value();
            }
            if (xsum == totalPlayerWinVlaue)
                return +EVALUATION_SCORE;
            else if (xsum == totalOpponentWinVlaue)
                return -EVALUATION_SCORE;
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < size; col++) {
            ysum = 0;
            for (int row = 0; row < size; row++) {
                ysum += cells[row][col].getState().value();
            }

            if (ysum == totalPlayerWinVlaue)
                return +EVALUATION_SCORE;
            else if (ysum == totalOpponentWinVlaue)
                return -EVALUATION_SCORE;
        }

        xsum = 0;
        ysum = 0;
        // Checking for Diagonals for X or O victory.
        for (int i = 0; i < size; i++) {
                xsum += cells[i][i].getState().value();
                ysum += cells[i][size - 1 - i].getState().value();
        }

        if (xsum == totalPlayerWinVlaue || ysum == totalPlayerWinVlaue)
            return +EVALUATION_SCORE;
        else if (xsum == totalOpponentWinVlaue || ysum == totalOpponentWinVlaue)
            return -EVALUATION_SCORE;

        // Else if none of them have won then return 0
        return 0;
    }

    private boolean isMovesLeft(Cell[][] cells) {
        int size = cells.length;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (cells[i][j].isEmpty())
                    return true;
        return false;
    }
}