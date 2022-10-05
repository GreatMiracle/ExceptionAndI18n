package com.example.demo.service.impl;

import com.example.demo.model.Customer;
import com.example.demo.respository.CustomerRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomer() {
        List<Customer> cus = customerRepository.findAll();
        System.out.println("Getting data from DB : " + cus);
        return cus;
    }

    @Override
    public List<Customer> getCustomerByAddress(String address) {
        return customerRepository.findByAddress(address);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
