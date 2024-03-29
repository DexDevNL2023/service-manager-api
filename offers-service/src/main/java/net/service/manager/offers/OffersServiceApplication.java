package net.service.manager.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
public class OffersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffersServiceApplication.class, args);
	}

}
