package tr.com.migros.tyildirim.order.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import tr.com.migros.tyildirim.order.OrderServiceApplication;
import tr.com.migros.tyildirim.order.dto.OrderDto;
import tr.com.migros.tyildirim.order.dto.OrderProductsDto;
import tr.com.migros.tyildirim.order.dto.UpdateOrderDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taner YILDIRIM
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderControllerTest {

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
    public void testGetOrderById() {
        int orderId = 1;
        OrderDto order = restTemplate.getForObject(getRootUrl() + "/order/" + orderId, OrderDto.class);
        System.out.println(order.getCustomerName());
        assertNotNull(order.getId());
    }

    @Test
    public void testCreateOrder() {
        OrderDto createOrderRequest = new OrderDto();
        createOrderRequest.setCustomerId(1L);
        createOrderRequest.setOrderDate(LocalDateTime.now().plusHours(3));

        OrderProductsDto orderProductsRequest = new OrderProductsDto();
        orderProductsRequest.setProductId(1L);
        orderProductsRequest.setQuantity(2L);
        orderProductsRequest.setPrice(new BigDecimal(3));

        createOrderRequest.getProductOfOrders().add(orderProductsRequest);
        ResponseEntity<OrderDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/order/create/", createOrderRequest, OrderDto.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertNotNull(postResponse.getBody().getCustomerName());
    }

    @Test
    public void testUpdateOrder() {
        int orderId = 1;

        OrderProductsDto orderProductsRequest = new OrderProductsDto();
        orderProductsRequest.setProductId(1L);
        orderProductsRequest.setQuantity(2L);
        orderProductsRequest.setPrice(new BigDecimal(3));

        UpdateOrderDto updateOrderRequest = new UpdateOrderDto();
        updateOrderRequest.getProductOfOrders().add(orderProductsRequest);

        restTemplate.put(getRootUrl() + "/order/" + orderId, updateOrderRequest);
        OrderDto updatedOrder = restTemplate.getForObject(getRootUrl() + "/order/" + orderId, OrderDto.class);
        assertNotNull(updatedOrder);
    }

    @Test
    public void testDeleteOrder() {
        int orderId = 1;
        OrderDto order = restTemplate.getForObject(getRootUrl() + "/order/" + orderId, OrderDto.class);
        assertNotNull(order);
        restTemplate.delete(getRootUrl() + "/order/" + orderId);
        try {
            order = restTemplate.getForObject(getRootUrl() + "/order/" + orderId, OrderDto.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
