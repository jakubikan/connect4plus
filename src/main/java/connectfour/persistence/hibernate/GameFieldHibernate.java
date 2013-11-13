package connectfour.persistence.hibernate;

import connectfour.model.GameField;
import connectfour.model.Player;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Stefano Di Martino
 * Date: 13.11.13
 * Time: 14:41
 */

@Entity
@Table(name="GameField")
public class GameFieldHibernate  {
    @Id
    @GeneratedValue
    private long id;

    public Player player;
    public Player opponent;
    public Player playerOnTurn;
    public int modCount = 0;

    @OneToMany(mappedBy="gameField")
    public List<MatrixRow> matrix;

    public Player playerWon;

    public boolean gameWon;

    public GameFieldHibernate(Player player, Player opponent, Player[][] gameField, Player playerOnTurn, int modCount, Player playerWon, boolean gameWon) {
        this.player = player;
        this.opponent = opponent;
        this.modCount = modCount;
        this.playerWon = playerWon;
        this.gameWon = gameWon;
        this.playerOnTurn = playerOnTurn;

        mapToHibernateScheme(gameField);
    }

    private void mapToHibernateScheme(Player[][] gameField) {
        matrix = new LinkedList<>();

        for(int i = 0; i < GameField.DEFAULT_ROWS; i++) {
            List<Player> row = new LinkedList<>();

            for(int j = 0; j < GameField.DEFAULT_COLUMNS; j++) {
                row.add(gameField[i][j]);
            }

            matrix.add(new MatrixRow(row));
        }
    }
}
