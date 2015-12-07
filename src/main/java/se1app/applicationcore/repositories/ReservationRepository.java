package se1app.applicationcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se1app.applicationcore.Customer;
import se1app.applicationcore.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Spring leitet die Query aus der Signatur ab
    List<Reservation> findByMovie(String movie);

    // Spring leitet die Query aus der Signatur ab; hier übergreifend mit referenzierter Entität 'Customer'
    List<Reservation> findByCustomer(Customer customer);
}