package com.tkbaru.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Erwin
 */
@Entity
@Table(name = "tb_supplier_product")
public class SupplierProduct {

    public SupplierProduct() {
    }

    @Id
    @Column(name = "supplier_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierProductId;
    @Column(name = "supplier_id")
    private int supplierId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "status")
    private String supplierProductStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplierEnt;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product productDetail;

    /**
     * @return the supplierProductId
     */
    public int getSupplierProductId() {
        return supplierProductId;
    }

    /**
     * @param supplierProductId the supplierProductId to set
     */
    public void setSupplierProductId(int supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    /**
     * @return the supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the supplierProductStatus
     */
    public String getSupplierProductStatus() {
        return supplierProductStatus;
    }

    /**
     * @param supplierProductStatus the supplierProductStatus to set
     */
    public void setSupplierProductStatus(String supplierProductStatus) {
        this.supplierProductStatus = supplierProductStatus;
    }

    /**
     * @return the supplierEnt
     */
    public Supplier getSupplierEnt() {
        return supplierEnt;
    }

    /**
     * @param supplierEnt the supplierEnt to set
     */
    public void setSupplierEnt(Supplier supplierEnt) {
        this.supplierEnt = supplierEnt;
    }

    /**
     * @return the productDetail
     */
    public Product getProductDetail() {
        return productDetail;
    }

    /**
     * @param productDetail the productDetail to set
     */
    public void setProductDetail(Product productDetail) {
        this.productDetail = productDetail;
    }
    
    @Override
	public String toString() {
		return "SupplierProduct [supplierProductId="
				+ supplierProductId + ", supplierId=" + supplierId
				+ ", bankAccId=" + supplierId + ", supplierBankAccountStatus="
				+ supplierProductStatus + ", supplierEnt=" + supplierEnt
				+ ", bankAccDetail=" + productDetail + "]";
	}

}
