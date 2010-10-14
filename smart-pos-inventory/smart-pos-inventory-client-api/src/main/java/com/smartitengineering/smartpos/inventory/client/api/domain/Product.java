/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

/**
 *
 * @author saumitra
 */
public interface Product {

  public String getId();

  public void setId(String id);

  public String getName();

  public void setName(String name);

  public String getDescription();

  public void setDescription(String description);

  public String getSkuId();

  public void setSkuId(String skuId);

}
