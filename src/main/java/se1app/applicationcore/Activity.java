package se1app.applicationcore;

//import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by talal on 04.12.15.
 */
@Entity
public class Activity {
    @Id
    @GeneratedValue
    private Integer id;
    private Date date;
    private Integer periodInDays;
    private String comments;
    @ManyToOne
    private Destination destination;
    public Activity(Date date, String comments, int periodInDays) {
        //Preconditions.checkNotNull(date);
        //Preconditions.checkNotNull(comments);
        this.date = date;
        this.comments = comments;
        this.periodInDays = periodInDays;
    }

    public String getComments() {
        return comments;
    }

    public int getPeriodInDays() {
        return periodInDays;
    }

    public Date getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;

        Activity that = (Activity) o;

        if (!id.equals(that.id)) return false;
        if (!date.equals(that.date)) return false;
        if (!periodInDays.equals(that.periodInDays)) return false;
        return comments.equals(that.comments);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + periodInDays.hashCode();
        result = 31 * result + comments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", date=" + date +
                ", periodInDays=" + periodInDays +
                ", comments='" + comments + '\'' +
                '}';
    }
}
