package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public interface IPlayer {

    Cell.CellState getSeed();

    void requestPlay();

    String getName();

    void setListener(IPlayerListener listener);

    void makeMove(int row, int col);
}
