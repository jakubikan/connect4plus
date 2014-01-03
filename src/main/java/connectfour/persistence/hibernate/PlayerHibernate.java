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

    private String name;
    private boolean isComputer;

    public PlayerHibernate(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean isComputer) {
        this.isComputer = isComputer;
    }
}
