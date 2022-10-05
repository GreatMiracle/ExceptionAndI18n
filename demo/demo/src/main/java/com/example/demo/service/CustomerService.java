package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer);
    public List<Customer> getCustomer();
    public List<Customer> getCustomerByAddress(String address);
    public void deleteCustomer(Customer customer);

}
