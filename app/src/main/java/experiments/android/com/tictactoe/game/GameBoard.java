package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GameBoard {


    private final int size;
    private Cell[][] cellArray;
    private GameEngine gameEngine;

    public GameBoard(int size, GameEngine gameEngine) {
        this.size = size;
        cellArray = new Cell[size][size];
        this.gameEngine = gameEngine;
        init();
    }

    public void init() {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int colIndex = 0; colIndex < size; colIndex++) {
                cellArray[rowIndex][colIndex] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cellArray;
    }

    public boolean markCell(int row, int col, Cell.CellState seed) {
        if (isCellAvailable(row, col)) {
            setCellValue(row, col, seed);
            gameEngine.checkGameStatus(cellArray);
            return true;
        } else {
            return false;
        }
    }

    public boolean isCellAvailable(int row, int col) {
        return getCell(row, col).isEmpty();
    }

    public void setCellValue(int row, int col, Cell.CellState value) {
        cellArray[row][col].setValue(value);
    }

    public Cell getCell(int row, int col) {
        return cellArray[row][col];
    }

    public int getSize() {
        return size;
    }

    public void reset() {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int colIndex = 0; colIndex < size; colIndex++) {
                cellArray[rowIndex][colIndex].setValue(Cell.CellState.EMPTY);
            }
        }
    }
}
