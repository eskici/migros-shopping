package tr.com.migros.tyildirim.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.migros.tyildirim.order.model.Order;

/**
 * @author Taner YILDIRIM
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
