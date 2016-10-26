package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GameEngine {

    public GameEngine() {
    }
/*

    public boolean checkGameStatus(GameBoard gameBoard) {
        GameStatus status = getGameStatus(gameBoard.getCells());

        if (this.listener != null) {
            if (status == GameStatus.WIN) {
                this.listener.onGameFinished(player);
                return true;
            } else if (status == GameStatus.DRAW) {
                this.listener.onGameDraw();
                return true;
            }
        }
        return false;
    }
*/

    public GameStatus getGameStatus(Cell[][] cellArray) {
        if (checkVertical(cellArray) || checkHorizontal(cellArray) /*|| checkLeftDiagonal() || checkRightDiagonal()*/)
            return GameStatus.WIN;
        else if (checkDraw(cellArray))
            return GameStatus.DRAW;

        return GameStatus.NONE;
    }

    private boolean checkHorizontal(Cell[][] cellArray) {
        int size = cellArray.length, hSum = 0, dSum = 0;
        for (int row = 0; row < size; row++) {
            hSum = 0;
            for (int col = 0; col < size; col++) {

                hSum += cellArray[row][col].getState().value();

                if (Math.abs(hSum) == cellArray.length) {
                    return true;
                }

                if (row == col) {
                    dSum += cellArray[row][col].state.value();

                    if (Math.abs(dSum) == cellArray.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkVertical(Cell[][] cellArray) {
        int size = cellArray.length, vSum = 0, dSum = 0;
        for (int col = 0; col < size; col++) {
            vSum = 0;
            for (int row = 0; row < size; row++) {

                vSum += cellArray[row][col].state.value();

                if (Math.abs(vSum) == cellArray.length) {
                    return true;
                }

                if (row + col == size - 1) {
                    dSum += cellArray[row][col].state.value();
                    if (Math.abs(dSum) == cellArray.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*private boolean checkLeftDiagonal() {
        Cell[][] cells = gameBoard.cellArray;
        int size = cells.length, dSum = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                if (row == col) {
                    dSum += cells[row][col].state.value();

                    if (Math.abs(dSum) == Constants.BOARD_SIZE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkRightDiagonal() {
        Cell[][] cells = gameBoard.cellArray;
        int size = cells.length, dSum = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row + col == size - 1) {
                    dSum += cells[row][col].state.value();
                    if (Math.abs(dSum) == Constants.BOARD_SIZE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

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

    enum GameStatus {
        WIN, DRAW, NONE
    }
}