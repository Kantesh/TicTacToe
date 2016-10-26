package experiments.android.com.tictactoe.game;

import android.support.v4.util.Pair;

/**
 * Created by kantesh on 10/26/16.
 */

public class MachineBrain implements IPlayerBrain {

    private final GameBoard gameBoard;

    public MachineBrain(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public Pair<Integer, Integer> getMove() {
        Cell[][] cells = gameBoard.getCells();

        int size = cells.length;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col].isEmpty()) {
                    return new Pair<>(row, col);
                }
            }
        }
        return null;
    }
}
