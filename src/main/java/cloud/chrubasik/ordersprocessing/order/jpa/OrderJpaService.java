package cloud.chrubasik.ordersprocessing.order.jpa;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderComposite;
import cloud.chrubasik.ordersprocessing.order.model.OrderNotFoundException;
import cloud.chrubasik.ordersprocessing.order.model.OrderToPost;
import cloud.chrubasik.ordersprocessing.customer.model.Customer;
import cloud.chrubasik.ordersprocessing.customer.CustomerDao;
import cloud.chrubasik.ordersprocessing.order.OrderDao;
import cloud.chrubasik.ordersprocessing.order.OrderService;

class OrderJpaServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6187567676325724185L;

    public OrderJpaServiceException(String s) {
        super(s);
    }
}

@Service
public class OrderJpaService implements OrderService {

    @Autowired
    private final OrderDao orderDao;

    @Autowired
    private final CustomerDao customerDao;


    public OrderJpaService(OrderDao orderDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }
    

    @Override
    public Set<EntityModel<Order>> performListSetForCustomer(Long customerId) {
        return orderDao.listAllOrdersByCustomerId(customerId);

    }

    @Override
    public EntityModel<Order> performDetail(Long id) throws OrderNotFoundException {
        return orderDao.getOrderById(id);
    }


    @Override
    public EntityModel<Order> performCreate(OrderToPost orderToPost, Long customerId) {
        Customer customer = customerDao.getRawCustomerById(customerId);
        return orderDao.createOrder(new OrderComposite().data(orderToPost, customer));
    }

    @Override
    public EntityModel<Order> performDelete(Long orderId, Long customerId) {
        Order order = orderDao.getRawOrderById(orderId);
        if (order.getCustomer().getId() != customerId) { // check if customer owns this order
            throw new OrderJpaServiceException(String.format("Customer %d doesn't own order %d", customerId, orderId));
        }
        return orderDao.deleteOrderById(orderId);
    }
}
