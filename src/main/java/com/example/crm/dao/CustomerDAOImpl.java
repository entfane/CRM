package com.example.crm.dao;

import com.example.crm.entity.Customer;
import com.example.crm.utils.SortUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Locale;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(SortUtils sort) {
        //get current hibernate session
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query<Customer> theQuery;
        switch (sort) {

            case FIRST_NAME:
                theQuery = session.createQuery("from Customer order by firstName", Customer.class);
                break;
            case EMAIL:
                theQuery = session.createQuery("from Customer order by email", Customer.class);
                break;
            default:    //by default lastName
                theQuery = session.createQuery("from Customer order by lastName", Customer.class);

        }
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

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = null;
        if ( (searchName != null) && (searchName.trim().length() > 0) ) {       // check whether searchName is empty
            query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        } else {    // searchName is empty
            query = session.createQuery("from Customer", Customer.class);
        }

        List<Customer> customers = query.getResultList();
        session.getTransaction().commit();
        return customers;
    }
}
