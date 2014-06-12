package com.prudentcpa.customerDB;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.prudentcpa.customerDB.domain.Customer;
import com.prudentcpa.customerDB.repository.CustomerRepository;

@ImportResource("classpath:cache-config.xml")
@EnableGemfireRepositories
public class Application {
	
	
	public static void createFile(){
		//Create a new Path
		
		String tmp = System.getProperty("java.io.tmpdir");
		Path newFile = Paths.get(tmp +"pdx");
		try {
		  if(!Files.exists(newFile)){
			  newFile = Files.createDirectory(newFile);
		  }
		} catch (IOException ex) {
		  ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//TODO use it temporary, delete it later
		createFile();
		
		
		ConfigurableApplicationContext appContext = SpringApplication.run(Application.class, args);
		CustomerRepository customerRepository = appContext.getBean(CustomerRepository.class);
		
		Customer customer = new Customer(1L, "Jimmy", "Jones");
		customerRepository.save(customer);
		
		Customer reCustomer = customerRepository.findOne(1L);
		System.out.println(reCustomer);
		
		System.out.println("Done!");
	}
}
