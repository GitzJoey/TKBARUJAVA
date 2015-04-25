package com.tkbaru.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "tb_price"
 *
 * @author 
 *
 */

@Entity
@Table(name="tb_price")

public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="price_id", nullable=false)
    private Integer priceId;


    @Column(name="product_id")
    private Integer productId;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="level_id")
    private Integer levelId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="effective_date")
    private Date effectiveDate;

    @Column(name="is_active", length=5)
    private Boolean isActive;

    @Column(name="created_by")
    private Integer createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="updated_by")
    private Integer updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;
    
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false, insertable=false, updatable=false)
    private Product product;


    public Price() {
		
    }
    
   
    public void setPriceId( Integer priceId ) {
        this.priceId = priceId ;
    }
    public Integer getPriceId() {
        return this.priceId;
    }

   
    public void setProductId( Integer productId ) {
        this.productId = productId;
    }
    public Integer getProductId() {
        return this.productId;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }
    public BigDecimal getPrice() {
        return this.price;
    }

   
    public void setLevelId( Integer levelId ) {
        this.levelId = levelId;
    }
    public Integer getLevelId() {
        return this.levelId;
    }

    
    public void setEffectiveDate( Date effectiveDate ) {
        this.effectiveDate = effectiveDate;
    }
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

   
    public void setIsActive( Boolean isActive ) {
        this.isActive = isActive;
    }
    public Boolean getIsActive() {
        return this.isActive;
    }

  
    public void setCreatedBy( Integer createdBy ) {
        this.createdBy = createdBy;
    }
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    
    public void setCreatedDate( Date createdDate ) {
        this.createdDate = createdDate;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }

    
    public void setUpdatedBy( Integer updatedBy ) {
        this.updatedBy = updatedBy;
    }
    public Integer getUpdatedBy() {
        return this.updatedBy;
    }

    
    public void setUpdatedDate( Date updatedDate ) {
        this.updatedDate = updatedDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    


    public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(priceId);
        sb.append("]:"); 
        sb.append(productId);
        sb.append("|");
        sb.append(price);
        sb.append("|");
        sb.append(levelId);
        sb.append("|");
        sb.append(effectiveDate);
        sb.append("|");
        sb.append(isActive);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(createdDate);
        sb.append("|");
        sb.append(updatedBy);
        sb.append("|");
        sb.append(updatedDate);
        return sb.toString(); 
    } 

}
