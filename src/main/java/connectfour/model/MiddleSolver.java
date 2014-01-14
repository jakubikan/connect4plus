package connectfour.model;

/**
 * User: Stefano Di Martino
 * Date: 13.01.14
 * Time: 20:28
 */

public class MiddleSolver implements SolverPlugin {
    private int doNextColumn = 3;
    private final int deepSearch;
    private boolean firstMove = true;
    private final int difficulty = 5;

    private Computer computer;

    public MiddleSolver() {
        deepSearch = difficulty;
    }

    @Override
    public int solve(Computer computer) {
        this.computer = computer;

        if (firstMove && computer.getGameField().isEmpty()) {
            firstMove = false;
            return doNextColumn;
        }

        maxValue(difficulty);
        return doNextColumn;
    }

    private int maxValue(final int restTiefe) {
        int computed = -Integer.MAX_VALUE;
        int drawValue;

        for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
            GameField previousState = computer.saveState();
            if (computer.getGameField().getPlayerOnTurn() != this) {
                computer.getGameField().changePlayerTurn();
            }

            if (computer.getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS) {
                computer.setGameField(previousState);
                continue;
            }

            if (restTiefe <= 1) {
                drawValue = computer.getGameField().evaluateScore();
            } else {
                drawValue = minValue(restTiefe - 1);
            }
            GameField newState = computer.saveState();
            computer.setGameField(previousState);
            if (newState.getWinner() != null) {
                drawValue = Computer.IF_WINNER_TURN_VALUE;
            }
            if (drawValue > computed) {
                computed = drawValue;
                if (restTiefe >= deepSearch
                        || (newState.getWinner() != null && newState.getWinner().equals(this) && restTiefe >= deepSearch)) {
                    doNextColumn = i;
                }
            }

        }
        return computed;
    }

    private int minValue(final int restTiefe) {
        int computed = Integer.MAX_VALUE;
        int drawValue;

        for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
            GameField previousState = computer.saveState();
            if (computer.getGameField().getPlayerOnTurn().equals(this)) {
                computer.getGameField().changePlayerTurn();
            }
            if (computer.getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS) {
                computer.setGameField(previousState);
                continue;

            }
            GameField newState = computer.saveState();
            if (restTiefe <= 1) {
                drawValue = computer.getGameField().evaluateScore();
            } else {
                drawValue = maxValue(restTiefe - 1);
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