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
public class CalenderRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CalenderRepository calenderRepository;


    @Before
    public void setup() {
        Client talal = new Client("Talal", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
        talal.addDestination(new Destination("Casablanca", "Summer", new Date(2016, 06, 01), 20, "Flugzeug"));
        clientRepository.save(talal);

        Calender talalCalender = new Calender(new Date(2016, 06, 02), "Sehr gute Bewertung von anderen Reisenden");
        talal.addCalender(talalCalender);
        calenderRepository.save(talalCalender);


        Client kyo = new Client("Kyo", Gender.MALE, 26, Email.getEmail("lee@googlemail.com"));
        kyo.addDestination(new Destination("Seoul", "Summer", new Date(2016, 8, 21), 10, "Auto"));
        clientRepository.save(kyo);

        Calender kyoCalender = new Calender((new Date(2016, 12, 24)), "Diese Reise schenke ich mir für meinen Geburtstag");
        kyo.addCalender(kyoCalender);

        calenderRepository.save(kyoCalender);


        Client superman = new Client("supermarkt", Gender.MALE, 35, Email.getEmail("super@superemail.com"));
        superman.addDestination(new Destination("Mars", "Winter", new Date(2222, 9, 22), 100, "Spaceship"));
        clientRepository.save(superman);

        Calender supermanCalender = new Calender((new Date(2016, 7, 24)), "Fuer die Welt");
        superman.addCalender(supermanCalender);

        calenderRepository.save(supermanCalender);
    }

    @Test
    public void testFindAll(){
        List<Calender> calender = calenderRepository.findAll();
        assertThat(calender).hasSize(3);
    }

    @Test
    public void testFindbyTripDate(){
        //Optional<Client> client = clientRepository.findByClientName("kyo");
        Optional<Calender> calenderNotice = calenderRepository.findByTripDate((new Date(2016, 7, 24)));
        StrictAssertions.assertThat(calenderNotice.isPresent());
        StrictAssertions.assertThat(calenderNotice.get().getTripDetails().equals("Fuer die Welt"));
    }

    /*@Test
    public void testFindbyClient(){;
        Optional<Client> kyo = clientRepository.findByClientName("Kyo");
        StrictAssertions.assertThat(kyo.isPresent());
        Optional<Calender> calender = calenderRepository.findByClient(kyo.get());
        StrictAssertions.assertThat(calender.isPresent());
        StrictAssertions.assertThat(calender.get().getTripDate().equals((new Date(2016, 8, 21))));
    }*/
}