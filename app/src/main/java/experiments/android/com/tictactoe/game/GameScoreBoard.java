package experiments.android.com.tictactoe.game;

/**
 * Created by kanteshb on 10/25/16.
 */

public class GameScoreBoard {

    int wins, loses, ties;

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getTies() {
        return ties;
    }

    public void intcreamentWins() {
        wins++;
    }

    public void increamentLoses() {
        loses++;
    }

    public void increamentTies() {
        ties++;
    }
}
