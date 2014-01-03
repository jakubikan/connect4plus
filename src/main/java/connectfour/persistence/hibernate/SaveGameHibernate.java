package connectfour.persistence.hibernate;

import connectfour.model.GameField;
import connectfour.model.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Stefano Di Martino
 */

@Entity
@Table(name="SaveGame")
public class SaveGameHibernate implements Serializable {

    private static final Logger log = Logger.getLogger(SaveGameHibernate.class.getName());

    @Column(columnDefinition = "BLOB")
    private GameFieldHibernate gameField;

    private PlayerHibernate player1;
    private PlayerHibernate player2;
    private String saveGameName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    // Hibernate needs default constructor!
    public SaveGameHibernate() {

    }

    /**
     * @param saveGameName Unique savegame name, otherwise it will be overwritten!
     * @param gameField gameField to save
     * @param player1 player 1 to save
     * @param player2 player 2 to save
     */
    public SaveGameHibernate(String saveGameName, GameField gameField, Player player1, Player player2) {
        this.gameField = mapToGameFieldHibernate(gameField);
        this.player1 = HibernateUtil.convertToPlayerHibernate(player1);
        this.player2 = HibernateUtil.convertToPlayerHibernate(player2);
        this.saveGameName = saveGameName;
    }


    public String getSaveGameName() {
        return saveGameName;
    }

    public Player getPlayer2() {
        return HibernateUtil.convertToStandardPlayer(player2);
    }

    public Player getPlayer1() {
        return HibernateUtil.convertToStandardPlayer(player1);
    }

    public GameField getGameField() {
        return mapToGameField(gameField);
    }

    private GameFieldHibernate mapToGameFieldHibernate(GameField gf) {
        try {
            return new GameFieldHibernate(gf.getPlayer(), gf.getOpponent(), gf.getCopyOfGamefield(), gf.getPlayerOnTurn(),
                    gf.getModCount(), gf.getWinner(), gf.isGameWon());

        } catch (CloneNotSupportedException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    private GameField mapToGameField(GameFieldHibernate gf) {
        GameField gameField1 = new GameField(getPlayer1(), getPlayer2());
        gameField1.setGameIsWon(gf.isGameWon());
        gameField1.setModCount(gf.getModCount());
        gameField1.setPlayerOnTurn(HibernateUtil.convertToStandardPlayer(gf.getPlayerOnTurn()));
        gameField1.setGameField(mapToGameFieldsArray(new LinkedList<MatrixRow>(gf.getMatrix())));

        return gameField1;
    }

    private Player[][] mapToGameFieldsArray(List<MatrixRow> matrix) {
        Player [][] players = new Player[GameField.DEFAULT_ROWS][GameField.DEFAULT_COLUMNS];

        int i = 0;


        for (MatrixRow row : matrix) {
            int j = 0;
            for (PlayerHibernate player: row.getRow()) {
                players[i][j++] = HibernateUtil.convertToStandardPlayer(player);
            }
            i++;
        }
        return players;
    }
}

