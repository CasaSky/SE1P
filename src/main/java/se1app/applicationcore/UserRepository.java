package se1app.applicationcore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by talal on 05.12.15.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Spring leitet die Query aus der Signatur ab
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(Email email);

    // Hier definieren wir eine eigene SQL-Query (native)
    @Query(value = "SELECT * FROM USER WHERE EXISTS (SELECT * FROM TripDestination WHERE TripDestination.USER_ID=USER.ID AND TripDestination.DestinationName=:destinationName)", nativeQuery = true)
    List<User> findUsersByDestinationName(@Param("destinationName") String destinationName);
}
