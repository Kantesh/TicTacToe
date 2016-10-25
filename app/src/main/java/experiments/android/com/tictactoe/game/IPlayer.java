package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public interface IPlayer {

    Cell.CellState getValue();

    boolean isHuman();

    void play();

    String getName();
}
