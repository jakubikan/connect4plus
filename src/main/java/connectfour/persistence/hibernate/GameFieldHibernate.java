package connectfour.persistence.hibernate;

import connectfour.model.GameField;
import connectfour.model.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    private PlayerHibernate player;


    private PlayerHibernate opponent;
    private PlayerHibernate playerOnTurn;
    private int modCount = 0;

    @OneToMany(mappedBy="gameField")
    private Set<MatrixRow> matrix;

    private PlayerHibernate playerWon;

    private boolean gameWon;

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
        matrix = new LinkedHashSet<MatrixRow>();

        for(int i = 0; i < GameField.DEFAULT_ROWS; i++) {
            List<PlayerHibernate> row = new LinkedList<PlayerHibernate>();

            for(int j = 0; j < GameField.DEFAULT_COLUMNS; j++) {
                row.add(HibernateUtil.convertToPlayerHibernate(gameField[i][j]));
            }

            matrix.add(new MatrixRow(row));
        }
    }

    public PlayerHibernate getPlayer() {
        return player;
    }

    public void setPlayer(PlayerHibernate player) {
        this.player = player;
    }

    public PlayerHibernate getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerHibernate opponent) {
        this.opponent = opponent;
    }

    public PlayerHibernate getPlayerOnTurn() {
        return playerOnTurn;
    }

    public void setPlayerOnTurn(PlayerHibernate playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }

    public Set<MatrixRow> getMatrix() {
        return matrix;
    }

    public void setMatrix(Set<MatrixRow> matrix) {
        this.matrix = matrix;
    }

    public PlayerHibernate getPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(PlayerHibernate playerWon) {
        this.playerWon = playerWon;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}