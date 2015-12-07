package se1app.applicationcore.userStories;

import java.util.List;

/**
 * Created by talal on 06.12.15.
 */
public interface IDestinationManager {
    /**
     * Sucht nach dem gegebenen Schlüsselwort in der Reiseziel Datenbank und zeigt dies an
     * @param keyword Schlüsselwort Eingabe - Reiseort
     * @return Liste der Reiseziele die ungefär gleich der Benutzereingabe sind
     */
    List<?> findDestinationName(String keyword);

    /**
     * Fügt eine Reise ein
     * @param destinationName Reiseort
     */
    void addDestination(String destinationName);

    /**
     * Registriert den Benutzer in der Reise
     * @param client_id Benutzer id
     * @param destination_id Reise Id
     */
    void signinUpForDestination(Integer client_id, Integer destination_id);

    /**
     * Zeigt die Liste der Reiseziele des Benutzers an
     * @param client_id Benutzer id
     * @return Liste der Reiseziele
     */
    List<?> showDestination(Integer client_id);
}
