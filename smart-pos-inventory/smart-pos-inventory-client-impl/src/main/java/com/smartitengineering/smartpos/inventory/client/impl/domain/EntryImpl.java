/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Entry;
import java.util.Date;

/**
 *
 * @author saumitra
 */
public class EntryImpl implements Entry {
  
  private String quantity;
  private Date entryDate;
  private Date expireDate;
  private String type;
  private String productId;
  private String storeId;
  private Integer orgId;

  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String getQuantity() {
    return quantity;
  }

  @Override
  public Date gerEntryDate() {
    return entryDate;
  }

  @Override
  public Date getExpireDate() {
    return expireDate;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public String getProductId() {
    return productId;
  }

  @Override
  public String getStoreId() {
    return storeId;
  }

  @Override
  public Integer getOrganizationId() {
    return orgId;
  }

}
