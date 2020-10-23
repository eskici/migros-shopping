package tr.com.migros.tyildirim.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.migros.tyildirim.order.converter.OrderConverter;
import tr.com.migros.tyildirim.order.converter.OrderProductsConverter;
import tr.com.migros.tyildirim.order.dto.OrderDto;
import tr.com.migros.tyildirim.order.dto.UpdateOrderDto;
import tr.com.migros.tyildirim.order.exception.BusinessException;
import tr.com.migros.tyildirim.order.model.Order;
import tr.com.migros.tyildirim.order.model.OrderProducts;
import tr.com.migros.tyildirim.order.repository.OrderRepository;

/**
 * @author Taner YILDIRIM
 */
@Service
public class OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderConverter converter;

    @Autowired
    private OrderProductsConverter productsConverter;

    public OrderDto create(OrderDto orderDto) throws BusinessException {
        Order order = converter.convertToEntity(orderDto);
        order = repository.save(order);
        return converter.convertToDto(order);
    }

    public OrderDto read(Long id) throws BusinessException {
        Order order = findById(id);
        return converter.convertToDto(order);
    }

    public void update(Long id, UpdateOrderDto orderDto) throws BusinessException {
        Order updateOrder = findById(id);

        orderDto.getProductOfOrders().forEach( orderProductsDto -> {
            try {
                OrderProducts orderProducts = productsConverter.convertToEntity(orderProductsDto);
                orderProducts.setOrder(updateOrder);
                updateOrder.getProductsOfOrder().add(orderProducts);
            } catch (BusinessException e) {
                log.error("An error occured : ", e);
            }
        });
        repository.save(updateOrder);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Order findById(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Order not found"));
    }
}
