package com.example.crm.controller;

import com.example.crm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.crm.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    //need to inject our customer com.example.crm.service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String listCustomers(Model theModel) {
        //get customers from the com.example.crm.service
        List<Customer> customers = customerService.getCustomers();
        //add the customers to the model
        theModel.addAttribute("customers", customers);
        return "listCustomers";
    }

}
