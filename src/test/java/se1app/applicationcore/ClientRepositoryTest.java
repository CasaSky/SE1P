package se1app.applicationcore;

import org.assertj.core.api.StrictAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import se1app.applicationcore.complexDataTyp.Email;
import se1app.applicationcore.complexDataTyp.Gender;
import se1app.applicationcore.repositories.ClientRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by talal on 06.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void setup() {
        Client talal = new Client("Talal", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
        talal.addDestination(new Destination("Casablanca", "Summer", new Date(2016, 06, 01), 20, "Flugzeug"));
        talal.addDestination(new Destination("Marrakesch", "Summer", new Date(2016, 02, 01), 10, "Flugzeug"));
        // Kaskadierendes Speichern der Reservierungen durch entsprechende 'Cascade'-Angabe in Customer!
        clientRepository.save(talal);

        Client lizzy = new Client("Elizabeth", Gender.FEMALE, 25, Email.getEmail("lizzy@gmail.com"));
        lizzy.addDestination(new Destination("Casablanca", "Summer", new Date(2016, 06, 01), 20, "Flugzeug"));
        clientRepository.save(lizzy);
    }

    @Test
    public void testFindAll(){
        List<Client> client = clientRepository.findAll();
        assertThat(client).hasSize(2);
    }

   @Test
    public void testFindByClientName(){
        Optional<Client> client = clientRepository.findByClientName("Talal");
        StrictAssertions.assertThat(client.isPresent()); // Test -> isPresent client has a Value
        StrictAssertions.assertThat(client.get().getClientName().equals("Talal"));
        assertThat(client.get().getDestinations()).hasSize(2);
    }

    @Test
    public void testFindByEmail(){
        Optional<Client> client = clientRepository.findByEmail(Email.getEmail("talal.tabi@gmail.com"));
        StrictAssertions.assertThat(client.isPresent()); // Test -> isPresent client has a Value
        StrictAssertions.assertThat(client.get().getEmail().equals(Email.getEmail("talal.tabi@gmail.com")));
        //assertThat(client.get().getDestinations()).hasSize(2);
    }

    @Test
    public void testFindClientsByDestinationName(){
        List<Client> clients = clientRepository.findClientsByDestinationName("Casablanca");
        StrictAssertions.assertThat(!clients.isEmpty()); // Test -> isPresent client has a Value
        for (Client client : clients)
            StrictAssertions.assertThat(client.getDestinations().contains("Casablanca"));
        assertThat(clients).hasSize(2);
    }

}