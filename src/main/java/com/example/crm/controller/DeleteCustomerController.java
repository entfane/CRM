package com.example.crm.controller;

import com.example.crm.RESTService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/delete")
    public String showFormForUpdate(@RequestParam("customerId") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }



}
