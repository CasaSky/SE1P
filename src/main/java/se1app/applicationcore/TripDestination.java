package se1app.applicationcore;

//import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by talal on 04.12.15.
 */
@Entity
public class TripDestination {
    @Id
    @GeneratedValue
    private Integer id;
    private String destinationName;
    private String destinationInfo;
    private Date date;
    private Integer periodInDays;
    private String transport;
    @ManyToMany
    private List<User> user = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="tripDestination_id")
    private List<Activity> activities = new ArrayList<>();

    public TripDestination(String destinationName, String destinationInfo, Date date, Integer periodInDays, String transport) {
        /*Preconditions.checkNotNull(destinationName);
        Preconditions.checkNotNull(destinationInfo);
        Preconditions.checkNotNull(date);
        Preconditions.checkNotNull(periodInDays);
        Preconditions.checkNotNull(transport);*/
        this.destinationName = destinationName;
        this.destinationInfo = destinationInfo;
        this.date = date;
        this.periodInDays = periodInDays;
        this.transport = transport;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
    public Integer getId() {
        return id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public Date getDate() {
        return date;
    }

    public String getDestinationInfo() {
        return destinationInfo;
    }

    public Integer getPeriodInDays() {
        return periodInDays;
    }

    public String getTransport() {
        return transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripDestination)) return false;

        TripDestination that = (TripDestination) o;

        if (!id.equals(that.id)) return false;
        if (!destinationName.equals(that.destinationName)) return false;
        if (!destinationInfo.equals(that.destinationInfo)) return false;
        if (!date.equals(that.date)) return false;
        if (!periodInDays.equals(that.periodInDays)) return false;
        return transport.equals(that.transport);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + destinationName.hashCode();
        result = 31 * result + destinationInfo.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + periodInDays.hashCode();
        result = 31 * result + transport.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TripDestination{" +
                "id=" + id +
                ", destinationName='" + destinationName + '\'' +
                ", destinationInfo='" + destinationInfo + '\'' +
                ", date=" + date +
                ", periodInDays=" + periodInDays +
                ", transport='" + transport + '\'' +
                '}';
    }
}
