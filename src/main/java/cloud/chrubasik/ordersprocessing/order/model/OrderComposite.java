package cloud.chrubasik.ordersprocessing.order.model;

import cloud.chrubasik.ordersprocessing.customer.model.Customer;

public class OrderComposite {
    private AbstractOrder order;

    public OrderComposite() {
    }

    // public OrderComposite data(String description, Customer customer) {
    // this.order = new Order(description, customer);
    // return this;
    // }

    public OrderComposite data(OrderToPost orderToPost, Customer customer) {
        this.order = new Order(orderToPost, customer);
        return this;
    }

    public AbstractOrder getData() {
        return this.order;
    }
}
