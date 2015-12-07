package se1app.applicationcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se1app.applicationcore.Destination;
import se1app.applicationcore.Client;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by talal on 05.12.15.
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    // Spring leitet die Query aus der Signatur ab
    Optional<Destination> findByDate(Date date);

    List<Destination>findByClient(Client client);

    @Query(value = "SELECT * FROM DESTINATION WHERE EXISTS (SELECT * FROM ACTIVITY WHERE ACTIVITY.DESTINATION_ID=DESTINATION.ID AND ACTIVITY.DATE=:dateActivity)", nativeQuery = true)
    Optional<Destination> findDestinationByDate(@Param("dateActivity") Date dateActivity);

}
