package experiments.android.com.tictactoe.game;

import android.support.v4.util.Pair;

/**
 * Created by kantesh on 10/26/16.
 */

public interface IPlayerBrain {
    Pair<Integer, Integer> getMove();
}
