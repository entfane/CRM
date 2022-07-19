package com.example.crm.controller;

import com.example.crm.entity.Customer;
import com.example.crm.RESTService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        //get the customer from the database
        Customer customer = customerService.getCustomer(id);
        //set the customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        //send over to our form
        return "customerForm";
    }

}
