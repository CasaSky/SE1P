package se1app.applicationcore;


//import com.google.common.base.Preconditions;
import se1app.applicationcore.complexDataTyp.Email;
import se1app.applicationcore.complexDataTyp.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by talal on 04.12.15.
 */

/*@TypeDef(
        name = "Email",
        defaultForType = Email.class,
        typeClass = Email.class
)*/
@Entity
public class Client {
    private String clientName;
    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    //@Autowired
    //@Column(name = "EMAIL")
    //@Type(type="Email")
    @Embedded
    //@Basic
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
    @JoinColumn(name = "client_id")
    private List<Destination> destinations = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Friend> friends = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Calender calender;


    public Client(String clientName, Gender gender, Integer age, Email email) {
        //Preconditions.checkNotNull(clientName);
        //Preconditions.checkNotNull(age);
        this.gender = gender;
        this.clientName = clientName;
        this.age = age;
        this.email = email;
    }

    public void addCalender(Calender calender) {
        //Preconditions.checkNotNull(calender);
        this.calender = calender;
    }
    public void addDestination(Destination destination) {
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

    public String getClientName() {
        return clientName;
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

    public List<Destination> getDestinations() { return destinations; }

    public List<Friend> getFriends() { return friends; }

    public Calender getCalender() { return calender; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!clientName.equals(client.clientName)) return false;
        if (!gender.equals(client.gender)) return false;
        if (!age.equals(client.age)) return false;
        if (!email.equals(client.email)) return false;
        if (!id.equals(client.id)) return false;
        if (!destinations.equals(client.destinations)) return false;
        return friends.equals(client.friends);

    }

    @Override
    public int hashCode() {
        int result = clientName.hashCode();
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
        return "Client{" +
                "clientName='" + clientName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email=" + email +
                ", id=" + id +
                ", destinations=" + destinations +
                ", friends=" + friends +
                '}';
    }
}
