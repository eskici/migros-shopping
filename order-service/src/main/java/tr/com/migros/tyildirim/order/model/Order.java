package tr.com.migros.tyildirim.order.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taner YILDIRIM
 */
@Entity
@Table(name = "ORDERZ")
@Data
@ToString(exclude = "productsOfOrder")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name="ORDER_DATE")
    private LocalDateTime orderDate;

    @Column(name="CUSTOMER_ID")
    private Long customerId;

    @Column(name="CUSTOMER_NAME")
    private String customerName;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderProducts> productsOfOrder = new ArrayList<>();
}
