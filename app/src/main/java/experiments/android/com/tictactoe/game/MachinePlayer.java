package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class MachinePlayer implements IPlayer {

    @Override
    public Cell.CellState getValue() {
        return Cell.CellState.NOUGHT;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void play() {

    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
