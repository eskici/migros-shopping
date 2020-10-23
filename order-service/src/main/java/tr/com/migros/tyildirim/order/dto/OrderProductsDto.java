package tr.com.migros.tyildirim.order.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Taner YILDIRIM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductsDto {

    private Long id;

    private BigDecimal price;

    private Long quantity;

    private Long productId;

    private String productName;
}
