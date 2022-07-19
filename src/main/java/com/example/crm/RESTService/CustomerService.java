package com.example.crm.RESTService;

import com.example.crm.entity.Customer;
import com.example.crm.utils.SortUtils;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(SortUtils sort);

    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String searchName);
}
