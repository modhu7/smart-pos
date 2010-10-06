/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

import com.smartitengineering.user.client.api.Address;

/**
 *
 * @author saumitra
 */
public interface Supplier {

  public  String getName();

  public  String getEmail();

  public  String getContactNumber();
  
  public  Address getAddress();
}
