package experiments.android.com.tictactoe.ui;

import experiments.android.com.tictactoe.Constants;
import experiments.android.com.tictactoe.R;
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

    private final IGameBoardView gameBoardView;

    private GameBoard gameBoard;
    private GameEngine gameEngine;
    private GameScoreBoard scoreBoard;

    private IPlayer humanPlayer;
    private IPlayer machinePlayer;
    private boolean isGameOver;
    private IPlayer curPlayer;

    public GamePresenter(IGameBoardView view) {
        this.gameBoardView = view;
        initGame();
    }

    private void initGame() {
        gameEngine = new GameEngine(this);
        gameBoard = new GameBoard(Constants.BOARD_SIZE, gameEngine);
        gameBoardView.initBoard(gameBoard.getCells());
        scoreBoard = new GameScoreBoard();

        humanPlayer = new HumanPlayer("Jack", Cell.CellState.CROSS, gameBoard);
        machinePlayer = new MachinePlayer("Jones", Cell.CellState.NOUGHT, gameBoard, new MachineBrain());
        humanPlayer.setOpponent(machinePlayer);
        machinePlayer.setOpponent(humanPlayer);
        humanPlayer.setListener(this);
        machinePlayer.setListener(this);
        curPlayer = humanPlayer;
        curPlayer.requestPlay();
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

        gameBoardView.showMessage(String.format(gameBoardView.getContext().getString(R.string.msg_game_over_win), curPlayer.getName()));
        gameBoardView.updateScoreboard(scoreBoard);
    }

    @Override
    public void onGameDraw() {
        isGameOver = true;
        gameBoardView.showRestart(true);
        scoreBoard.increamentTies();
        gameBoardView.showMessage(gameBoardView.getContext().getString(R.string.msg_game_over_draw));
        gameBoardView.updateScoreboard(scoreBoard);
    }

    private void resetGame() {
        isGameOver = false;
        gameBoard.reset();
        gameBoardView.updateBoard(gameBoard.getCells());
    }

    public void restartClicked() {
        resetGame();
        curPlayer.requestPlay();
    }

    @Override
    public void onMakeMove() {
        gameBoardView.showMessage(gameBoardView.getContext().getString(R.string.msg_waiting_input));
    }

    @Override
    public void onMoveNotAvailable() {
        gameBoardView.showMessage(gameBoardView.getContext().getString(R.string.error_move_unavailable));
    }

    @Override
    public void onMoveDone() {
        gameBoardView.updateBoard(gameBoard.getCells());
        if (!isGameOver) {
            flipTurn();
            curPlayer.requestPlay();
        }
    }

    private void clearMessage() {
        gameBoardView.showMessage("");
    }
}
