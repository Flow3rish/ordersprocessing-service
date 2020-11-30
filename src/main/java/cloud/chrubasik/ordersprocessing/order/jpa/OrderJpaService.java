package cloud.chrubasik.ordersprocessing.order.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import cloud.chrubasik.ordersprocessing.order.Order;
import cloud.chrubasik.ordersprocessing.order.OrderNotFoundException;
import cloud.chrubasik.ordersprocessing.order.OrderService;

@Service
public class OrderJpaService implements OrderService {

    @Autowired
    private final OrderJpaRepository repository;

    @Autowired
    private final OrderModelAssembler assembler;

    public OrderJpaService(OrderJpaRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
    public List<EntityModel<Order>> performList() {
        return repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    }

    @Override
    public EntityModel<Order> performDetail(Long id) throws OrderNotFoundException {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Override
    public EntityModel<Order> performCreate(Order order) {
        return assembler.toModel(order);
    }
}
