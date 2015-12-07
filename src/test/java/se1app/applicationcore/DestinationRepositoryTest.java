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
import se1app.applicationcore.repositories.CalenderRepository;
import se1app.applicationcore.repositories.ClientRepository;
import se1app.applicationcore.repositories.DestinationRepository;

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
public class DestinationRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DestinationRepository destinationRepository;


    @Before
    public void setup() {
        Client talal = new Client("Talal", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
        talal.addDestination(new Destination("Casablanca", "Summer", new Date(2016, 06, 01), 20, "Flugzeug"));
        clientRepository.save(talal);

        Client kyo = new Client("Kyo", Gender.MALE, 26, Email.getEmail("lee@googlemail.com"));
        kyo.addDestination(new Destination("Seoul", "Summer", new Date(2016, 8, 21), 10, "Auto"));
        kyo.addDestination(new Destination("Tokyo", "Herbst", new Date(2016, 9, 30), 9, "Schiff"));
        clientRepository.save(kyo);

        Client superman = new Client("supermarkt", Gender.MALE, 35, Email.getEmail("super@superemail.com"));
        superman.addDestination(new Destination("Mars", "Winter", new Date(2222, 9, 22), 100, "Spaceship"));
        clientRepository.save(superman);
    }

    @Test
    public void testFindAll(){
        List<Destination> destination = destinationRepository.findAll();
        assertThat(destination).hasSize(4);
    }

    @Test
    public void testFindbyDate(){
        //Optional<Client> client = clientRepository.findByClientName("kyo");
        List<Destination> destinations = destinationRepository.findByDate(new Date(2016, 8, 21));
        StrictAssertions.assertThat(!destinations.isEmpty());
        StrictAssertions.assertThat(destinations.contains("Seoul"));
    }

    @Test
    public void testFindbyClient(){;
        Optional<Client> kyo = clientRepository.findByClientName("Kyo");
        StrictAssertions.assertThat(kyo.isPresent());
        List<Destination> destination = destinationRepository.findByClients(kyo.get());
        StrictAssertions.assertThat(!destination.isEmpty());
        StrictAssertions.assertThat(destination.contains("Seoul"));
        StrictAssertions.assertThat(destination.contains("Tokyo"));
    }
}