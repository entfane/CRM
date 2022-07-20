package com.example.crm.RESTService;

import com.example.crm.entity.Customer;

import java.util.List;

public interface RestCustomerService {

    List<Customer> getCustomers(int sort);

    void saveOrUpdateCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String searchName);
}
