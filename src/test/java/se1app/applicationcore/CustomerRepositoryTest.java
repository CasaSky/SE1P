package se1app.applicationcore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Before
	public void setup() {
		Customer stefan = new Customer("Stefan");
		stefan.addReservation(new Reservation("Spectre"));
		stefan.addReservation(new Reservation("Goldfinger"));
		// Kaskadierendes Speichern der Reservierungen durch entsprechende 'Cascade'-Angabe in Customer!
		customerRepository.save(stefan);

		Customer ina = new Customer("Ina");
		ina.addReservation(new Reservation("Spectre"));
		customerRepository.save(ina);

		Customer michel = new Customer("Michael");
		michel.addReservation(new Reservation("Minions"));
		customerRepository.save(michel);
	}

	@Test
	public void testFindAll(){
		List<Customer> customers = customerRepository.findAll();
		assertThat(customers).hasSize(3);
	}

	@Test
	public void testFindByName(){
		Optional<Customer> customer = customerRepository.findByName("Stefan");
		assertThat(customer.isPresent());
		assertThat(customer.get().getName().equals("Stefan"));
		assertThat(customer.get().getReservations()).hasSize(2);
	}

	@Test
	public void testFindByMovie(){
		List<Customer> customers = customerRepository.findCustomersByMovie("Spectre");
		assertThat(customers).hasSize(2);

		// Vergleich aufgrund der (hier technischen - besser fachlichen) IDs der Entitäten
		// Die Customer-Objekte sind unterschiedlich, obwohl sie denselben Customer darstellen!
		Customer stefan =  customerRepository.findByName("Stefan").get();
		Customer ina =  customerRepository.findByName("Ina").get();
		assertThat(customers).extracting(customer -> customer.getId())
				.contains(stefan.getId(), ina.getId());

		// Alternativ, da equals der Entitäten entsprechend definiert
		assertThat(customers).contains(
				customerRepository.findByName("Stefan").get(),
				customerRepository.findByName("Ina").get());
	}
}
