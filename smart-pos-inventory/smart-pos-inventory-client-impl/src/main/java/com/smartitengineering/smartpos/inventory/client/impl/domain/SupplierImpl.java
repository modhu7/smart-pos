/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Supplier;

/**
 *
 * @author saumitra
 */
public class SupplierImpl implements Supplier{

  private String name;
  private String email;
  private String contactNumber;

  @Override
  public String getName() {
    return name;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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

}
