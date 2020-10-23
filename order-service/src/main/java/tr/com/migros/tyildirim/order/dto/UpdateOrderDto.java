package tr.com.migros.tyildirim.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taner YILDIRIM
 */
@Getter
@Setter
public class UpdateOrderDto {

    @NotNull
    @NotEmpty(message = "Sepetinizde en az 1 adet ürün olmalıdır !")
    private List<OrderProductsDto> productOfOrders = new ArrayList<>();

}
