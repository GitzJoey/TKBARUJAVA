package com.tkbaru.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Override
	public Person getPersonEntityById(int personId) {
		Person result = new Person();
		
		String sqlquery = 
				"SELECT tbperson.person_id,           "+
				"		tbperson.first_name,          "+
				"       tbperson.last_name,           "+
				"       tbperson.addr_1,              "+
				"       tbperson.addr_2,              "+
				"       tbperson.addr_3,              "+
				"       tbperson.email,               "+
				"       tbperson.photo_path           "+
				"FROM tb_person tbperson              "+
				"WHERE tbperson.person_id = ?         ";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		result = jdbcTemplate.queryForObject(sqlquery, new Object[] { personId }, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person pers = new Person();
				
				pers.setPersonId(rs.getInt("person_id"));
				pers.setFirstName(rs.getString("first_name"));
				pers.setLastName(rs.getString("last_name"));
				pers.setAddressLine1(rs.getString("addr_1"));
				pers.setAddressLine2(rs.getString("addr_2"));
				pers.setAddressLine2(rs.getString("addr_3"));
				pers.setEmailAddr(rs.getString("email"));
				pers.setPhotoPath(rs.getString("photo_path"));
				
				return pers;
			}
		});
				
		return result;
	}

	@Override
	public Person getPersonById(int selectedId) {
		logger.info("[getPersonById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
        Person pers = null;
        
        try {
        	pers = (Person) session.load(Person.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Person loaded successfully, Person details = " + pers.toString());
        
        return pers;
	}

	@Override
	public int addPerson(Person person) {
		logger.info("[addPerson] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(person);
        
        logger.info("Person added successfully, Person Details = " + person.toString());
        
        return person.getPersonId();
	}

	@Override
	public void editPerson(Person person) {
		logger.info("[editPerson] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(person);
	    
	    logger.info("Person updated successfully, Person Details = " + person.toString());	
	}

	@Override
	public void deletePerson(int selectedId) {
		logger.info("[deletePerson] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.load(Person.class, new Integer(selectedId));
        if(null != person){
            session.delete(person);
        }
        
        logger.info("Person deleted successfully, Person details = " + person.toString());	
	}

}
