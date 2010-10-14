/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Address;

/**
 *
 * @author saumitra
 */
public interface Store {

  public String getName();

  public void setName(String name);

  public String getId();

  public void setId(String id);

  public Address getAddress();

  public void setAddress(Address address);

}
