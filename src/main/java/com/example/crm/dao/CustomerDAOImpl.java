package com.example.crm.dao;

import com.example.crm.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        //create a query, sort by  last name
        Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        session.getTransaction().commit();
        //return the results
        return customers;
    }

    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        //save customer or update if it already exists
        session.saveOrUpdate(customer);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }
}
