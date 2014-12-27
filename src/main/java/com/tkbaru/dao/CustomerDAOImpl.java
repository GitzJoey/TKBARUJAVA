package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomer() {
		logger.info("[getAllCustomer] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customerList = session.createQuery("FROM Customer").list();
	
		for(Customer cust:customerList) {
			logger.info("Customer : " + cust.toString());
		}
		
		return customerList;
	}

	@Override
	public Customer getCustomerById(int selectedId) {
		logger.info("[getCustomerById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
        Customer cust = null;
        
        try {
        	cust = (Customer) session.load(Customer.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Customer loaded successfully, Customer details = " + cust.toString());
        
        return cust;	
	}

	@Override
	public void addCustomer(Customer cust) {
		logger.info("[addCustomer] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(cust);
        
        logger.info("Customer added successfully, Customer Details = " + cust.toString());		
	}

	@Override
	public void editCustomer(Customer cust) {
		logger.info("[editCustomer] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(cust);
	    
	    logger.info("Customer updated successfully, Customer Details = " + cust.toString());			
	}

	@Override
	public void deleteCustomer(int selectedId) {
		logger.info("[deleteCustomer] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.load(Customer.class, new Integer(selectedId));
        if(null != customer){
            session.delete(customer);
        }
        
        logger.info("Customer deleted successfully, Customer details = " + customer.toString());	
	}
}