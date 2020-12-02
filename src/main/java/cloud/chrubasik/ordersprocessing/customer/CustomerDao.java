package cloud.chrubasik.ordersprocessing.customer;

import java.util.Set;

import org.springframework.hateoas.EntityModel;

import cloud.chrubasik.ordersprocessing.customer.model.Customer;

public interface CustomerDao {
    public Set<EntityModel<Customer>> getAllCustomers();

    public EntityModel<Customer> getCustomerById(Long id) throws CustomerNotFoundException;

    public Customer getRawCustomerById(Long id) throws CustomerNotFoundException;
}
