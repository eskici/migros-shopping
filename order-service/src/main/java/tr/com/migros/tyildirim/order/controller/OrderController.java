package tr.com.migros.tyildirim.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tr.com.migros.tyildirim.order.dto.OrderDto;
import tr.com.migros.tyildirim.order.dto.UpdateOrderDto;
import tr.com.migros.tyildirim.order.exception.BusinessException;
import tr.com.migros.tyildirim.order.service.OrderService;

import javax.validation.Valid;

/**
 * @author Taner YILDIRIM
 */
@RestController
@RequestMapping(value = "/order")
@Validated
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto createRequest) throws Exception {
       return new ResponseEntity<>(orderService.create(createRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> read(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(orderService.read(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @Valid @RequestBody UpdateOrderDto orderDto) throws BusinessException {
        orderService.update(id, orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
