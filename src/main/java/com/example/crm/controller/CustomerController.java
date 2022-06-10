package com.example.crm.controller;

import com.example.crm.dao.CustomerDAO;
import com.example.crm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //need to inject the customer dao
    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomers(Model theModel) {
        //get customers from the dao
        List<Customer> customers = customerDAO.getCustomers();
        //add the customers to the model
        theModel.addAttribute("customers", customers);
        return "listCustomers";
    }

}
