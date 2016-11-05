package experiments.android.com.tictactoe.game;

import android.graphics.Point;

/**
 * Created by kantesh on 10/26/16.
 */

public class UserBrain implements IPlayerBrain {

    private IPlayer player;

    public UserBrain(IPlayer player) {
        this.player = player;
    }

    @Override
    public Point getMove(Cell[][] cells, IPlayer player) {
        return null;
    }
}