package com.example.crm.controller;

import com.example.crm.entity.Customer;
import com.example.crm.RESTService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchCustomersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {

        List<Customer> customers = customerService.searchCustomers(searchName);

        model.addAttribute("customers", customers);

        return "listCustomers";
    }

}
