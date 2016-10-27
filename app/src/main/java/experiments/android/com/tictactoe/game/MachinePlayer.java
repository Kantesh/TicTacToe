package experiments.android.com.tictactoe.game;

import android.graphics.Point;

/**
 * Created by kanteshb on 10/25/16.
 */

public class MachinePlayer implements IPlayer {

    private final IPlayerBrain playerBrain;
    private final Cell.CellState seed;
    private final String name;
    private final GameBoard board;
    private IPlayerListener listener;
    private IPlayer opponent;

    public MachinePlayer(String name, Cell.CellState seed, GameBoard gameBoard, IPlayerBrain playerBrain) {
        this.seed = seed;
        this.playerBrain = playerBrain;
        this.name = name;
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
            Point move = playerBrain.getMove(board.getCells(), this);
            makeMove(move.x, move.y);
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