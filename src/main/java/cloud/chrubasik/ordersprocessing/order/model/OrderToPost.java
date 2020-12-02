package cloud.chrubasik.ordersprocessing.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderToPost extends AbstractOrder {
    protected @Id @GeneratedValue Long id;
    protected String description;

    public OrderToPost() {
    }

    public OrderToPost(String description) {
        this.description = description;
    }

    public OrderToPost(OrderToPost otp) {
        this.description = otp.description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
