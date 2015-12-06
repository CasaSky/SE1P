package se1app.applicationcore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by talal on 05.12.15.
 */
public interface TripDestinationRepository extends JpaRepository<TripDestination, Integer> {

    // Spring leitet die Query aus der Signatur ab
    Optional<TripDestination> findByDate(Date date);

}
