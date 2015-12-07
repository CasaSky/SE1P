package se1app.applicationcore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import se1app.applicationcore.complexDataTyp.Email;
import se1app.applicationcore.complexDataTyp.Gender;
import se1app.applicationcore.repositories.ClientRepository;
import se1app.applicationcore.repositories.CustomerRepository;
import se1app.applicationcore.repositories.FriendRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by talal on 06.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class FriendRepositoryTest {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void setup() {
        Client talal = new Client("Talal", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
        talal.addFriend(new Friend("Lizzy"));
        clientRepository.save(talal);

        Client kyo = new Client("Kyo", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
        kyo.addFriend(new Friend("Lizzy"));
        clientRepository.save(kyo);
    }

    @Test
    public void testFiendByFriendName(){
        List<Friend> friends = friendRepository.findByFriendName("Lizzy");
        assertThat(friends).hasSize(2);
    }

    @Test
    public void testFindFriendsByClients(){
        Client talal = clientRepository.findByClientName("Talal").get();
        List<Client> clients = new ArrayList<>();
        clients.add(talal);
        List<Friend> friends = friendRepository.findByClients(clients);
        assertThat(friends).hasSize(1);
        assertThat(friends).extracting(reservation -> reservation.getFriendName())
               .contains("Lizzy");
    }
}
