package com.microservice.registry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public class ServiceUrlRegistry {

	public ServiceUrlRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	// parameterized method
	public String serviceUrl(String serviceUrl) {
	    List<ServiceInstance> list = discoveryClient.getInstances(serviceUrl);
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
	// Check method
	public String serviceUrl() {
	    List<ServiceInstance> list = discoveryClient.getInstances("accounts-service");
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
}
