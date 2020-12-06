package cloud.chrubasik.ordersprocessing.order.jpa;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderComposite;
import cloud.chrubasik.ordersprocessing.order.model.OrderNotFoundException;
import cloud.chrubasik.ordersprocessing.order.model.OrderToPost;
import cloud.chrubasik.ordersprocessing.customer.model.Customer;
import cloud.chrubasik.ordersprocessing.customer.CustomerDao;
import cloud.chrubasik.ordersprocessing.customer.CustomerNotFoundException;
import cloud.chrubasik.ordersprocessing.order.OrderDao;
import cloud.chrubasik.ordersprocessing.order.OrderService;

class OrderJpaServiceException extends RuntimeException {

    private final String messageDestination;


    /**
     * basic throw
     */
    private static final long serialVersionUID = 6187567676325724185L;

    public OrderJpaServiceException(String s) {
        super(s);
        this.messageDestination = null;
    }

    /**
     * throw and send notification
     * 
     * @param messageDestination
     * @param jmsTemplate
     * @param s
     */
    public OrderJpaServiceException(String messageDestination, JmsTemplate jmsTemplate, String s) {
        super(s);
        this.messageDestination = messageDestination;
        jmsTemplate.convertAndSend(this.messageDestination, s);
    }

}

@Service
public class OrderJpaService implements OrderService {


    @Autowired
    private final OrderDao orderDao;

    @Autowired
    private final CustomerDao customerDao;

    private final String messageDestination;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public OrderJpaService(@Value("${message.destination}") String messageDestination, JmsTemplate jmsTemplate,
            OrderDao orderDao, CustomerDao customerDao) {
        this.messageDestination = messageDestination;
        this.jmsTemplate = jmsTemplate;
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }
    

    @Override
    public Set<EntityModel<Order>> performListSetForCustomer(Long customerId) {
        return orderDao.listAllOrdersByCustomerId(customerId);

    }

    @Override
    public EntityModel<Order> performDetail(Long orderId, Long customerId) throws OrderNotFoundException {
        Order order = orderDao.getRawOrderById(orderId);
        if (order.getCustomer().getId() != customerId) { // check if customer owns this order
            throw new OrderJpaServiceException(String.format("Customer %d doesn't own order %d", customerId, orderId));
        }
        return orderDao.getOrderById(orderId);
    }


    @Override
    public EntityModel<Order> performCreate(OrderToPost orderToPost, Long customerId) {
        try {
            Customer customer = customerDao.getRawCustomerById(customerId);
            this.jmsTemplate.convertAndSend(this.messageDestination, "creating order for customer" + customer);
            EntityModel<Order> createdOrderEm = orderDao.createOrder(new OrderComposite().data(orderToPost, customer));
            this.jmsTemplate.convertAndSend(this.messageDestination,
                    "created order " + createdOrderEm + " for customer" + customer);
            return createdOrderEm;
        } catch (CustomerNotFoundException e) {
            throw new OrderJpaServiceException(this.messageDestination, this.jmsTemplate, String.format("%s", e));
        }
    }

    @Override
    public Order performDelete(Long orderId, Long customerId) {
        try {
            Order order = orderDao.getRawOrderById(orderId);
            Customer customer = customerDao.getRawCustomerById(customerId);
            this.jmsTemplate.convertAndSend(this.messageDestination,
                    "deleting order " + orderId + " for customer" + customer);
            if (order.getCustomer().getId() != customerId) { // check if customer owns this order
                throw new OrderJpaServiceException(this.messageDestination, this.jmsTemplate,
                        String.format("Customer %d doesn't own order %d", customerId, orderId));
            }

            Order deletedOrderEm = orderDao.deleteOrderById(orderId);
            this.jmsTemplate.convertAndSend(this.messageDestination,
                    "deleted order " + orderId + " for customer" + customer);

            return deletedOrderEm;

        } catch (OrderNotFoundException e) {
            throw new OrderJpaServiceException(this.messageDestination, this.jmsTemplate, String.format("%s", e));
        }
    }
}
