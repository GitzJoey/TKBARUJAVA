package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.model.Items;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.User;
import com.tkbaru.model.report.ItemsReportView;
import com.tkbaru.model.report.PurchaseOrderReportView;
import com.tkbaru.model.report.PurchasePaymentReportView;
import com.tkbaru.model.report.ReceiptReportView;
import com.tkbaru.model.report.SalesOrderReportView;
import com.tkbaru.model.report.SalesPaymentReportView;

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
	
	@Override
	public JRDataSource generateReportDS_PurchaseOrder(PurchaseOrder po) {
		logger.info("[generateReportDS_PurchaseOrder] " + "");
		
		PurchaseOrderReportView objPo = new PurchaseOrderReportView();
		objPo.setPoCode(po.getPoCode());
		objPo.setStoreName(po.getPoStoreEntity().getStoreName());
		objPo.setStoreAddress1(po.getPoStoreEntity().getStoreAddress1());
		objPo.setStoreAddress2(po.getPoStoreEntity().getStoreAddress2());
		objPo.setStoreAddress3(po.getPoStoreEntity().getStoreAddress3());
		objPo.setNpwpNumber(po.getPoStoreEntity().getNpwpNumber());
		objPo.setStorePhone(po.getPoStoreEntity().getStorePhone());
		objPo.setShippingDate(po.getShippingDate());
		objPo.setPoCreatedDate(po.getPoCreatedDate());
		objPo.setPoRemarks(po.getPoRemarks());
		objPo.setSupplierName(po.getSupplierEntity().getSupplierName());
		objPo.setWarehouseName(po.getWarehouseEntity().getWarehouseName());
		
		List<ItemsReportView> itemsReportList = new ArrayList<ItemsReportView>();

		for (Items item : po.getItemsList()) {
			ItemsReportView objItem = new ItemsReportView();
			objItem.setProductName(item.getProductEntity().getProductName());
			objItem.setProdPrice(item.getProdPrice());
			objItem.setProdQuantity(item.getProdQuantity());
			objItem.setUnitCode(item.getUnitCodeLookup().getLookupValue());
		
			itemsReportList.add(objItem);
		}
		
		objPo.setItems(itemsReportList);
		
		if (po.getPaymentList().size() > 0){
		
			PurchasePaymentReportView payment = new PurchasePaymentReportView();
			payment.setPaymentType(po.getPaymentList().get(0).getPaymentTypeLookup().getLookupValue());
			payment.setPaymentDate(po.getPaymentList().get(0).getPaymentDate());
			payment.setEffectiveDate(po.getPaymentList().get(0).getEffectiveDate());
			payment.setBankCode(po.getPaymentList().get(0).getBankCodeLookup().getLookupValue());
			payment.setTotalAmount(po.getPaymentList().get(0).getTotalAmount());
			payment.setPaymentStore(po.getPaymentList().get(0).getPaymentStoreEntity().getStoreName());
		
			objPo.setPayment(payment);
		}
		
		
		if(po.getItemsList().get(0).getReceiptList().size() > 0){
			
			ReceiptReportView receipt = new ReceiptReportView();
			receipt.setReceiptDate(po.getItemsList().get(0).getReceiptList().get(0).getReceiptDate());
			receipt.setBruto(po.getItemsList().get(0).getReceiptList().get(0).getBruto());
			receipt.setNet(po.getItemsList().get(0).getReceiptList().get(0).getNet());
			receipt.setTare(po.getItemsList().get(0).getReceiptList().get(0).getTare());
			receipt.setUnitCode(po.getItemsList().get(0).getReceiptList().get(0).getUnitCodeLookup().getLookupValue());
			receipt.setReceiptStore(po.getItemsList().get(0).getReceiptList().get(0).getReceiptStoreEntity().getStoreName());
		}
		
		List<PurchaseOrderReportView> poReportList = new ArrayList<PurchaseOrderReportView>();
		poReportList.add(objPo);

		JRDataSource ds = new JRBeanCollectionDataSource(poReportList);
		return ds;
	}

	@Override
	public JRDataSource generateReportDS_PurchaseOrder(List<PurchaseOrder> data) {
		logger.info("[generateReportDS_PurchaseOrder] " + "");
		
		return null;
	}

	@Override
	public JRDataSource generateReportDS_SalesOrder(SalesOrder so) {
		logger.info("[generateReportDS_SalesOrder] " + "");
		List<SalesOrderReportView> soReportList = new ArrayList<SalesOrderReportView>();
		SalesOrderReportView objSo = new SalesOrderReportView();
		objSo.setSalesCode(so.getSalesCode());
		objSo.setCustomerName(so.getCustomerEntity().getCustomerName());
		objSo.setStoreName(so.getSalesStoreEntity().getStoreName());
		objSo.setStoreAddress1(so.getSalesStoreEntity().getStoreAddress1());
		objSo.setStoreAddress2(so.getSalesStoreEntity().getStoreAddress2());
		objSo.setStoreAddress3(so.getSalesStoreEntity().getStoreAddress3());
		objSo.setNpwpNumber(so.getSalesStoreEntity().getNpwpNumber());
		objSo.setStorePhone(so.getSalesStoreEntity().getStorePhone());
		objSo.setShippingDate(so.getShippingDate());
		objSo.setSalesCreatedDate(so.getSalesCreatedDate());
		objSo.setSalesRemarks(so.getSalesRemarks());
		
		List<ItemsReportView> itemsList = new ArrayList<ItemsReportView>();
		
		for (Items item : so.getItemsList()){
			ItemsReportView objItem = new ItemsReportView();
			objItem.setProductName(item.getProductEntity().getProductName());
			objItem.setProdPrice(item.getProdPrice());
			objItem.setProdQuantity(item.getProdQuantity());
			objItem.setUnitCode(item.getUnitCodeLookup().getLookupValue());
			itemsList.add(objItem);
			
		}
		objSo.setItems(itemsList);
		
		if(so.getPaymentList().size() > 0) {
		
		SalesPaymentReportView payment = new SalesPaymentReportView();
		payment.setPaymentType(so.getPaymentList().get(0).getPaymentTypeLookup().getLookupValue());
		payment.setPaymentDate(so.getPaymentList().get(0).getPaymentDate());
		payment.setEffectiveDate(so.getPaymentList().get(0).getEffectiveDate());
		payment.setBankCode(so.getPaymentList().get(0).getBankCodeLookup().getLookupValue());
		payment.setTotalAmount(so.getPaymentList().get(0).getTotalAmount());
		payment.setPaymentStore(so.getPaymentList().get(0).getPaymentStoreEntity().getStoreName());
		
		objSo.setPayment(payment);
		}
		
		soReportList.add(objSo);
		
		JRDataSource ds = new JRBeanCollectionDataSource(soReportList,false);
		return ds;
	}

	@Override
	public JRDataSource generateReportDS_SalesOrder(List<SalesOrder> data) {
		logger.info("[generateReportDS_SalesOrder] " + "");
		
		return null;
	}
	
}
