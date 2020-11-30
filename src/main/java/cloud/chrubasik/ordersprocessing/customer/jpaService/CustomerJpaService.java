package cloud.chrubasik.ordersprocessing.customer.jpaService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import cloud.chrubasik.ordersprocessing.customer.Customer;
import cloud.chrubasik.ordersprocessing.customer.CustomerNotFoundException;
import cloud.chrubasik.ordersprocessing.customer.CustomerService;

/**
 * service interacts with a jpa repository and provides entity model conversion
 */
@Service
public class CustomerJpaService implements CustomerService {

    @Autowired
    private final CustomerJpaRepository repository;
    @Autowired
    private final CustomerModelAssembler assembler;

    CustomerJpaService(CustomerJpaRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public List<EntityModel<Customer>> performList() {
        return repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
    }

    @Override
    public EntityModel<Customer> performDetail(Long id) throws CustomerNotFoundException {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

}
