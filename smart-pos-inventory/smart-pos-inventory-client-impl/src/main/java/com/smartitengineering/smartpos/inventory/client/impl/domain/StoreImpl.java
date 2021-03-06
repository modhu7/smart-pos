/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Address;
import com.smartitengineering.smartpos.inventory.client.api.domain.Store;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author saumitra
 */
public class StoreImpl implements Store{

  private String name;
  private String id;
  private Address address;


  @Override
  public String getName() {
    return name;
  }

  @Override
  @JsonDeserialize(as = AddressImpl.class)
  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }  

  @Override
  public String getId() {
    return id;
  }  

  @Override
  public Address getAddress() {
    return address;
  }  
}
