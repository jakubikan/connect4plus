package connectfour.persistence.couchdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import connectfour.model.GameField;
import connectfour.model.Player;
import connectfour.util.observer.IObserverWithArguments;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * Created by Jakub Werner on 01/02/14.
 */
public class PlayerCouchDb extends CouchDbDocument {
    @TypeDiscriminator
    public String id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("is_computer")
    private boolean isComputer;


    public PlayerCouchDb(@JsonProperty("name")String name, @JsonProperty("is_computer") boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
    }


    public String getName() {
        return name;
    }



    @JsonProperty("is_computer")
    public boolean isComputer() {
        return isComputer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerCouchDb that = (PlayerCouchDb) o;

        if (isComputer != that.isComputer) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isComputer ? 1 : 0);
        return result;
    }
}