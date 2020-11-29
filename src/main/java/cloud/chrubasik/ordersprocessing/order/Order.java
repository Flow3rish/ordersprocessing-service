package cloud.chrubasik.ordersprocessing.order;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cloud.chrubasik.ordersprocessing.customer.Customer;

@Entity
public class Order {
    private @Id @GeneratedValue Long id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY) // TODO skoncil jsem tady
    private Customer customer;



}
