package com.tkbaru.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.model.User;

public class ReportServiceImpl implements ReportService {
	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	UserService userManager;
	
	@Override
	public JRDataSource generateReportUserDS() {
		logger.info("[generateReportUserDS] " + "");
	
		List<User> userList = userManager.getAllUser();
		
		JRDataSource ds = new JRBeanCollectionDataSource(userList);
		
		return ds;
	}
	
}
