package se1app.applicationcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se1app.applicationcore.Client;
import se1app.applicationcore.complexDataTyp.Email;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by talal on 05.12.15.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Spring leitet die Query aus der Signatur ab
    Optional<Client> findByClientName(String clientName);
    Optional<Client> findByEmail(Email email);

    // Hier definieren wir eine eigene SQL-Query (native)
    @Query(value = "SELECT * FROM CLIENT WHERE EXISTS (SELECT * FROM DESTINATION WHERE DESTINATION.CLIENT_ID=CLIENT.ID AND DESTINATION.DESTINATIONNAME=:destinationName)", nativeQuery = true)
    List<Client> findClientsByDestinationName(@Param("destinationName") String destinationName);

    // Hier definieren wir eine eigene SQL-Query (native)
    @Query(value = "SELECT * FROM CLIENT WHERE EXISTS (SELECT * FROM FRIEND WHERE FRIEND.CLIENT_ID=CLIENT.ID AND FRIEND.FriendName=:friendName)", nativeQuery = true)
    List<Client> findClientsByFriendName(@Param("friendName") String friendName);

    @Query(value = "SELECT * FROM CLIENT WHERE EXISTS (SELECT * FROM CALENDER WHERE CALENDER.CLIENT_ID=CLIENT.ID AND CALENDER.CALENDERNR=:calenderNr)", nativeQuery = true)
    List<Client> findClientsByCalenderNr(@Param("calenderNr") Integer calenderNr);


    //finde den Benutzer aus Calender trip date
    @Query(value = "SELECT * FROM CLIENT WHERE EXISTS (SELECT * FROM CALENDER WHERE CALENDER.CLIENT_ID=CLIENT.ID AND CALENDER.TRIPDATE=:tripDate)", nativeQuery = true)
    Optional<Client> findClientByTripDate(@Param("tripDate") Integer tripDate);

    // Finde alle Reisenden, die an diesem Datum @param Date verreisen
    @Query(value = "SELECT * FROM CLIENT WHERE EXISTS (SELECT * FROM DESTINATION WHERE DESTINATION.CLIENT_ID=CLIENT.ID AND DESTINATION.DATE=:date)", nativeQuery = true)
    List<Client> findClientsByDate(@Param("date") Date Date);

}
