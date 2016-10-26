package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class HumanPlayer implements IPlayer {

    public final Cell.CellState seed;
    private final String name;
    private IPlayerListener listener;

    public HumanPlayer(String name, Cell.CellState seed) {
        this.name = name;
        this.seed = seed;
    }

    @Override
    public Cell.CellState getSeed() {
        return Cell.CellState.CROSS;
    }

    @Override
    public void play() {
        if (listener != null) {
            listener.onManualMove();
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
