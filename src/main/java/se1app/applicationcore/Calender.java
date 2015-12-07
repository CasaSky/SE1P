package se1app.applicationcore;

//import com.google.common.base.Preconditions;

import java.util.Date;

import javax.persistence.*;

/**
 * Created by talal on 04.12.15.
 */
@Entity
public class Calender {
    @Id
    @GeneratedValue
    private Integer calenderNr;
    private Date tripDate;
    private String tripDetails;
    @OneToOne
    private Client client;

    public Calender(Date tripDate, String tripDetails) {
        //Preconditions.checkNotNull(tripDate);
        //Preconditions.checkNotNull(tripDetails);
        this.tripDate = tripDate;
        this.tripDetails = tripDetails;
    }

    public Integer getCalenderNr() {
        return calenderNr;
    }

    public String getTripDetails() {
        return tripDetails;
    }

    public Date getTripDate() {
        return tripDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Calender)) return false;

        Calender calender = (Calender) o;

        if (!calenderNr.equals(calender.calenderNr)) return false;
        if (!tripDate.equals(calender.tripDate)) return false;
        if (!tripDetails.equals(calender.tripDetails)) return false;
        return client.equals(calender.client);

    }

    @Override
    public int hashCode() {
        int result = calenderNr.hashCode();
        result = 31 * result + tripDate.hashCode();
        result = 31 * result + tripDetails.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Calender{" +
                "calenderNr=" + calenderNr +
                ", tripDate=" + tripDate +
                ", tripDetails='" + tripDetails + '\'' +
                ", client=" + client +
                '}';
    }
}
