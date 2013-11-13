package connectfour.persistence.hibernate;

import connectfour.model.GameField;
import connectfour.model.Player;
import connectfour.model.SaveGame;

import javax.persistence.*;

import java.io.Serializable;

/**
 * @author Stefano Di Martino
 */

@Entity
@Table(name="SaveGame")
public class SaveGameHibernate implements Serializable {
    private GameField gameField;
    private Player player1;
    private Player player2;
    private String saveGameName;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    /**
     * @param saveGameName Unique savegame name, otherwise it will be overwritten!
     * @param gameField gameField to save
     * @param player1 player 1 to save
     * @param player2 player 2 to save
     */
    public SaveGameHibernate(String saveGameName, GameField gameField, Player player1, Player player2) {
        this.gameField = gameField;
        this.player1 = player1;
        this.player2 = player2;
        this.saveGameName = saveGameName;
    }

    public String getSaveGameName() {
        return saveGameName;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public GameField getGameField() {
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