package sample;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest extends ApplicationTest {

    @Autowired
    private TestRestTemplate _restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository _customerRepository;

    @Test
    public void sut_should_delete_customer_correctly() throws Exception {
        // Arrange
        //Customer customerId = _customerRepository.save(new Customer("john", "lee"));
        Customer target = this._restTemplate.getForObject(
                "http://localhost:" + String.format("%s", port) + "/customer/add?firstName=john&lastName=lee", Customer.class);

        // Act
        _restTemplate.delete("http://localhost:" + String.format("%s", port) + "/customer/delete?id=" + String.format("%s", target.getId()), String.class);

        // Assert
        Assertions.assertThat(_customerRepository.findById(target.getId())).isEmpty();

    }
}
