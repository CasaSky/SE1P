package se1app.applicationcore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

/**
 * Created by talal on 05.12.15.
 */
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    // Spring leitet die Query aus der Signatur ab
    Optional<Destination> findByDate(Date date);

}
