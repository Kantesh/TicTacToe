package experiments.android.com.tictactoe.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import experiments.android.com.tictactoe.R;
import experiments.android.com.tictactoe.game.Cell;

/**
 * Created by kanteshb on 10/28/16.
 */

public class Board extends LinearLayout {

    public interface IBoardListener {
        void onCellClicked(int row, int col);
    }

    private final TableLayout tableLayout;
    private final Context context;
    private IBoardListener listener;
    private TextView[][] cellsTvArray;

    public Board(Context context) {
        this(context, null);
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        tableLayout = new TableLayout(context);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);
        addView(tableLayout);
    }

    public void setListener(IBoardListener listener) {
        this.listener = listener;
    }

    public void init(int size) {
        cellsTvArray =  new TextView[size][size];
        tableLayout.removeAllViews();
        for (int row = 0; row < size; row++) {
            TableRow tableRow = new TableRow(context);
            for (int col = 0; col < size; col++) {
                View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.table_cell_layout, null);
                cellsTvArray[row][col] = (TextView) view.findViewById(R.id.cell_value_tv);

                final int seedRowIndex = row;
                final int seedColIndex = col;

                cellsTvArray[row][col].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null)
                            listener.onCellClicked(seedRowIndex, seedColIndex);
                    }
                });
                tableRow.addView(view);
            }
            tableLayout.addView(tableRow);
        }
    }

    public void updateBoard(Cell[][] cellArray) {
        int size = cellArray.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
               cellsTvArray[row][col].setText(cellArray[row][col].getState().symbol());
            }
        }
    }
}
