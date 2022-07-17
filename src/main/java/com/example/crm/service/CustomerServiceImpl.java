package com.example.crm.service;

import com.example.crm.dao.CustomerDAO;
import com.example.crm.entity.Customer;
import com.example.crm.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers(SortUtils sort) {
        return customerDAO.getCustomers(sort);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers(SortUtils.LAST_NAME);
    }

    @Override
    @Transactional
    public void saveOrUpdateCustomer(Customer customer) {
        customerDAO.saveOrUpdateCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String searchName) {
        return customerDAO.searchCustomers(searchName);
    }
}
