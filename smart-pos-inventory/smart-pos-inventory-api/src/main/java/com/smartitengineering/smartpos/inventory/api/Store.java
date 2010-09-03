/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractPersistentDTO;
import com.smartitengineering.domain.PersistentDTO;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.Organization;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class Store extends AbstractPersistentDTO<Store>{

  private String name;

  private String code;

  private Organization organization;
  
  private Address address;

  private Integer organizationID;

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

  public boolean isValid(){
    return true;
  }

}
