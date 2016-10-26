package experiments.android.com.tictactoe.game;

/**
 * Created by kantesh on 10/26/16.
 */

public interface IPlayerListener {
    void onMoveAvailable(int row, int col);
    void onManualMove();
    void onMoveNotAvailable();
}
