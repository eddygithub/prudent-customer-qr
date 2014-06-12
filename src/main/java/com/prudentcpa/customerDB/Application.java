package com.prudentcpa.customerDB;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.prudentcpa.customerDB.domain.Customer;
import com.prudentcpa.customerDB.repository.CustomerRepository;

@ImportResource("classpath:cache-config.xml")
@EnableGemfireRepositories
public class Application {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(Application.class, args);
		CustomerRepository customerRepository = appContext.getBean(CustomerRepository.class);
		
		Customer customer = new Customer(1L, "Jimmy", "Jones");
		customerRepository.save(customer);
		
		Customer reCustomer = customerRepository.findOne(1L);
		System.out.println(reCustomer);
		
		System.out.println("Done!");
	}
}
