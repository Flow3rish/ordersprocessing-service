package cloud.chrubasik.ordersprocessing.order;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cloud.chrubasik.ordersprocessing.customer.Customer;

@Entity
@Table(name = "orders")
public class Order {
    private @Id @GeneratedValue Long id;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    public Order() {
    }

    public Order(String description, Customer customer) {
        this.description = description;
        this.customer = customer;
    }

    public Order(Order order) {
        this.description = order.description;
        this.customer = order.customer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public Order description(String description) {
        this.description = description;
        return this;
    }

    public Order customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(description, order.description) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, customer);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", customer='" + getCustomer() + "'" +
            "}";
    }


}
