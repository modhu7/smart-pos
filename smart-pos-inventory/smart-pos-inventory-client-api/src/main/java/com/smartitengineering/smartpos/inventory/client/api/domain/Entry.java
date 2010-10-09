/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

/**
 *
 * @author saumitra
 */
public interface Entry {

  public String getQuantity();

  public String gerEntryDate();

  public String getExpireDate();

  public String getType();

  public String getProductId();

  public String getStoreId();

  public Integer getOrganizationId();

}
