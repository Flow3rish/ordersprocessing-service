package cloud.chrubasik.ordersprocessing.customer;

import java.util.Set;

import org.springframework.hateoas.EntityModel;

public interface CustomerService {
    public Set<EntityModel<Customer>> performList();

    public EntityModel<Customer> performDetail(Long id) throws CustomerNotFoundException;
}
