package connectfour.solver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import connectfour.GameControllerModule;
import connectfour.model.Computer;
import connectfour.model.GameField;

/**
 * User: Stefano Di Martino
 * Date: 13.01.14
 * Time: 20:28
 */
public class MiddleSolver implements SolverPlugin {
    private GameField gameField;
    private Computer computer;

    private final int deepSearch;
    private final int difficulty = 5;
    private final static int FIRST_MOVE = 3;
    private boolean firstMove = true;

    public MiddleSolver() {
        deepSearch = difficulty;
    }

    @Override
    public int solve(Computer computer, GameField gameField) {
        this.gameField = gameField;
        this.computer = computer;

        if (firstMove && gameField.isEmpty()) {
            firstMove = false;
            return FIRST_MOVE;
        }

        return maxValue(difficulty);
    }

    private int maxValue(final int depth) {
        int computed = -Integer.MAX_VALUE;
        int drawValue;

        for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
            GameField previousState = computer.saveState();
            if (this.gameField.getPlayerOnTurn() != this) {
                this.gameField.changePlayerTurn();
            }

            if (this.gameField.dropCoin(i) >= GameField.DEFAULT_ROWS) {
                computer.setGameField(previousState);
                continue;
            }

            if (depth <= 1) {
                drawValue = this.gameField.evaluateScore();
            } else {
                drawValue = minValue(depth - 1);
            }
            GameField newState = computer.saveState();
            computer.setGameField(previousState);
            if (newState.getWinner() != null) {
                drawValue = Computer.IF_WINNER_TURN_VALUE;
            }
            if (drawValue > computed) {
                computed = drawValue;
                if (depth >= deepSearch
                        || (newState.getWinner() != null && newState.getWinner().equals(this) && depth >= deepSearch)) {
                    return i;
                }
            }

        }
        return computed;
    }

    private int minValue(final int depth) {
        int computed = Integer.MAX_VALUE;
        int drawValue;

        for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
            GameField previousState = computer.saveState();
            if (this.gameField.getPlayerOnTurn().equals(this)) {
                this.gameField.changePlayerTurn();
            }
            if (this.gameField.dropCoin(i) >= GameField.DEFAULT_ROWS) {
                computer.setGameField(previousState);
                continue;

            }
            GameField newState = computer.saveState();
            if (depth <= 1) {
                drawValue = this.gameField.evaluateScore();
            } else {
                drawValue = maxValue(depth - 1);
            }

            if (newState.getWinner() != null) {
                drawValue = Computer.IF_LOOSER_TURN_VALUE;
            }
            computer.setGameField(previousState);
            if (drawValue < computed) {
                computed = drawValue;
            }
        }
        return computed;
    }
}