/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class Supplier{

  private String id;
  private String name;
  private String email;
  private String contactNumber;
  private Address address;
  @JsonIgnore
  private String orgUniqueShortName;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  @JsonIgnore
  public String getOrgUniqueShortName() {
    return orgUniqueShortName;
  }

  @JsonIgnore
  public void setOrgUniqueShortName(String orgUniqueShortName) {
    this.orgUniqueShortName = orgUniqueShortName;
  }

  
}
