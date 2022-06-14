package com.example.crm.service;

import com.example.crm.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);
}
