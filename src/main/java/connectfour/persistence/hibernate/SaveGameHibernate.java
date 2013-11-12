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
public class SaveGameHibernate extends SaveGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public SaveGameHibernate(String saveGameName, GameField gameField, Player player1, Player player2) {
        super(saveGameName, gameField, player1, player2);
    }
/*
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
    */
}