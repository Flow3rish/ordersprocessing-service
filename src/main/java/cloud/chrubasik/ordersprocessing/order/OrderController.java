package cloud.chrubasik.ordersprocessing.order;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.model.OrderToPost;

@RestController
@RequestMapping("customers/{customerId}/orders")
public class OrderController {
    // TODO napsat dokumentaci

    @Autowired
    OrderService service;

    @GetMapping
    public Set<EntityModel<Order>> all(@PathVariable Long customerId) {
        return service.performListSetForCustomer(customerId);
    }

    @PostMapping
    public EntityModel<Order> postOrder(@PathVariable Long customerId,
            @RequestBody final OrderToPost orderFromRequest) {
        return service.performCreate(orderFromRequest, customerId);
    }
}
