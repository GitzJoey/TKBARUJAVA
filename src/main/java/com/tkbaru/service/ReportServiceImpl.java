package com.tkbaru.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.User;
import com.tkbaru.model.report.PurchaseOrderReportView;
import com.tkbaru.model.report.SalesOrderReportView;

@Service
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

	private PurchaseOrderReportView ConvertPurchaseOrderToPurchaseOrderReportView(PurchaseOrder data) {
		PurchaseOrderReportView result = new PurchaseOrderReportView();
		
		return result;
	}
	
	private SalesOrderReportView ConvertSalesOrderToSalesOrderReportView(SalesOrder data) {
		SalesOrderReportView result = new SalesOrderReportView();
		
		return result;
	}
	
	@Override
	public JRDataSource generateReportDS_PurchaseOrder(PurchaseOrder data) {
		logger.info("[generateReportDS_PurchaseOrder] " + "");
		
		return null;
	}

	@Override
	public JRDataSource generateReportDS_PurchaseOrder(List<PurchaseOrder> data) {
		logger.info("[generateReportDS_PurchaseOrder] " + "");
		
		return null;
	}

	@Override
	public JRDataSource generateReportDS_SalesOrder(SalesOrder data) {
		logger.info("[generateReportDS_SalesOrder] " + "");
		
		return null;
	}

	@Override
	public JRDataSource generateReportDS_SalesOrder(List<SalesOrder> data) {
		logger.info("[generateReportDS_SalesOrder] " + "");
		
		return null;
	}
	
}
