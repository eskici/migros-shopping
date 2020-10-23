package tr.com.migros.tyildirim.customer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.migros.tyildirim.customer.CustomerServiceApplication;
import tr.com.migros.tyildirim.customer.dto.Customer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taner YILDIRIM
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetCustomerById() {
        Customer customer = restTemplate.getForObject(getRootUrl() + "/customer/1", Customer.class);
        System.out.println(customer.getName());
        assertNotNull(customer);
        assertNotNull(customer.getName());
    }
}
