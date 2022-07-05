package com.example.crm.controller;

import com.example.crm.entity.Customer;
import com.example.crm.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crm.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    //need to inject our customer com.example.crm.service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String listCustomers(@RequestParam(required = false) Integer sortCode, Model theModel) {

        SortUtils sort = sortCodeToSortUtils(sortCode);

        List<Customer> customers = customerService.getCustomers(sort);

        //add the customers to the model
        theModel.addAttribute("customers", customers);
        return "listCustomers";
    }

    /*

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        //save customer using our service
        customerService.saveOrUpdateCustomer(customer);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        //get the customer from the database
        Customer customer = customerService.getCustomer(id);
        //set the customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        //send over to our form
        return "customerForm";
    }

    @GetMapping("/delete")
    public String showFormForUpdate(@RequestParam("customerId") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {

        List<Customer> customers = customerService.searchCustomers(searchName);

        model.addAttribute("customers", customers);

        return "listCustomers";
    }

    @PostMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
     */

    private SortUtils sortCodeToSortUtils(Integer sortCode) {       // 1 - first name, 2 - last name, 3 - email

        if (sortCode == null) {
            return SortUtils.LAST_NAME;
        }

        switch (sortCode) {

            case 1:
                return SortUtils.FIRST_NAME;
            case 3:
                return SortUtils.EMAIL;
            default:
                return SortUtils.LAST_NAME;
        }
    }

}
