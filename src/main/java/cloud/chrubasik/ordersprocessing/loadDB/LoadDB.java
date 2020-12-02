package cloud.chrubasik.ordersprocessing.loadDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cloud.chrubasik.ordersprocessing.order.model.Order;
import cloud.chrubasik.ordersprocessing.order.jpa.OrderJpaRepository;
import cloud.chrubasik.ordersprocessing.customer.jpa.CustomerJpaRepository;
import cloud.chrubasik.ordersprocessing.customer.model.Customer;

@Configuration
/**
 * pre-load the in-memory database
 */
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerJpaRepository customerJpaRepository, OrderJpaRepository orderJpaRepository) {

        return args -> {
            Customer customer1 = new Customer("Jon Doe");
            Customer customer2 = new Customer("Jane Doe");

            log.info("Preloading " + customerJpaRepository.save(customer1));
            log.info("Preloading " + customerJpaRepository.save(customer2));
            log.info("Preloading " + orderJpaRepository.save(new Order("2x rohlik", customer1)));
            log.info("Preloading " + orderJpaRepository.save(new Order("3x banan", customer1)));
            log.info("Preloading " + orderJpaRepository.save(new Order("5x houska", customer1)));
            log.info("Preloading " + orderJpaRepository.save(new Order("1x okurka", customer2)));
        };
    }
}