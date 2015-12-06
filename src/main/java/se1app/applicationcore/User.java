package se1app.applicationcore;


//import com.google.common.base.Preconditions;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import se1app.applicationcore.complexDataTyp.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by talal on 04.12.15.
 */
/*@TypeDef(
        name = "email",
        defaultForType = Email.class,
        typeClass = Email.class
)*/

@Entity
public class User {
    private String userName;
    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    //@Basic(optional = true)
    //@Autowired
    //@Column(name = "EMAIL")
    //@Type(type="email")
    @Embedded
    private Email email;
    // Technische ID der Entitaet (Auto-generiert)
    @Id
    @GeneratedValue
    private Integer id;
    // Definion *:* Beziehung
    // Kasadoerende Operationen
    // Name der Fremdschlüßel für die Verwendung in der Query
    // fineby in der jeweiligen Repository
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<TripDestination> destinations = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Friend> friends = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Calender calender;


    public User(String userName, Gender gender, Integer age, Email email) {
        //Preconditions.checkNotNull(userName);
        //Preconditions.checkNotNull(age);
        this.gender = gender;
        this.userName = userName;
        this.age = age;
        this.email = email;
    }

    public void addCalender(Calender calender) {
        //Preconditions.checkNotNull(calender);
        this.calender = calender;
    }
    public void addDestination(TripDestination destination) {
        //Preconditions.checkNotNull(destination);
        this.destinations.add(destination);
    }

    public void addFriend(Friend friend) {
        //Preconditions.checkNotNull(friend);
        this.friends.add(friend);
    }
    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        if (!gender.equals(user.gender)) return false;
        if (!age.equals(user.age)) return false;
        if (!email.equals(user.email)) return false;
        if (!id.equals(user.id)) return false;
        if (!destinations.equals(user.destinations)) return false;
        return friends.equals(user.friends);

    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + destinations.hashCode();
        result = 31 * result + friends.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email=" + email +
                ", id=" + id +
                ", destinations=" + destinations +
                ", friends=" + friends +
                '}';
    }
}
