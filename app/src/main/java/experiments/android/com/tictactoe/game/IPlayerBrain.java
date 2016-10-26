package experiments.android.com.tictactoe.game;

import android.graphics.Point;

/**
 * Created by kantesh on 10/26/16.
 */

public interface IPlayerBrain {
    Point getMove(Cell[][] cells);
}
