package cloud.chrubasik.ordersprocessing.order;

public class OrderNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4525562785659369118L;

    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
