/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;



/**
 *
 * @author saumitra
 */
public interface Supplier {

  public String getId();
  
  public void setId(String id);

  public  String getName();

  public void setName(String name);

  public  String getEmail();

  public void setEmail(String email);

  public  String getContactNumber();

  public void setContactNumber(String contactNumber);
  
  public  Address getAddress();

  public void setAddress(Address address);
}
