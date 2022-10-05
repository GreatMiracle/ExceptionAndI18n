package com.example.demo.web.rest;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UnitTestController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name){
        return String.format("Hello, %s", name);
    }

    private final CustomerService customerService;

    @PostMapping(value = "/customer/save")
    public Customer saveUser(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/customer/getAll")
    public List<Customer> findAllUsers() {
        return customerService.getCustomer();
    }

    @GetMapping("/getUserByAddress/{address}")
    public List<Customer> findUserByAddress(@PathVariable String address) {
        return customerService.getCustomerByAddress(address);
    }

    @DeleteMapping(value="/remove")
    public Customer removeUser(@RequestBody Customer user) {
        customerService.deleteCustomer(user);
        return user;
    }
}
