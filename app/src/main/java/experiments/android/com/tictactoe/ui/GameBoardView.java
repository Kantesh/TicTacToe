package experiments.android.com.tictactoe.ui;

import experiments.android.com.tictactoe.game.Cell;
import experiments.android.com.tictactoe.game.GameScoreBoard;

/**
 * Created by kanteshb on 10/25/16.
 */

public interface GameBoardView extends TTTView {

    void showBoard(Cell[][] size);

    void updateScoreboard(GameScoreBoard scoreBoard);
}
