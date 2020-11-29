package cloud.chrubasik.ordersprocessing.customer;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 688291065481139317L;

    public CustomerNotFoundException(Long id) {
        super("Could not find customer " + id);
    }
}
