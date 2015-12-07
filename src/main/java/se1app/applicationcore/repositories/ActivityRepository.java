package se1app.applicationcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se1app.applicationcore.Activity;
import se1app.applicationcore.Destination;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by talal on 06.12.15.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByDate(Date date);
    List<Activity> findByPeriodInDays(Integer periodInDays);
    List<Activity> findByDestination(Destination destination);

}
