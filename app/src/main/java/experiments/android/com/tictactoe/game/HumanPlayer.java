package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class HumanPlayer implements IPlayer {

    public Cell.CellState vlaue = Cell.CellState.CROSS;

    @Override
    public Cell.CellState getValue() {
        return Cell.CellState.CROSS;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public void play() {

    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
