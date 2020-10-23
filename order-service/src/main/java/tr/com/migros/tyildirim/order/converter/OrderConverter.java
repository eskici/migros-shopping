package tr.com.migros.tyildirim.order.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tr.com.migros.tyildirim.order.dto.CustomerDto;
import tr.com.migros.tyildirim.order.dto.OrderDto;
import tr.com.migros.tyildirim.order.dto.OrderProductsDto;
import tr.com.migros.tyildirim.order.exception.BusinessException;
import tr.com.migros.tyildirim.order.model.Order;
import tr.com.migros.tyildirim.order.model.OrderProducts;
import tr.com.migros.tyildirim.order.restclient.CustomerServiceProxy;

/**
 * @author Taner YILDIRIM
 */
@Component
@Slf4j
public class OrderConverter {

    @Autowired
    private CustomerServiceProxy customerService;

    @Autowired
    private OrderProductsConverter productsConverter;

    public Order convertToEntity(OrderDto orderDto) throws BusinessException {
        Order orderEntity = new Order();
        orderEntity.setOrderDate(orderDto.getOrderDate());
        orderEntity.setCustomerName(getCustomerName(orderDto.getCustomerId()));
        orderEntity.setCustomerId(orderDto.getCustomerId());

        orderDto.getProductOfOrders().forEach(poDto -> {
            try {
                OrderProducts poEntity = productsConverter.convertToEntity(poDto);
                poEntity.setOrder(orderEntity);
                orderEntity.getProductsOfOrder().add(poEntity);
            } catch (BusinessException e) {
                log.error("An error occured ", e);
            }
        });

        return orderEntity;
    }

    public OrderDto convertToDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDate(entity.getOrderDate());
        orderDto.setCustomerId(entity.getCustomerId());
        orderDto.setCustomerName(entity.getCustomerName());
        orderDto.setId(entity.getId());

        entity.getProductsOfOrder().forEach(poEntity -> {
            OrderProductsDto orderProductsDto = productsConverter.convertToDto(poEntity);
            orderDto.getProductOfOrders().add(orderProductsDto);
        });

        return orderDto;
    }

    private String getCustomerName(Long customerId) throws BusinessException {
        ResponseEntity<CustomerDto> customerResponseEntity = customerService.get(customerId);
        CustomerDto customerDto = customerResponseEntity.getBody();

        if(customerDto == null)
            throw new BusinessException("Müşteri bulunamadı");

        return customerDto.getName();
    }
}
