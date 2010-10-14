/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Address;
import com.smartitengineering.smartpos.inventory.client.api.domain.Supplier;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author saumitra
 */
public class SupplierImpl implements Supplier{

  private String id;
  private String name;
  private String email;
  private String contactNumber;
  private Address address;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getContactNumber() {
    return contactNumber;
  }

  @Override
  public Address getAddress() {
    return address;
  }

  @JsonDeserialize(as = AddressImpl.class)
  @Override  
  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

}
