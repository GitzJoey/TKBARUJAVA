package com.tkbaru.service;

import java.util.List;

import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.Stocks;

import net.sf.jasperreports.engine.JRDataSource;

public interface ReportService {
	public JRDataSource generateReportUserDS();
	
	public JRDataSource generateReportDS_PurchaseOrder(PurchaseOrder data);
	public JRDataSource generateReportDS_PurchaseOrder(List<PurchaseOrder> data);
	public JRDataSource generateReportDS_SalesOrder(SalesOrder data);
	public JRDataSource generateReportDS_SalesOrder(List<SalesOrder> data);
	public JRDataSource generateReportDS_Stocks(List<Stocks> data);
}
