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

        CROSS {
            @Override
            public int value() {
                return 1;
            }

            @Override
            public String symbol() {
                return "X";
            }
        },

        NOUGHT {
            @Override
            public int value() {
                return -1;
            }

            @Override
            public String symbol() {
                return "O";
            }
        },

        EMPTY {
            @Override
            public int value() {
                return 0;
            }

            @Override
            public String symbol() {
                return "";
            }
        };

        abstract public int value();
        abstract public String symbol();
    }
}
