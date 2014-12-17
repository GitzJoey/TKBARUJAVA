package com.tkbaru.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

	@Autowired
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    private BasicDataSource dbcpDataSource;
    public void setDbcpDataSource(BasicDataSource dbcpDataSource) {
    	this.dbcpDataSource = dbcpDataSource;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomer() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customerList = session.createQuery("FROM Customer").list();
	
		return customerList;
	}

	@Override
	public Customer getCustomerById(int selectedId) {
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
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(cust);
        logger.info("Customer added successfully, Customer Details = " + cust.toString());		
	}

	@Override
	public void editCustomer(Customer cust) {
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(cust);
	    logger.info("Customer updated successfully, Customer Details = " + cust.toString());			
	}

	@Override
	public void deleteCustomer(int selectedId) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.load(Customer.class, new Integer(selectedId));
        if(null != customer){
            session.delete(customer);
        }
        logger.info("Customer deleted successfully, Customer details = " + customer.toString());	
	}
}
