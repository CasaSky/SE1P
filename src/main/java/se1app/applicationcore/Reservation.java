package se1app.applicationcore;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Integer id;

    private String movie;

    @ManyToOne
    private Customer customer;

    public Reservation() {}

    public Reservation(String movie)
    {
        this.movie = movie;
    }

    public Integer getId() {
        return id;
    }

    public String getMovie() {
        return movie;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Reservation){
            Reservation toCompare = (Reservation) o;
            return this.id.equals(toCompare.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format(
                "Reservation[id=%d, movie='%s']",
                id, movie);
    }
}