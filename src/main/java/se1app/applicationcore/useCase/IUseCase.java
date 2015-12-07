package se1app.applicationcore.useCase;

import java.util.Date;

/**
 * Created by talal on 06.12.15.
 */
public interface IUseCase {
    /**
     * Die Funktion prüft die Eingabe des Benutzers
     * @param date Datum der Reise
     * @param destination Zielort der Reise
     * @param periodsInDays Dauer der Reise in Tagen
     * @param transport Trasportmittel der Reise
     * @return true fals alle Eingabe gültig sind, sonst false
     */
    boolean checkForm(Date date, String destination, Integer periodsInDays, String transport);

    /**
     * Die Funktion gibt eine Bestätigung der geprüften Eingaben
     * @return Mitteilung ob die Prüfung ok war oder nicht mit Bemerkungen
     */
    String confirmForm();

    /**
     * Schickt den Client eine Bestätigungsemail
     */
    void sendEmailConfirm();
}
