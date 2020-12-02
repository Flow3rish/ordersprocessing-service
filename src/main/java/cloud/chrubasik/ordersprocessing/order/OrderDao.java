package cloud.chrubasik.ordersprocessing.order;

import java.util.Set;

import org.springframework.hateoas.EntityModel;

import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderComposite;
import cloud.chrubasik.ordersprocessing.order.model.OrderNotFoundException;

/**
 * the dao has access to models and repository
 */
public interface OrderDao {

    public Set<EntityModel<Order>> listAllOrdersByCustomerId(Long customerId);

    public EntityModel<Order> createOrder(OrderComposite order);

    public EntityModel<Order> getOrderById(Long id) throws OrderNotFoundException;

    public EntityModel<Order> deleteOrderById(Long id) throws OrderNotFoundException;

    public Order getRawOrderById(Long id) throws OrderNotFoundException;

}
