package com.example.crm.dao;

import com.example.crm.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer customer);

    Customer getCustomer(int id);
}
