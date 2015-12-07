package se1app.applicationcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se1app.applicationcore.Client;
import se1app.applicationcore.Friend;

import java.util.List;
import java.util.Optional;

/**
 * Created by talal on 06.12.15.
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{
    List<Friend> findByFriendName(String friendName);

    List<Friend> findByClients(List<Client> client);
}
