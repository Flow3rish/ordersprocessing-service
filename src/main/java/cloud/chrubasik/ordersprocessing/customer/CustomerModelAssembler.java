package cloud.chrubasik.ordersprocessing.customer;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    /**
     * convert a non-model object (Customer) into a model-based object (EntityModel<Customer>).
     */
    @Override
    public EntityModel<Customer> toModel(Customer entity) {
        return EntityModel.of(entity, //
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).one(entity.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)).withRel("customers"));
    }
}
