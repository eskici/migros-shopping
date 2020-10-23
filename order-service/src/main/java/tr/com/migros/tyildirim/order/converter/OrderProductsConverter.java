package tr.com.migros.tyildirim.order.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tr.com.migros.tyildirim.order.dto.OrderProductsDto;
import tr.com.migros.tyildirim.order.dto.ProductDto;
import tr.com.migros.tyildirim.order.exception.BusinessException;
import tr.com.migros.tyildirim.order.model.OrderProducts;
import tr.com.migros.tyildirim.order.restclient.ProductServiceProxy;

/**
 * @author Taner YILDIRIM
 */
@Component
public class OrderProductsConverter {

    @Autowired
    private ProductServiceProxy productService;

    public OrderProducts convertToEntity(OrderProductsDto orderProductsDto) throws BusinessException {
        OrderProducts productsOfOrder = new OrderProducts();
        productsOfOrder.setPrice(orderProductsDto.getPrice());
        productsOfOrder.setProductId(orderProductsDto.getProductId());
        productsOfOrder.setQuantity(orderProductsDto.getQuantity());
        productsOfOrder.setProductName(findProductName(orderProductsDto.getProductId()));
        return productsOfOrder;
    }

    public OrderProductsDto convertToDto(OrderProducts entity) {
        OrderProductsDto orderProductsDto = new OrderProductsDto();
        orderProductsDto.setId(entity.getId());
        orderProductsDto.setPrice(entity.getPrice());
        orderProductsDto.setProductId(entity.getProductId());
        orderProductsDto.setProductName(entity.getProductName());
        orderProductsDto.setQuantity(entity.getId());
        return orderProductsDto;
    }

    private String findProductName(Long productId) throws BusinessException {
        ResponseEntity<ProductDto> productResponse = productService.get(productId);
        ProductDto productDto = productResponse.getBody();

        if(productDto == null)
            throw new BusinessException("Ürün bulunamadı");

        return productDto.getName();
    }
}
