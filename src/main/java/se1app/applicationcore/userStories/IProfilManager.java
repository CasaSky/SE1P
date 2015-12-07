package se1app.applicationcore.userStories;

import se1app.applicationcore.complexDataTyp.Email;
import se1app.applicationcore.complexDataTyp.Gender;

/**
 * Created by talal on 06.12.15.
 */
public interface IProfilManager {

    /**
     * Erstellt ein Basis Profil
     * @param name Name des Benutzers
     * @param gender Geschlecht des Benutzers
     * @param email Email des Benutzers
     * @param age Alter des Benutzers
     */
    void addBasicProfile(String name, Gender gender, Email email, Integer age);

    /**
     * Logt den Benutzer im System ein
     * @param email Email des Benutzers
     * @param password Password des Benutzers
     * @return true falls Eingabedaten ok sind, sonst false
     */
    boolean login(Email email, String password);

    /**
     * Ändert die E-Mail Adresse des Benutzers
     * @param client_id Id weiß das System wenn der Benutzer eingemeldet ist
     */
    void updateEmail(Integer client_id);
}
