package tr.com.migros.tyildirim.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.migros.tyildirim.product.dto.Product;

/**
 * @author Taner YILDIRIM
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product product = new Product(id , "Beyaz Peynir");
        return ResponseEntity.ok(product);
    }
}
