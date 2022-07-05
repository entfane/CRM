package com.example.crm.controller;

import com.example.crm.entity.Customer;
import com.example.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveCustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        //save customer using our service
        customerService.saveOrUpdateCustomer(customer);

        return "redirect:/";
    }

}
