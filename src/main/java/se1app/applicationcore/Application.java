package se1app.applicationcore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se1app.applicationcore.repositories.CustomerRepository;


import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner init(CustomerRepository costumerRepository) {
       /* return args -> {
            Client talal = new Client("Talal", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
            Client lizzy = new Client("Elizabeth", Gender.FEMALE, 25, Email.getEmail("lizzy@gmail.com"));
            clientRepository.save(talal);
            clientRepository.save(lizzy);
        };*/
        return (evt) -> Arrays.asList(
                "mueller,meier,schulze".split(","))
                .forEach(
                        a -> {
                            costumerRepository.save(new Customer(a));
                        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
