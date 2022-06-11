package com.example.crm.dao;

import com.example.crm.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImp implements CustomerDAO {

    // need to inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional()
    public List<Customer> getCustomers() {
        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        //create a query
        Query<Customer> theQuery = session.createQuery("from Customer", Customer.class);
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        session.getTransaction().commit();
        //return the results
        return customers;
    }
}
