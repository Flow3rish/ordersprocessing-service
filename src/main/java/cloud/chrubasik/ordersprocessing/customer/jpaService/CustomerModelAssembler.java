package cloud.chrubasik.ordersprocessing.customer.jpaService;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cloud.chrubasik.ordersprocessing.customer.Customer;
import cloud.chrubasik.ordersprocessing.customer.CustomerController;
import cloud.chrubasik.ordersprocessing.order.OrderController;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    /**
     * convert a non-model object (Customer) into a model-based object
     * (EntityModel<Customer>).
     */
    @Override
    public EntityModel<Customer> toModel(Customer entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).one(entity.getId()))
                        .withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).all())
                                        .withRel("customers"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).all(entity.getId()))
                                        .withRel("ordersList"));

    }
}
