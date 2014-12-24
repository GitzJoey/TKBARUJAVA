package com.tkbaru.reports.datasource;

import java.util.ArrayList;
import java.util.List;

import com.tkbaru.model.User;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UserDataSource extends JRAbstractBeanDataSourceProvider {

	private List<User> listUser;
	
	public UserDataSource() {
		super(User.class);
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		listUser = new ArrayList<User>();
		
		User x = new User();
		x.setUserId(1);
		x.setUserName("test");

		listUser.add(x);

		return new JRBeanCollectionDataSource(listUser);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		listUser.clear();
		listUser = null;
	}

}
