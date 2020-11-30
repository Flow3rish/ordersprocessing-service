package cloud.chrubasik.ordersprocessing.order;

import java.util.List;

import org.springframework.hateoas.EntityModel;

public interface OrderService {
    public List<EntityModel<Order>> performList();

    public EntityModel<Order> performDetail(Long id) throws OrderNotFoundException;

    public EntityModel<Order> performCreate(Order order); // TODO implement create

}
