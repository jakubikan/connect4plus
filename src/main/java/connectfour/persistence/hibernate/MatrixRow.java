package connectfour.persistence.hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * User: Stefano Di Martino
 * Date: 13.11.13
 * Time: 16:24
 */
@Entity
@Table(name="MatrixRow")
public class MatrixRow implements Serializable {
    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    private GameFieldHibernate gameField;


    @ManyToMany
    private List<PlayerHibernate> row;

    public MatrixRow(List<PlayerHibernate> row) {
        this.row = row;
    }

    public List<PlayerHibernate> getRow() {
        return row;
    }

    public void setRow(List<PlayerHibernate> row) {
        this.row = row;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameFieldHibernate getGameField() {
        return gameField;
    }

    public void setGameField(GameFieldHibernate gameField) {
        this.gameField = gameField;
    }
}