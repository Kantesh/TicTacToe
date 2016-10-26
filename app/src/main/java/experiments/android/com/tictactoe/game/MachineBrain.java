package experiments.android.com.tictactoe.game;

import android.graphics.Point;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kantesh on 10/26/16.
 */

public class MachineBrain implements IPlayerBrain {

    public MachineBrain() {
    }

    @Override
    public Point getMove(Cell[][] cells) {
        int size = cells.length;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col].isEmpty()) {
                    return new Point(row, col);


                }
            }
        }

        //Point point = new Point();
        //minimax(cells, 2, 1, point);
        return null;
    }


   /* private Point findBestMove(Cell[][] cells)
    {
        int bestVal = -1000;
        Point bestMove = new Point();
        bestMove.x = -1;
        bestMove.y = -1;

        // Traverse all cells, evalutae minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i<3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                // Check if celll is empty
                if (cells[i][j].isEmpty()) {
                    // Make the move
                    cells[i][j].setValue(Cell.CellState.CROSS);

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(cells, 0, false);

                    // Undo the move
                    cells[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.x = i;
                        bestMove.y = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    int minimax(Cell[][] cells, int depth, boolean isMax)
    {
        int score = evaluate(cells);

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
        if (isMovesLeft(cells)==false)
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    // Check if cell is empty
                    if (cells[i][j]=='_')
                    {
                        // Make the move
                        cells[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max( best, minimax(board, depth+1, !isMax) );

                        // Undo the move
                        cells[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i<3; i++)
            {
                for (int j = 0; j<3; j++)
                {
                    // Check if cell is empty
                    if (cells[i][j]=='_')
                    {
                        // Make the move
                        cells[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(cells, depth+1, !isMax));

                        // Undo the move
                        cells[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    int evaluate(Cell[][] cells)
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row<3; row++)
        {
            if (cells[row][0]==cells[row][1] &&
                    cells[row][1]==cells[row][2])
            {
                if (cells[row][0]==player)
                    return +10;
                else if (cells[row][0]==opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col<3; col++)
        {
            if (cells[0][col]==cells[1][col] &&
                    cells[1][col]==cells[2][col])
            {
                if (b[0][col]==player)
                    return +10;

                else if (b[0][col]==opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0]==b[1][1] && b[1][1]==b[2][2])
        {
            if (b[0][0]==player)
                return +10;
            else if (b[0][0]==opponent)
                return -10;
        }

        if (b[0][2]==b[1][1] && b[1][1]==b[2][0])
        {
            if (b[0][2]==player)
                return +10;
            else if (b[0][2]==opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }*/
}
