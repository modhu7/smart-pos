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

  public static enum TransactionType{
    INBOUND_PURCHASE,
    INBOUND_RETURN,
    INBOUND_WAREHOUSE_RECIEVE,
    OUTBOUND_SALE,
    OUTBOUND_RETURN,
    OUTBOUND_WAREHOUSE_TRANSFER
  }

  private Double quantity;
  private Date entryDate;
  private Date expiryDate;
  private TransactionType type;
  
  private String productId;
  private String storeId;
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

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }  

  @JsonIgnore
  public boolean isValid() {
    if (productId == null || storeId == null || quantity.equals(0.0)) {
      return false;
    }
    return true;
  }
}
