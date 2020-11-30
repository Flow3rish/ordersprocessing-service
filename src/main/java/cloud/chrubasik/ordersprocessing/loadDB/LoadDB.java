package cloud.chrubasik.ordersprocessing.loadDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cloud.chrubasik.ordersprocessing.order.Order;
import cloud.chrubasik.ordersprocessing.order.jpa.OrderJpaRepository;
import cloud.chrubasik.ordersprocessing.customer.Customer;
import cloud.chrubasik.ordersprocessing.customer.jpaService.CustomerJpaRepository;

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
            log.info("Preloading " + orderJpaRepository.save(new Order("2x okurka", customer2)));
        };
    }
}