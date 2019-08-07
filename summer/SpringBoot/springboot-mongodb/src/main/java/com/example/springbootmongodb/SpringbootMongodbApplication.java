package com.example.springbootmongodb;

import com.example.springbootmongodb.dao.CustomerRepository;
import com.example.springbootmongodb.enty.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMongodbApplication {

    @Autowired
    private CustomerRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbApplication.class, args);
    }

    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for(Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println("Customer found with findByFirstName('Alice):");
        System.out.println("----------------------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith):");
        System.out.println("--------------------------------------------");
        for(Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }

}
