package tr.com.migros.tyildirim.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taner YILDIRIM
 */
@Getter
@Setter
public class OrderDto {

    private Long id;

    @Positive(message = "Müşteri no sıfırdan büyük olmalıdır !")
    @NotNull
    private Long customerId;

    private String customerName;

    @NotNull
    @Future(message = "Siparişiniz ileri tarihli olmalıdır !")
    private LocalDateTime orderDate;

    @NotNull
    @NotEmpty(message = "Sepetinizde en az 1 adet ürün olmalıdır !")
    private List<OrderProductsDto> productOfOrders = new ArrayList<>();

}
