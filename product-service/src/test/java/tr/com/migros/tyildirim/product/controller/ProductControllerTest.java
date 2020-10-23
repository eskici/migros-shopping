package tr.com.migros.tyildirim.product.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.migros.tyildirim.product.ProductServiceApplication;
import tr.com.migros.tyildirim.product.dto.Product;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taner YILDIRIM
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerTest {

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
    public void testGetProductById() {
        Product product = restTemplate.getForObject(getRootUrl() + "/product/1", Product.class);
        System.out.println(product.getName());
        assertNotNull(product);
        assertNotNull(product.getName());
    }
}
