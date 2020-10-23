package tr.com.migros.tyildirim.order.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tr.com.migros.tyildirim.order.dto.ProductDto;

/**
 * @author Taner YILDIRIM
 */
@FeignClient(name = "product-service")
public interface ProductServiceProxy {

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long id);
}
