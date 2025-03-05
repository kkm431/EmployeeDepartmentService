package com.employeedetailsservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class EmployeeConfiguration {
	@Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServerUrl;

    @Bean
    @LoadBalanced // This annotation enables Eureka client-side load balancing
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().baseUrl(eurekaServerUrl);
    }
}
