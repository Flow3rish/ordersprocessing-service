package cloud.chrubasik.ordersprocessing.order.jpa;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import cloud.chrubasik.ordersprocessing.order.Order;
import cloud.chrubasik.ordersprocessing.order.OrderController;

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
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).one(entity.getId()))
                        .withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)).withRel("orders"));
    }
}
