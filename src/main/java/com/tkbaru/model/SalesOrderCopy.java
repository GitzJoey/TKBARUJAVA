package com.tkbaru.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="tb_so_copy")
public class SalesOrderCopy implements Serializable {
	private static final long serialVersionUID = -1616534193996270137L;

	public SalesOrderCopy() {
		
	}

	@Id
	@Column(name="so_copy_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer salesCopyId;	
	@Column(name="so_code")
	private String salesCode;
	@Column(name="so_copy_code")
	private String salesOrderCopyCode;
	@Column(name="so_copy_desc")
	private String salesOrderCopyDescription;
	@Column(name="so_created")
	private Date salesCreatedDate;
	@Column(name="shipping_date")
	private Date shippingDate;
	@Column(name="walk_in_cust_det")
	private String walkInCustDetail;
	@Column(name="remarks")
	private String salesRemarks;
	@Column(name="created_by")
	private Integer createdBy;
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="updated_by")
	private Integer updatedBy;
	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@OneToMany(mappedBy="salesOrderCopyEntity")
	private List<SalesOrderCopyItems> itemsList;

	@ManyToOne
	@JoinColumn(name="customer_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Customer customerEntity;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store salesStoreEntity;

	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="lookup_key")
	private Lookup salesStatusLookup;
	
	@ManyToOne
	@JoinColumn(name="so_type", referencedColumnName="lookup_key")
	private Lookup salesTypeLookup;

	@ManyToOne
	@JoinColumn(name="customer_type", referencedColumnName="lookup_key")
	private Lookup customerTypeLookup;

	@ManyToOne
	@JoinColumn(name="so_id", nullable=false)
	private SalesOrder salesOrderEntity;

	public String getSalesOrderCopyCode() {
		return salesOrderCopyCode;
	}

	public void setSalesOrderCopyCode(String salesOrderCopyCode) {
		this.salesOrderCopyCode = salesOrderCopyCode;
	}

	public String getSalesOrderCopyDescription() {
		return salesOrderCopyDescription;
	}

	public void setSalesOrderCopyDescription(String salesOrderCopyDescription) {
		this.salesOrderCopyDescription = salesOrderCopyDescription;
	}

	public Integer getSalesCopyId() {
		return salesCopyId;
	}

	public void setSalesCopyId(Integer salesCopyId) {
		this.salesCopyId = salesCopyId;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public Date getSalesCreatedDate() {
		return salesCreatedDate;
	}

	public void setSalesCreatedDate(Date salesCreatedDate) {
		this.salesCreatedDate = salesCreatedDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getWalkInCustDetail() {
		return walkInCustDetail;
	}

	public void setWalkInCustDetail(String walkInCustDetail) {
		this.walkInCustDetail = walkInCustDetail;
	}

	public String getSalesRemarks() {
		return salesRemarks;
	}

	public void setSalesRemarks(String salesRemarks) {
		this.salesRemarks = salesRemarks;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<SalesOrderCopyItems> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<SalesOrderCopyItems> itemsList) {
		this.itemsList = itemsList;
	}

	public Customer getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(Customer customerEntity) {
		this.customerEntity = customerEntity;
	}

	public Store getSalesStoreEntity() {
		return salesStoreEntity;
	}

	public void setSalesStoreEntity(Store salesStoreEntity) {
		this.salesStoreEntity = salesStoreEntity;
	}

	public Lookup getSalesStatusLookup() {
		return salesStatusLookup;
	}

	public void setSalesStatusLookup(Lookup salesStatusLookup) {
		this.salesStatusLookup = salesStatusLookup;
	}

	public Lookup getSalesTypeLookup() {
		return salesTypeLookup;
	}

	public void setSalesTypeLookup(Lookup salesTypeLookup) {
		this.salesTypeLookup = salesTypeLookup;
	}

	public Lookup getCustomerTypeLookup() {
		return customerTypeLookup;
	}

	public void setCustomerTypeLookup(Lookup customerTypeLookup) {
		this.customerTypeLookup = customerTypeLookup;
	}

	public SalesOrder getSalesOrderEntity() {
		return salesOrderEntity;
	}

	public void setSalesOrderEntity(SalesOrder salesOrderEntity) {
		this.salesOrderEntity = salesOrderEntity;
	}
	
}
