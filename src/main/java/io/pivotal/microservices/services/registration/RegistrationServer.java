package io.pivotal.microservices.services.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All you need to run a Eureka registration server.
 * 
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class RegistrationServer {

	@Autowired
	private DiscoveryClient discoveryClient;

	public String serviceUrl() {
	    List<ServiceInstance> list = discoveryClient.getInstances("accounts-service");
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
	
	@RequestMapping("/health")
    public String home() {
        return "Hello world - "+this.serviceUrl();
    }
	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for registration.properties or registration.yml
		//System.setProperty("spring.config.name", "registration-server");

		SpringApplication.run(RegistrationServer.class, args);
	}
}
