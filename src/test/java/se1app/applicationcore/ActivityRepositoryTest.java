package se1app.applicationcore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import se1app.applicationcore.complexDataTyp.Email;
import se1app.applicationcore.complexDataTyp.Gender;
import se1app.applicationcore.repositories.*;

import java.util.ArrayList;
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
public class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private DestinationRepository destinationRepository;

    @Before
    public void setup() {
        Destination casablanca = new Destination("Casablanca", "Sommer", new Date(2016, 06, 01), 20, "Flugzeug");
        casablanca.addActivity(new Activity(new Date(2016, 06, 01), "Beach", 1));
        destinationRepository.save(casablanca);

        Destination marrakesch = new Destination("Marrakesch", "Sommer", new Date(2016, 06, 02), 20, "Flugzeug");
        marrakesch.addActivity(new Activity(new Date(2016, 06, 01), "Beach", 1));
        destinationRepository.save(marrakesch);
    }

    @Test
    public void testFindByDate(){
        List<Activity> activities = activityRepository.findByDate(new Date(2016, 06, 01));
        assertThat(activities).hasSize(2);
    }




    @Test
    public void testFindByPeriodInDays(){
        List<Activity> activities = activityRepository.findByPeriodInDays(1);
        assertThat(activities).hasSize(2);
    }

    @Test
    public void testFindByDestination(){
        List<Destination> destinations = destinationRepository.findByDestinationName("Casablanca");
        List<List<Activity>> activities = new ArrayList<>();
        for (Destination destination : destinations) {
            activities.add(activityRepository.findByDestination(destination));
        }
        assertThat(activities).hasSize(1);
    }
}
