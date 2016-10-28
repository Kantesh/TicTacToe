package experiments.android.com.tictactoe.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import experiments.android.com.tictactoe.R;
import experiments.android.com.tictactoe.game.Cell;
import experiments.android.com.tictactoe.game.GameScoreBoard;

public class TTTGameActivity extends AppCompatActivity implements IGameBoardView, Board.IBoardListener {

    private GamePresenter presenter;
    private Board board;
    private Button restartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new GamePresenter(this);
    }

    private void initViews() {
        board = (Board) findViewById(R.id.game_board_layout);
        board.setListener(this);

        restartBtn = (Button) findViewById(R.id.restart_btn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.restartClicked();
            }
        });
    }

    @Override
    public void initBoard(Cell[][] cells) {
        board.init(cells.length);
    }

    @Override
    public void updateBoard(Cell[][] cellArray) {
        board.updateBoard(cellArray);
    }

    @Override
    public void updateScoreboard(GameScoreBoard scoreBoard) {
        ((TextView) findViewById(R.id.score_board)).setText(String.format(getString(R.string.score_board_data_string), scoreBoard.getWins(), scoreBoard.getLoses(), scoreBoard.getTies()));
    }


    @Override
    public void showMessage(String msg) {
        ((TextView) findViewById(R.id.message)).setText(msg);
    }

    @Override
    public void showRestart(boolean show) {
        restartBtn.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onCellClicked(int row, int col) {
        presenter.cellClicked(row, col);
    }
}
