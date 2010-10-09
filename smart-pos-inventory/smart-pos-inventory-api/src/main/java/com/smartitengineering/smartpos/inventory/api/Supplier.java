/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class Supplier extends AbstractGenericPersistentDTO<Supplier, SupplierId, Long>{

  private String name;
  private String email;
  private String contactNumber;
  private Address address;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isValid(){
    if(StringUtils.isBlank(name))
      return false;
    return true;
  }
}
