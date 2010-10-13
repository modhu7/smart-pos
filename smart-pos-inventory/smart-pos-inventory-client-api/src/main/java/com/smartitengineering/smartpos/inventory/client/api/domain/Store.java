/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

import com.smartitengineering.user.client.api.Address;
import com.smartitengineering.user.client.api.Organization;

/**
 *
 * @author saumitra
 */
public interface Store {

  public String getName();

  public String getId();  

  public Address getAddress();

}
