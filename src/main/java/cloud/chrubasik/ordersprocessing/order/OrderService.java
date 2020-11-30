package cloud.chrubasik.ordersprocessing.order;

import java.util.Set;

import org.springframework.hateoas.EntityModel;
import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderNotFoundException;
import cloud.chrubasik.ordersprocessing.order.model.OrderToPost;

public interface OrderService {
    public Set<EntityModel<Order>> performListSetForCustomer(Long customerId);

    public EntityModel<Order> performDetail(Long id) throws OrderNotFoundException;

    public EntityModel<Order> performCreate(Order order);

    public EntityModel<Order> performCreate(OrderToPost order, Long customerId);

}
