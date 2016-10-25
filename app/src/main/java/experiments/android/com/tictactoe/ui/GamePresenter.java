package experiments.android.com.tictactoe.ui;

import experiments.android.com.tictactoe.Constants;
import experiments.android.com.tictactoe.game.GameBoard;
import experiments.android.com.tictactoe.game.GameEngine;
import experiments.android.com.tictactoe.game.GameScoreBoard;
import experiments.android.com.tictactoe.game.IPlayer;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GamePresenter implements GameEngine.GameListener {

    private final GameBoardView gameBoardView;
    private final GameBoard gameBoard;
    private final GameEngine gameEngine;
    private final GameScoreBoard scoreBoard;

    public GamePresenter(GameBoardView view) {
        this.gameBoardView = view;
        gameBoard = new GameBoard(Constants.BOARD_SIZE);
        scoreBoard = new GameScoreBoard();
        gameEngine = new GameEngine(gameBoard, this);
        gameBoardView.showBoard(gameBoard.cellArray);
    }

    public void cellClicked(int row, int col) {
        if (gameEngine.markCell(row, col)) {
            gameBoardView.showBoard(gameBoard.cellArray);
        }
    }

    @Override
    public void onGameFinished(IPlayer winner) {
        if (winner.isHuman())
            scoreBoard.intcreamentWins();
        else
            scoreBoard.increamentLoses();
        resetGame();
        gameBoardView.showMessage("Game over and winner is: " + winner.getName());
        gameBoardView.updateScoreboard(scoreBoard);
    }

    @Override
    public void onGameDraw() {
        resetGame();
        scoreBoard.increamentTies();
        gameBoardView.showMessage("Game over and Draw");
        gameBoardView.updateScoreboard(scoreBoard);
    }

    private void resetGame() {
        gameBoard.reset();
        gameBoardView.showBoard(gameBoard.cellArray);
    }

    @Override
    public void cellAlreadySeeded() {
        gameBoardView.showMessage("Cell not available");
    }
}
