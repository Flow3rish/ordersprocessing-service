package cloud.chrubasik.ordersprocessing.customer;

import java.util.List;

import org.springframework.hateoas.EntityModel;

public interface CustomerService {
    public List<EntityModel<Customer>> performList();
    public Customer performDetail(Long id) throws CustomerNotFoundException;
}
