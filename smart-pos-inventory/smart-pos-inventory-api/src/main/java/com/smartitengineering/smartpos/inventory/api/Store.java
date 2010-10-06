/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import org.apache.commons.lang.StringUtils;
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
    if(StringUtils.isNotBlank( getId().getId()))
      return true;
    
    return false;
  }

  @Override
  public String toString(){
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("id:").append(getId());
    strBuilder.append("name:").append(name);
    strBuilder.append("code:").append(code);
    strBuilder.append("Address: StreeAddress:").append(address.getStreetAddress());
    strBuilder.append("Address: City:").append(address.getCity());
    strBuilder.append("Address: State:").append(address.getState());
    strBuilder.append("Address: Country:").append(address.getCountry());
    strBuilder.append("Address: Zip:").append(address.getZip());
    strBuilder.append("Address: Longitude:").append(address.getGeoLocation().getLongitude());
    strBuilder.append("Address: Latitude:").append(address.getGeoLocation().getLatitude());
    return strBuilder.toString();
  }

}
