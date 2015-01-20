package com.tkbaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkbaru.model.Role;

@Repository
@SuppressWarnings("unchecked")
public class RoleDAOImpl implements RoleDAO {
	private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
	@Override
	public List<Role> getAllRole() {
		logger.info("[getAllRole] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList = session.createQuery("FROM Role").list();
	
		for(Role role:roleList) {
			logger.info("Role : " + role.toString());
		}
		
		return roleList;
	}

	@Override
	public Role getRoleById(int selectedId) {
		logger.info("[getRoleById] " + "");
        
		Session session = this.sessionFactory.getCurrentSession();
		Role role = null;
        
        try {
        	role = (Role) session.load(Role.class, new Integer(selectedId));
        } catch (Exception err) {
        	logger.info(err.getMessage());
        }
        
        logger.info("Role loaded successfully, Role details = " + role.toString());
        
        return role;	
	}

	@Override
	public void addRole(Role role) {
		logger.info("[addRole] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        session.save(role);
        
        logger.info("Role added successfully, Role Details = " + role.toString());		
	}

	@Override
	public void editRole(Role role) {
		logger.info("[editRole] " + "");
		
		Session session = this.sessionFactory.getCurrentSession();
	    session.update(role);
	    
	    logger.info("Role updated successfully, Role Details = " + role.toString());			
	}

	@Override
	public void deleteRole(int selectedId) {
		logger.info("[deleteRole] " + "");
		
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.load(Role.class, new Integer(selectedId));
        if(null != role){
            session.delete(role);
        }
        
        logger.info("Role deleted successfully, Role details = " + role.toString());	
	}
	
}
