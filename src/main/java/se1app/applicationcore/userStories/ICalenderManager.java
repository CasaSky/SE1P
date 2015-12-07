package se1app.applicationcore.userStories;

import java.util.List;

/**
 * Created by talal on 06.12.15.
 */
public interface ICalenderManager {
    /**
     * Einfügen Reiseziele vom Benutzer in seinem Kalender
     * @param client_id Id des Benutzers
     */
    void addDestinations(Integer client_id);

    /**
     * Einsehen die Reiseziele die im Client Kalender sind
     * @param client_id Id des Benutzers
     * @return Liste von den Reisezielen
     */
    List<?> showDestinations(Integer client_id);

    /**
     * Einfügen neuer Reiseziele zum Kalender
     * @param client_id Id des Benutzers
     */
    void addNewDestinations(Integer client_id);
}
