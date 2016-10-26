package experiments.android.com.tictactoe.game;

import android.support.v4.util.Pair;

/**
 * Created by kanteshb on 10/25/16.
 */

public class MachinePlayer implements IPlayer {

    private final IPlayerBrain playerBrain;
    public final Cell.CellState seed;
    private final String name;
    private IPlayerListener listener;

    public MachinePlayer(String name, Cell.CellState seed, IPlayerBrain playerBrain) {
        this.seed = seed;
        this.playerBrain = playerBrain;
        this.name = name;
    }

    @Override
    public Cell.CellState getSeed() {
        return Cell.CellState.NOUGHT;
    }

    @Override
    public void play() {
        if (listener != null) {
            Pair<Integer, Integer> move = playerBrain.getMove();
            listener.onMoveAvailable(move.first, move.second);
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
}