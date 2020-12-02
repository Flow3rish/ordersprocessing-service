package cloud.chrubasik.ordersprocessing.customer;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.chrubasik.ordersprocessing.customer.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;




@RestController
@RequestMapping("customers")
public class CustomerController {
    // TODO napsat dokumentaci

    @Autowired
    CustomerService service;

    @Operation(summary = "Gets a list of all customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "A list of all customers",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Customer.class)))}),
    })
    @GetMapping
    public Set<EntityModel<Customer>> all() {
        return service.performList();
    }

    @Operation(summary = "Gets detail of one customer by id.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "A detail of a customer", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }), })
    @GetMapping("/{id}")
    public EntityModel<Customer> one(@PathVariable @Parameter(in = ParameterIn.PATH, description = "Id of the customer") Long id) {
        return service.performDetail(id);
    }

}
