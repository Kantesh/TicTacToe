package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class Cell {

    CellState state = CellState.EMPTY;
    private boolean empty;

    public void setValue(CellState value) {
        this.state = value;
    }

    public CellState getState() {
        return this.state;
    }

    public boolean isEmpty() {
        return state == CellState.EMPTY;
    }

    public enum CellState {

        CROSS, NOUGHT, EMPTY;

        public int value() {

            switch (this) {

                case CROSS:
                    return 1;

                case NOUGHT:
                    return -1;

                case EMPTY:
                    return 0;

            }
            return 0;
        }

        public String symbol() {

            switch (this) {

                case CROSS:
                    return "X";

                case NOUGHT:
                    return "O";

                case EMPTY:
                    return "";
            }
            return "";
        }
    }
}
