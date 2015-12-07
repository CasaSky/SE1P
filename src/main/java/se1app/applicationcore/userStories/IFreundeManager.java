package se1app.applicationcore.userStories;

/**
 * Created by talal on 06.12.15.
 */
public interface IFreundeManager {
    /**
     *  Fügt ein Freund in der Freundesliste des Benutzers ein
     * @param friendName Name des Freundes
     * @param client_id Id des eingeloggten Benutzers
     */
    void addFriend(String friendName, Integer client_id);
}
