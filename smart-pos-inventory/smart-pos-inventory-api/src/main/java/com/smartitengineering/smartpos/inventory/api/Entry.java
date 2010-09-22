/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.admin.api.Organization;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class Entry extends AbstractGenericPersistentDTO<Entry, String, Long>{

  private Double quantity;
  private Date entryDate;
  private Date expiryDate;
  
  private Integer productId;
  private Integer storeId;
  private Integer organizationId;

  public Date getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Integer getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Integer organizationId) {
    this.organizationId = organizationId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }  

  @JsonIgnore
  public boolean isValid() {
    if (productId == null || storeId == null || quantity.equals(0.0)) {
      return false;
    }
    return true;
  }
}
