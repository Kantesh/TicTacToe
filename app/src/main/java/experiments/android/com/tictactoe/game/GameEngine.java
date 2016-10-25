package experiments.android.com.tictactoe.game;

import experiments.android.com.tictactoe.Constants;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GameEngine {

    public interface GameListener {
        void onGameFinished(IPlayer winner);
        void onGameDraw();
        void cellAlreadySeeded();
    }

    private final GameBoard gameBoard;
    private HumanPlayer humanPlayer;
    private MachinePlayer machinePlayer;
    private IPlayer payer;
    private GameListener listener;

    public GameEngine(GameBoard gameBoard, GameListener listener) {
        this.gameBoard = gameBoard;
        this.listener = listener;
        init();
    }

    public void init() {
        humanPlayer = new HumanPlayer();
        machinePlayer = new MachinePlayer();

        this.payer = humanPlayer;
    }

    public void flipTurn() {
        payer = payer.isHuman() ? machinePlayer : humanPlayer;
    }

    public boolean markCell(int row, int col) {
        if (isCellAvailable(row, col)) {
            gameBoard.setCellValue(row, col, payer.getValue());
            if (!checkGameStatus())
                flipTurn();
            return true;
        } else {
            this.listener.cellAlreadySeeded();
            return false;
        }
    }

    private boolean isCellAvailable(int row, int col) {
        return gameBoard.getCell(row, col).getState() == Cell.CellState.EMPTY;
    }

    private boolean checkGameStatus() {
        GameStatus status = getGameStatus();

        if (this.listener != null) {
            if (status == GameStatus.WIN) {
                this.listener.onGameFinished(payer);
                return true;
            } else if (status == GameStatus.DRAW) {
                this.listener.onGameDraw();
                return true;
            }
        }
        return false;
    }

    private GameStatus getGameStatus() {
        if (checkVertical() || checkHorizontal() /*|| checkLeftDiagonal() || checkRightDiagonal()*/)
            return GameStatus.WIN;
        else if (checkDraw())
            return GameStatus.DRAW;

        return GameStatus.NONE;
    }

    private boolean checkHorizontal() {
        Cell[][] cells = gameBoard.cellArray;
        int size = cells.length, hSum = 0, dSum = 0;
        for (int row = 0; row < size; row++) {
            hSum = 0;
            for (int col = 0; col < size; col++) {

                hSum += cells[row][col].getState().value();

                if (Math.abs(hSum) == gameBoard.getSize()) {
                    return true;
                }

                if (row == col) {
                    dSum += cells[row][col].state.value();

                    if (Math.abs(dSum) == gameBoard.getSize()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        Cell[][] cells = gameBoard.cellArray;
        int size = cells.length, vSum = 0, dSum = 0;
        for (int col = 0; col < size; col++) {
            vSum = 0;
            for (int row = 0; row < size; row++) {

                vSum += cells[row][col].state.value();

                if (Math.abs(vSum) == gameBoard.getSize()) {
                    return true;
                }

                if (row + col == size - 1) {
                    dSum += cells[row][col].state.value();
                    if (Math.abs(dSum) == gameBoard.getSize()) {
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

    private boolean checkDraw() {
        Cell[][] cells = gameBoard.cellArray;
        int size = cells.length, sum = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sum += Math.abs(cells[row][col].getState().value());

                if (sum == Math.pow(gameBoard.getSize(), 2)) {
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