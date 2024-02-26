package net.service.manager.about;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
public class AboutServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AboutServiceApplication.class, args);
	}

}
