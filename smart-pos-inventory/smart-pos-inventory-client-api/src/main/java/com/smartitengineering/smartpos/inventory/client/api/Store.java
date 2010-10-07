/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

import com.smartitengineering.user.client.api.Address;
import com.smartitengineering.user.client.api.Organization;

/**
 *
 * @author saumitra
 */
public interface Store {

  public String getName();

  public String getCode();

  public Organization getOrganization();

  public Address getAddress();

  public Integer getOrganizationID();

  public  String[] getProductIds();

}
