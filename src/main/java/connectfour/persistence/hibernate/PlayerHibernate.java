package connectfour.persistence.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: Stefano Di Martino
 * Date: 13.11.13
 * Time: 21:38
 */

@Entity
public class PlayerHibernate implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    public String name;
    public boolean isComputer;

    public PlayerHibernate(String name, boolean instanceOf) {
        this.name = name;
        this.isComputer = instanceOf;
    }
}
