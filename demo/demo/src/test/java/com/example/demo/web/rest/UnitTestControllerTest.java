package com.example.demo.web.rest;

import com.example.demo.model.Customer;
import com.example.demo.respository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
//@WebMvcTest(UnitTestController.class)
@SpringBootTest
class UnitTestControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    public void hello() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
//        MvcResult result = mockMvc.perform(request).andReturn();
//        assertEquals("Hello, World", result.getResponse().getContentAsString());
//    }

    @Autowired
    private CustomerService service;

    @MockBean
    private CustomerRepository repository;

    @Test
    public void getCustomerTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new Customer(376, "Danile", 31, "USA"), new Customer(958, "Huy", 35, "UK")).collect(Collectors.toList()));
        assertEquals(2, service.getCustomer().size());
    }

//    @BeforeEach
//    public List<Customer> abc(){
//        String address = "Angola";
//        return repository.findByAddress(address);
//    }

    @Test
    @DisplayName("check list Angila")
    public void getCustomerbyAddressTest() {
        String address = "Angola";
        when(repository.findByAddress(address))
                .thenReturn(Stream.of(new Customer(376, "Danile", 31, "USA")).collect(Collectors.toList()));
        assertEquals(1, service.getCustomerByAddress(address).size());
    }

    @Test
    public void saveCustomerTest() {
        Customer customer = new Customer(999, "Pranya", 33, "Pune");
        when(repository.save(customer)).thenReturn(customer);
        assertEquals(customer, service.addCustomer(customer));
    }

    @Test
    public void deleteCustomerTest() {
        Customer customer = new Customer(999, "Pranya", 33, "Pune");
        service.deleteCustomer(customer);
        verify(repository, times(1)).delete(customer);
    }


}