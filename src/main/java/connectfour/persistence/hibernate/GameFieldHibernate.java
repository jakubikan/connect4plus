package connectfour.persistence.hibernate;

import connectfour.model.GameField;
import connectfour.model.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Stefano Di Martino
 * Date: 13.11.13
 * Time: 14:41
 */

@Entity
@Table(name="GameField")
public class GameFieldHibernate  implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    public PlayerHibernate player;
    public PlayerHibernate opponent;
    public PlayerHibernate playerOnTurn;
    public int modCount = 0;

    @OneToMany(mappedBy="gameField")
    public List<MatrixRow> matrix;

    public PlayerHibernate playerWon;

    public boolean gameWon;

    public GameFieldHibernate(Player player, Player opponent, Player[][] gameField, Player playerOnTurn, int modCount, Player playerWon, boolean gameWon) {
        this.player = HibernateUtil.convertToPlayerHibernate(player);
        this.opponent = HibernateUtil.convertToPlayerHibernate(opponent);
        this.modCount = modCount;
        this.playerWon = HibernateUtil.convertToPlayerHibernate(playerWon);
        this.gameWon = gameWon;
        this.playerOnTurn = HibernateUtil.convertToPlayerHibernate(playerOnTurn);

        mapToHibernateScheme(gameField);
    }

    private void mapToHibernateScheme(Player[][] gameField) {
        matrix = new LinkedList<>();

        for(int i = 0; i < GameField.DEFAULT_ROWS; i++) {
            List<PlayerHibernate> row = new LinkedList<>();

            for(int j = 0; j < GameField.DEFAULT_COLUMNS; j++) {
                row.add(HibernateUtil.convertToPlayerHibernate(gameField[i][j]));
            }

            matrix.add(new MatrixRow(row));
        }
    }
}
