package tr.com.migros.tyildirim.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Taner YILDIRIM
 */
@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
}
