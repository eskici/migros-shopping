package tr.com.migros.tyildirim.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Taner YILDIRIM
 */
@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
}
