package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GameEngine {

    private final IGameListener listener;

    public GameEngine(IGameListener listener) {
        this.listener = listener;
    }

    public void checkGameStatus(Cell[][] cellArray) {
        GameEngine.GameStatus status = getGameStatus(cellArray);

        if (status == GameEngine.GameStatus.WIN) {
            this.listener.onGameFinished();
        } else if (status == GameEngine.GameStatus.DRAW) {
            this.listener.onGameDraw();
        }
    }

    private GameStatus getGameStatus(Cell[][] cellArray) {
        if (checkVerticalAndSumDiagonal(cellArray) || checkHorizontalAndEqualsDiagonal(cellArray))
            return GameStatus.WIN;
        else if (checkDraw(cellArray))
            return GameStatus.DRAW;

        return GameStatus.NONE;
    }

    private boolean checkHorizontalAndEqualsDiagonal(Cell[][] cellArray) {
        int size = cellArray.length, hSum = 0, dSum = 0;
        for (int row = 0; row < size; row++) {
            hSum = 0;
            for (int col = 0; col < size; col++) {

                hSum += cellArray[row][col].getState().value();

                if (Math.abs(hSum) == cellArray.length) {
                    return true;
                }

                if (row == col) {
                    dSum += cellArray[row][col].getState().value();

                    if (Math.abs(dSum) == cellArray.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkVerticalAndSumDiagonal(Cell[][] cellArray) {
        int size = cellArray.length, vSum = 0, dSum = 0;
        for (int col = 0; col < size; col++) {
            vSum = 0;
            for (int row = 0; row < size; row++) {

                vSum += cellArray[row][col].getState().value();

                if (Math.abs(vSum) == cellArray.length) {
                    return true;
                }

                if (row + col == size - 1) {
                    dSum += cellArray[row][col].getState().value();
                    if (Math.abs(dSum) == cellArray.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDraw(Cell[][] cellArray) {
        int size = cellArray.length, sum = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sum += Math.abs(cellArray[row][col].getState().value());

                if (sum == Math.pow(cellArray.length, 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private enum GameStatus {
        WIN, DRAW, NONE
    }
}