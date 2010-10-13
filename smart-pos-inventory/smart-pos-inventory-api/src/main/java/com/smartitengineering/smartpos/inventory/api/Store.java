/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.smartpos.admin.api.Address;

/**
 *
 * @author russel
 */
public class Store {

  private String id;
  private String name;
  private String orgUniqueShortName;
  private Address address;

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrgUniqueShortName() {
    return orgUniqueShortName;
  }

  public void setOrgUniqueShortName(String orgUniqueShortName) {
    this.orgUniqueShortName = orgUniqueShortName;
  }
}
