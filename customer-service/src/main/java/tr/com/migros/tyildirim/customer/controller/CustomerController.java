package tr.com.migros.tyildirim.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.migros.tyildirim.customer.dto.Customer;

/**
 * @author Taner YILDIRIM
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Long id) {
        Customer product = new Customer(id , "Taner Yıldırım");
        return ResponseEntity.ok(product);
    }
}
