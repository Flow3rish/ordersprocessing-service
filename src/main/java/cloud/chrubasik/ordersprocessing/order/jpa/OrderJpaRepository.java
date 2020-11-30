package cloud.chrubasik.ordersprocessing.order.jpa;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud.chrubasik.ordersprocessing.order.model.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    // @Query(value = "SELECT * FROM Order o WHERE o.customer_id = :customerId")
    // public Set<Order> findAllForCustomer(@Param("customerId") Long customerId);
    Set<Order> findByCustomerId(Long customerId);
}
