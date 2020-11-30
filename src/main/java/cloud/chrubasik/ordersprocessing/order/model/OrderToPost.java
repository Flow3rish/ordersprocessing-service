package cloud.chrubasik.ordersprocessing.order.model;

public class OrderToPost {
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
}
