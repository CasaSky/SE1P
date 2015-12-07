package se1app.applicationcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se1app.applicationcore.Calender;
import se1app.applicationcore.Client;

import java.util.Date;
import java.util.Optional;

/**
 * Created by talal on 06.12.15.
 */
@Repository
public interface CalenderRepository extends JpaRepository<Calender, Integer> {

    Optional<Calender> findByTripDate(Date tripDate);

    Optional<Calender> findByClient(Client client);
}
