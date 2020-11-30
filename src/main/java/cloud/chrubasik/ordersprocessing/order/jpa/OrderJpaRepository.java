package cloud.chrubasik.ordersprocessing.order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud.chrubasik.ordersprocessing.order.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    
}
