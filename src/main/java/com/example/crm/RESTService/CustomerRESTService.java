package com.example.crm.RESTService;

import com.example.crm.entity.Customer;
import com.example.crm.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerRESTService implements CustomerService {

    private RestTemplate restTemplate;
    private final String crmRestUrl = "http://localhost:8080/api/customers";

    @Autowired
    public CustomerRESTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Customer> getCustomers(SortUtils sort) {
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl + "/sorted/" + sort, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<Customer> customers = responseEntity.getBody();
        return customers;
    }

    @Override
    public List<Customer> getCustomers() {
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<Customer> customers = responseEntity.getBody();
        return customers;
    }

    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        int id = customer.getId();
        if (id == 0) {
            restTemplate.postForEntity(crmRestUrl, customer, String.class);
        } else {
            restTemplate.put(crmRestUrl, customer);
        }
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = restTemplate.getForObject(crmRestUrl + "/" + id, Customer.class);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        restTemplate.delete(crmRestUrl + "/" + id);
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl + "/search/" + searchName, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<Customer> customers = responseEntity.getBody();
        return customers;
    }
}
