package cloud.chrubasik.ordersprocessing.order.jpa;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import cloud.chrubasik.ordersprocessing.customer.CustomerController;
import cloud.chrubasik.ordersprocessing.order.model.Order;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    /**
     * convert a non-model object (Order) into a model-based object
     * (EntityModel<Order>).
     */
    @Override
    public EntityModel<Order> toModel(Order entity) {


        return EntityModel.of(entity, //
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).one(entity.getCustomer().getId()))
                        .slash(entity.getId()).withSelfRel()); // TODO dodelat linky
    }

}
