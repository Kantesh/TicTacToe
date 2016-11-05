package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class Cell {

    private CellState state = CellState.EMPTY;

    void setValue(CellState value) {
        this.state = value;
    }

    public CellState getState() {
        return this.state;
    }

    public boolean isEmpty() {
        return state == CellState.EMPTY;
    }

    void clearState() {
        state = CellState.EMPTY;
    }

    public enum CellState {

        CROSS("X", 1) {
            @Override
            public int value() {
                return this.value;
            }

            @Override
            public String symbol() {
                return this.symbol;
            }
        },

        NOUGHT("O", -1) {
            @Override
            public int value() {
                return this.value;
            }

            @Override
            public String symbol() {
                return this.symbol;
            }
        },

        EMPTY ("", 0) {
            @Override
            public int value() {
                return this.value;
            }

            @Override
            public String symbol() {
                return this.symbol;
            }
        };

        protected final String symbol;
        protected final int value;

        CellState(String symbol, int value) {
            this.symbol = symbol;
            this.value = value;
        }

        abstract public int value();
        abstract public String symbol();
    }
}
