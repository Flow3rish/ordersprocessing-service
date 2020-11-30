package cloud.chrubasik.ordersprocessing.order.jpa;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderNotFoundException;
import cloud.chrubasik.ordersprocessing.order.model.OrderToPost;
import cloud.chrubasik.ordersprocessing.customer.Customer;
import cloud.chrubasik.ordersprocessing.customer.CustomerNotFoundException;
import cloud.chrubasik.ordersprocessing.customer.jpaService.CustomerJpaRepository;
import cloud.chrubasik.ordersprocessing.order.OrderService;

@Service
public class OrderJpaService implements OrderService {

    @Autowired
    private final OrderJpaRepository orderRepository;

    @Autowired
    private final CustomerJpaRepository customerRepository;

    @Autowired
    private final OrderModelAssembler assembler;

    public OrderJpaService(OrderJpaRepository orderRepository, CustomerJpaRepository customerRepository,
            OrderModelAssembler assembler) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @Override
    public Set<EntityModel<Order>> performListSetForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(assembler::toModel)
                .collect(Collectors.toSet());

    }

    @Override
    public EntityModel<Order> performDetail(Long id) throws OrderNotFoundException {
        return assembler.toModel(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Override
    public EntityModel<Order> performCreate(Order order) {
        return assembler.toModel(orderRepository.save(order));
    }

    @Override
    public EntityModel<Order> performCreate(OrderToPost orderToPost, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        return assembler.toModel(orderRepository.save(new Order(orderToPost, customer)));
    }
}
