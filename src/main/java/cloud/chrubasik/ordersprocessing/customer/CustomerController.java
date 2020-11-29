package cloud.chrubasik.ordersprocessing.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;




@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @Operation(summary = "Gets a list of all Customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "A list of all books",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Customer.class)))}),
    })
    @GetMapping
    public List<EntityModel<Customer>> all() {
        return service.performList();
    }

    @GetMapping("/{id}")
    public Customer one(@PathVariable Long id) {
        return service.performDetail(id);
    }
}
