package cloud.chrubasik.ordersprocessing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cloud.chrubasik.ordersprocessing.customer.jpa.CustomerJpaRepository;

import cloud.chrubasik.ordersprocessing.customer.Customer;

@Configuration
/**
 * pre-load the in-memory database
 */
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CustomerJpaRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Customer("Jon Doe")));
      log.info("Preloading " + repository.save(new Customer("Don Joe")));
      log.info("Preloading " + repository.save(new Customer("Jon Joe")));
      log.info("Preloading " + repository.save(new Customer("Don Doe")));
    };
  }
}