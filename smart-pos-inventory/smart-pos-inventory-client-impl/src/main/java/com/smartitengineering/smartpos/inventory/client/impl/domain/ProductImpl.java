/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Product;

/**
 *
 * @author saumitra
 */
public class ProductImpl implements Product{

  private String id;
  private String name;
  private String description;
  private String skuId;
  private String skuName;

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setSkuId(String skuId) {
    this.skuId = skuId;
  } 

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getSkuId() {
    return skuId;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id){
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }  

}
