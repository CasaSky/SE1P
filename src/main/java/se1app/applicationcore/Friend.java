package se1app.applicationcore;

//import com.google.common.base.Preconditions;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by talal on 04.12.15.
 */
@Entity
public class Friend {
    @Id
    @GeneratedValue
    private Integer friendNr;
    private String friendName;
    @ManyToMany
    private List<Client> clients = new ArrayList<>();

    public Friend(String friendName) {
        //Preconditions.checkNotNull(friendName);
        this.friendName = friendName;
    }

    public Integer getFriendNr() {
        return friendNr;
    }

    public String getFriendName() {
        return friendName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend)) return false;

        Friend friend = (Friend) o;

        if (!friendNr.equals(friend.friendNr)) return false;
        return friendName.equals(friend.friendName);

    }

    @Override
    public int hashCode() {
        int result = friendNr.hashCode();
        result = 31 * result + friendName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendNr=" + friendNr +
                ", friendName='" + friendName + '\'' +
                '}';
    }
}
