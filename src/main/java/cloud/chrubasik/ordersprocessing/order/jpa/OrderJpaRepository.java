package cloud.chrubasik.ordersprocessing.order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.chrubasik.ordersprocessing.order.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    
}
