package cloud.chrubasik.ordersprocessing.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping
    public List<EntityModel<Order>> all() {
        return service.performList();
    }

    @GetMapping("/{id}")
    public EntityModel<Order> one(@PathVariable Long id) {
        return service.performDetail(id);
    }

    @PostMapping
    public EntityModel<Order> post(@RequestBody final Order orderFromRequest) {
        return service.performCreate(orderFromRequest);
        // TODO v requestu nemusi byt cely customer
    }
    
}
