package cloud.chrubasik.ordersprocessing.customer.jpa;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import cloud.chrubasik.ordersprocessing.customer.model.Customer;
import cloud.chrubasik.ordersprocessing.customer.CustomerDao;
import cloud.chrubasik.ordersprocessing.customer.CustomerNotFoundException;
import cloud.chrubasik.ordersprocessing.customer.CustomerService;

/**
 * service interacts with a jpa repository and provides entity model conversion
 */
@Service
public class CustomerJpaService implements CustomerService {

    @Autowired
    private final CustomerDao customerDao;

    public CustomerJpaService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Set<EntityModel<Customer>> performList() {
        return customerDao.getAllCustomers();
    }

    @Override
    public EntityModel<Customer> performDetail(Long id) throws CustomerNotFoundException {
        return customerDao.getCustomerById(id);
    }

}
