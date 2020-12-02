package cloud.chrubasik.ordersprocessing.customer;

import java.util.Set;

import org.springframework.hateoas.EntityModel;

import cloud.chrubasik.ordersprocessing.customer.model.Customer;

public interface CustomerService {
    public Set<EntityModel<Customer>> performList();

    public EntityModel<Customer> performDetail(Long id);
}
