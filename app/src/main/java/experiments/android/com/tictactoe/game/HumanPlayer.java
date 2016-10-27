package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class HumanPlayer implements IPlayer {

    public final Cell.CellState seed;
    private final String name;
    private final GameBoard board;
    private IPlayerListener listener;
    private IPlayer opponent;

    public HumanPlayer(String name, Cell.CellState seed, GameBoard gameBoard) {
        this.name = name;
        this.seed = seed;
        this.board = gameBoard;
    }

    @Override
    public Cell.CellState getSeed() {
        return seed;
    }

    public void setOpponent(IPlayer player) {
        opponent = player;
    }

    @Override
    public IPlayer getOpponent() {
        return opponent;
    }

    @Override
    public void requestPlay() {
        if (listener != null) {
            listener.onMakeMove();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setListener(IPlayerListener listener) {
        this.listener = listener;
    }

    @Override
    public void makeMove(int row, int col) {
        if (board.markCell(row, col, seed)) {
            if (listener != null)
                listener.onMoveDone();
        } else {
            if (listener != null)
                listener.onMoveNotAvailable();
        }
    }
}
