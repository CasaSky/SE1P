package se1app.applicationcore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner init(CustomerRepository costumerRepository) {
       /* return args -> {
            User talal = new User("Talal", Gender.MALE, 25, Email.getEmail("talal.tabi@gmail.com"));
            User lizzy = new User("Elizabeth", Gender.FEMALE, 25, Email.getEmail("lizzy@gmail.com"));
            userRepository.save(talal);
            userRepository.save(lizzy);
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
