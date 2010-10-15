/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class Supplier {

  protected static final Logger logger = LoggerFactory.getLogger(Supplier.class);
  private String id;
  private String name;
  private String email;
  private String contactNumber;
  private Address address;
  @JsonIgnore
  private String orgUniqueShortName;

  public String getId() {
    if (id == null) {
      logger.error("id should not null");
      throw new RuntimeException("Id should not null");
    }
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Address getAddress() {
    if (address == null) {
      address = new Address();
    }
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getContactNumber() {
    if (contactNumber == null) {
      contactNumber = "";
    }
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmail() {
    if (email == null) {
      email = "";
    }
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    if (name == null) {
      name = "";
      logger.info("name is null");
    }
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
