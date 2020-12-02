package cloud.chrubasik.ordersprocessing.order.jpa;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cloud.chrubasik.ordersprocessing.order.OrderDao;
import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderComposite;
import cloud.chrubasik.ordersprocessing.order.model.OrderNotFoundException;

@Component
public class OrderJpaDao implements OrderDao {

    @Autowired
    private final OrderJpaRepository orderRepository;

    @Autowired
    private final OrderModelAssembler assembler;

    public OrderJpaDao(OrderJpaRepository orderRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @Override
    public Set<EntityModel<Order>> listAllOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(assembler::toModel)
                .collect(Collectors.toSet());
    }

    @Override
    public EntityModel<Order> createOrder(OrderComposite orderComposite) {
        Order order = (Order) orderComposite.getData();
        return assembler.toModel(orderRepository.save(order));
    }

    @Override
    public EntityModel<Order> getOrderById(Long id) throws OrderNotFoundException {
        return assembler.toModel(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Override
    public Order getRawOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public EntityModel<Order> deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.deleteById(id);
        return assembler.toModel(order);
    }

}
