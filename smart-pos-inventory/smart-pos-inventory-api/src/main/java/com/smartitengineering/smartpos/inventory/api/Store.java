/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractPersistentDTO;
import com.smartitengineering.domain.PersistentDTO;
import com.smartitengineering.smartpos.admin.api.Address;
import com.smartitengineering.smartpos.admin.api.Organization;

/**
 *
 * @author russel
 */
public class Store extends AbstractPersistentDTO<Store>{

  private String name;

  private Organization organization;
  
  private Address address;

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
