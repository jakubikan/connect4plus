package connectfour.solver;

import connectfour.model.Computer;
import connectfour.model.GameField;

/**
 * User: Stefano Di Martino
 * Date: 13.01.14
 * Time: 20:26
 */
public interface SolverPlugin {
    int solve(Computer computer, GameField gameField);
}
