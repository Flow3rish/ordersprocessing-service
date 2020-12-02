package cloud.chrubasik.ordersprocessing.customer.jpa;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cloud.chrubasik.ordersprocessing.customer.CustomerDao;
import cloud.chrubasik.ordersprocessing.customer.CustomerNotFoundException;
import cloud.chrubasik.ordersprocessing.customer.model.Customer;

@Component
public class CustomerJpaDao implements CustomerDao {

    private final CustomerJpaRepository repository;

    private final CustomerModelAssembler assembler;

    public CustomerJpaDao(CustomerJpaRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public Set<EntityModel<Customer>> getAllCustomers() {
        return repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toSet());
    }

    @Override
    public EntityModel<Customer> getCustomerById(Long id) throws CustomerNotFoundException {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Override
    public Customer getRawCustomerById(Long id) throws CustomerNotFoundException {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

}
