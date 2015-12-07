package se1app.applicationcore;

import org.springframework.beans.factory.annotation.Autowired;
import se1app.applicationcore.complexDataTyp.Email;
import se1app.applicationcore.repositories.ClientRepository;
import se1app.applicationcore.useCase.IUseCase;

import java.util.Date;

public class AppUseCases implements IUseCase{

    private boolean confirmed=false;
    private String clientName;
    private String destinationName;
    private String confirmationText;
    private Email email;
    @Autowired
    ClientRepository clientRepository;
    @Override
    public boolean checkForm(Integer client_id, Date date, String destination, Integer periodsInDays, String transport) {
        if ( periodsInDays < 0 ) {
            return false;
        }
        if (new Date().after(date)) {
            return false;
        }
        Client client = clientRepository.findOne(client_id);
        if (client==null)
            return false;
        Destination dest = new Destination(destination, "Info", date, periodsInDays, transport);
        client.addDestination(dest);
        clientName = client.getClientName();
        destinationName = destination;
        email = client.getEmail();
        clientRepository.save(client);
        confirmed=true;
        return true;
    }

    @Override
    public String confirmForm() {
        if (!confirmed)
            return "Bitte Eingabe prüfen!!!";
        else {
            confirmationText = clientName+", "+"Sie haben sich für die Reise nach "+destinationName+" erfolgreich angemeldet!";
            return confirmationText;
        }
    }

    @Override
    public void sendEmailConfirm() {
        //if (confirmed)
            // TO DO Confirmation Email an Benutzer schicken
    }
}
