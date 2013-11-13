package connectfour.persistence.hibernate;

import connectfour.model.Player;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Stefano Di Martino
 * Date: 13.11.13
 * Time: 16:24
 */
public class MatrixRow {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private GameFieldHibernate gameField;

    @ManyToMany
    public List<Player> row;

    public MatrixRow(List<Player> row) {
        this.row = row;
    }
}