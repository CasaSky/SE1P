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
public class Destination {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="DESTINATIONNAME")
    private String destinationName;
    @Column(name="DESTINATIONINFO")
    private String destinationInfo;
    @Column(name="DATE")
    private Date date;
    @Column(name="PERSIODINDAYS")
    private Integer periodInDays;
    @Column(name="TRANSPORT")
    private String transport;

    public List<Client> getClients() {
        return clients;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @ManyToMany(mappedBy="destinations")
    private List<Client> clients = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="destination_id")
    private List<Activity> activities = new ArrayList<>();

    public Destination(String destinationName, String destinationInfo, Date date, Integer periodInDays, String transport) {
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
        if (!(o instanceof Destination)) return false;

        Destination that = (Destination) o;

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
        return "Destination{" +
                "id=" + id +
                ", destinationName='" + destinationName + '\'' +
                ", destinationInfo='" + destinationInfo + '\'' +
                ", date=" + date +
                ", periodInDays=" + periodInDays +
                ", transport='" + transport + '\'' +
                '}';
    }
}
