package tr.com.migros.tyildirim.order.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.migros.tyildirim.order.dto.CustomerDto;

/**
 * @author Taner YILDIRIM
 */
@FeignClient(name = "customer-service")
public interface CustomerServiceProxy {

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id);
}
