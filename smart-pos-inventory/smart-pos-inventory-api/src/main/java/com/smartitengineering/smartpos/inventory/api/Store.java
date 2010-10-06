/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class Store extends AbstractGenericPersistentDTO<Store, StoreId, Long>{

  private String name;

  private String code;

  private Organization organization;
  
  private Address address;

  private Integer organizationID;

  private String[] productIds;

  @JsonIgnore
  public Integer getOrganizationID() {
    return organizationID;
  }

  public void setOrganizationID(Integer organizationID) {
    this.organizationID = organizationID;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  @JsonIgnore
  public String[] getProductIds() {
    return productIds;
  }

  public void setProductIds(String[] productIds) {
    this.productIds = productIds;
  }  

  public boolean isValid(){
    return true;
  }

}
