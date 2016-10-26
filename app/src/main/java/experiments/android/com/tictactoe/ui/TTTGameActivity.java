package experiments.android.com.tictactoe.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import experiments.android.com.tictactoe.R;
import experiments.android.com.tictactoe.game.Cell;
import experiments.android.com.tictactoe.game.GameScoreBoard;

public class TTTGameActivity extends AppCompatActivity implements GameBoardView {

    private GamePresenter presenter;
    private TableLayout gameBoardLinearLayout;
    private Button restartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new GamePresenter(this);
    }

    private void initViews() {
        gameBoardLinearLayout = (TableLayout) findViewById(R.id.game_board_layout);
        gameBoardLinearLayout.setStretchAllColumns(true);
        gameBoardLinearLayout.setShrinkAllColumns(true);

        restartBtn = (Button) findViewById(R.id.restart_btn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.restartClicked();
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        ((TextView) findViewById(R.id.message)).setText(msg);
    }

    @Override
    public void showBoard(Cell[][] cellArray) {
        int size = cellArray.length;
        gameBoardLinearLayout.removeAllViews();
        for (int row = 0; row < size; row++) {
            TableRow tableRow = new TableRow(this);
            for (int col = 0; col < size; col++) {
                View view = getLayoutInflater().inflate(R.layout.table_cell_layout, null);
                ((TextView) view.findViewById(R.id.cell_value_tv)).setText(cellArray[row][col].getState().symbol());
                final int seedRowIndex = row;
                final int seedColIndex = col;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.cellClicked(seedRowIndex, seedColIndex);
                    }
                });
                tableRow.addView(view);
            }
            gameBoardLinearLayout.addView(tableRow);
        }
    }

    @Override
    public void updateScoreboard(GameScoreBoard scoreBoard) {
        ((TextView) findViewById(R.id.score_board)).setText("Wins: " + scoreBoard.getWins() + " Loses: " + scoreBoard.getLoses() + " Ties: " + scoreBoard.getTies());
    }

    @Override
    public void showRestart(boolean show) {
        restartBtn.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
