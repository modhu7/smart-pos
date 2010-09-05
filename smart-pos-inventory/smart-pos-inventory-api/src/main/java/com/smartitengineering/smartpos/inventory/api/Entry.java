/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractPersistentDTO;
import com.smartitengineering.smartpos.admin.api.Organization;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class Entry extends AbstractPersistentDTO<Entry>{

  private Product product;
  private Store store;
  private Double quantity;
  private Date entryDate;
  private Date expiryDate;
  private Organization organization;

  private Integer productID;
  private Integer storeID;
  private Integer organizationID;


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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public Integer getProductID() {
    return productID;
  }

  public void setProductID(Integer productID) {
    this.productID = productID;
  }

  public Integer getStoreID() {
    return storeID;
  }

  public void setStoreID(Integer storeID) {
    this.storeID = storeID;
  }

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public Integer getOrganizationID() {
    return organizationID;
  }

  public void setOrganizationID(Integer organizationID) {
    this.organizationID = organizationID;
  }

  @JsonIgnore
  public boolean isValid(){
    if(product == null || store == null || quantity.equals(0.0))
      return false;
    return true;
  }

}
