package experiments.android.com.tictactoe.ui;

import experiments.android.com.tictactoe.Constants;
import experiments.android.com.tictactoe.game.Cell;
import experiments.android.com.tictactoe.game.GameBoard;
import experiments.android.com.tictactoe.game.GameEngine;
import experiments.android.com.tictactoe.game.GameScoreBoard;
import experiments.android.com.tictactoe.game.HumanPlayer;
import experiments.android.com.tictactoe.game.IGameListener;
import experiments.android.com.tictactoe.game.IPlayer;
import experiments.android.com.tictactoe.game.IPlayerListener;
import experiments.android.com.tictactoe.game.MachineBrain;
import experiments.android.com.tictactoe.game.MachinePlayer;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GamePresenter implements IPlayerListener, IGameListener {

    private final GameBoardView gameBoardView;

    private final GameBoard gameBoard;
    private final GameEngine gameEngine;
    private final GameScoreBoard scoreBoard;

    private IPlayer humanPlayer;
    private IPlayer machinePlayer;
    private boolean isGameOver;
    private IPlayer curPlayer;

    public GamePresenter(GameBoardView view) {
        this.gameBoardView = view;

        gameEngine = new GameEngine(this);
        gameBoard = new GameBoard(Constants.BOARD_SIZE, gameEngine);
        gameBoardView.showBoard(gameBoard.getCells());
        scoreBoard = new GameScoreBoard();

        humanPlayer = new HumanPlayer("Jack", Cell.CellState.CROSS, gameBoard);
        machinePlayer = new MachinePlayer("Jones", Cell.CellState.NOUGHT, gameBoard, new MachineBrain());
        humanPlayer.setListener(this);
        machinePlayer.setListener(this);
        curPlayer = humanPlayer;
    }

    public void cellClicked(int row, int col) {
        if (!isGameOver) {
            clearMessage();
            attemptMarkCell(row, col);
        }
    }

    private void attemptMarkCell(int row, int col) {
        if (curPlayer instanceof HumanPlayer)
            curPlayer.makeMove(row, col);
    }

    public void flipTurn() {
        if (curPlayer.equals(humanPlayer))
            curPlayer = machinePlayer;
        else
            curPlayer = humanPlayer;
    }

    @Override
    public void onGameFinished() {
        isGameOver = true;

        if (curPlayer.equals(humanPlayer))
            scoreBoard.intcreamentWins();
        else
            scoreBoard.increamentLoses();

        gameBoardView.showRestart(true);
        gameBoardView.showMessage("Game over and winner is: " + curPlayer.getName());
        gameBoardView.updateScoreboard(scoreBoard);
    }

    @Override
    public void onGameDraw() {
        isGameOver = true;
        gameBoardView.showRestart(true);
        scoreBoard.increamentTies();
        gameBoardView.showMessage("Game over and Draw");
        gameBoardView.updateScoreboard(scoreBoard);
    }

    private void resetGame() {
        isGameOver = false;
        gameBoard.reset();
        gameBoardView.showBoard(gameBoard.getCells());
    }

    public void restartClicked() {
        resetGame();
        curPlayer.requestPlay();
    }

    /*@Override
    public void onMoveAvailable(int row, int col) {
        attemptMarkCell(row, col);
    }*/

    @Override
    public void onMakeMove() {
        gameBoardView.showMessage("Waiting for user input...");
    }

    @Override
    public void onMoveNotAvailable() {
        gameBoardView.showMessage("Move not available.");
    }

    @Override
    public void onMoveDone() {
        gameBoardView.showBoard(gameBoard.getCells());
        if (!isGameOver) {
            flipTurn();
            curPlayer.requestPlay();
        }
    }

    private void clearMessage() {
        gameBoardView.showMessage("");
    }
}
