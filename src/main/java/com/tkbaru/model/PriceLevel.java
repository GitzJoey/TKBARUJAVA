package com.tkbaru.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="tb_price_level")

public class PriceLevel{

   

   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="price_level_id")
    private int priceLevelId ;


    @Column(name="level_name", length=255)
    private String levelName    ;

    @Column(name="addition")
    private BigDecimal addition;

    @Column(name="subtraction")
    private BigDecimal subtraction;

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


    public PriceLevel() {
		
    }
    
    
    public void setPriceLevelId( int priceLevelId ) {
        this.priceLevelId = priceLevelId ;
    }
    public int getPriceLevelId() {
        return this.priceLevelId;
    }

  
    public void setLevelName( String levelName ) {
        this.levelName = levelName;
    }
    public String getLevelName() {
        return this.levelName;
    }

    
    public void setAddition( BigDecimal addition ) {
        this.addition = addition;
    }
    public BigDecimal getAddition() {
        return this.addition;
    }

   
    public void setSubtraction( BigDecimal subtraction ) {
        this.subtraction = subtraction;
    }
    public BigDecimal getSubtraction() {
        return this.subtraction;
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


    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(priceLevelId);
        sb.append("]:"); 
        sb.append(levelName);
        sb.append("|");
        sb.append(addition);
        sb.append("|");
        sb.append(subtraction);
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
