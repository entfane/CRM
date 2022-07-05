package com.example.crm.controller;

import com.example.crm.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddCustomerController {

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerForm";
    }

}
