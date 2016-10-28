package experiments.android.com.tictactoe.ui;

import android.content.Context;

import experiments.android.com.tictactoe.game.Cell;
import experiments.android.com.tictactoe.game.GameScoreBoard;

/**
 * Created by kanteshb on 10/25/16.
 */

public interface IGameBoardView extends ITTTView {

    void updateBoard(Cell[][] size);

    void updateScoreboard(GameScoreBoard scoreBoard);

    void showRestart(boolean show);

    Context getContext();

    void initBoard(Cell[][] cells);
}
