package cloud.chrubasik.ordersprocessing.customer.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud.chrubasik.ordersprocessing.customer.model.Customer;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

}
