package connectfour.persistence.hibernate;

import connectfour.model.GameField;
import connectfour.model.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Stefano Di Martino
 */

@Entity
@Table(name="SaveGame")
public class SaveGameHibernate implements Serializable {
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
            e.printStackTrace();
        }
        return null;
    }

    private GameField mapToGameField(GameFieldHibernate gf) {
        GameField gameField = new GameField(getPlayer1(), getPlayer2());
        gameField.setGameIsWon(gf.gameWon);
        gameField.setModCount(gf.modCount);
        gameField.setPlayerOnTurn(HibernateUtil.convertToStandardPlayer(gf.playerOnTurn));
        gameField.setGameField(mapToGameFieldsArray(gf.matrix));

        return gameField;
    }

    private Player[][] mapToGameFieldsArray(List<MatrixRow> matrix) {
        Player [][] gameField = new Player[GameField.DEFAULT_ROWS][GameField.DEFAULT_COLUMNS];

        int i = 0;


        for (MatrixRow row : matrix) {
            int j = 0;
            for (PlayerHibernate player: row.row) {
                gameField[i][j++] = HibernateUtil.convertToStandardPlayer(player);
            }
            i++;
        }
        return gameField;
    }
}

/*
@Entity
@Table(name="SaveGame")
public class SaveGameHibernate extends SaveGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public SaveGameHibernate(String saveGameName, GameField gameField, Player player1, Player player2) {
        super(saveGameName, gameField, player1, player2);
    }

    @Override
    public String getSaveGameName() {
        return super.getSaveGameName();
    }

    @Override
    public Player getPlayer2() {
        return super.getPlayer2();
    }

    @Override
    public Player getPlayer1() {
        return super.getPlayer1();
    }

    @Override
    public GameField getGameField() {
        return super.getGameField();
    }

}     */