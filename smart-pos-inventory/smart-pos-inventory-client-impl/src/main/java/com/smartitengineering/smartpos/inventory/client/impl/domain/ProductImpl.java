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

  private String name;
  private String description;
  private String skuId;
  private Integer orgId;

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public void setSkuId(String skuId) {
    this.skuId = skuId;
  }

  @Override
  public String getProductName() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Integer getOrganizationId() {
    return orgId;
  }

  @Override
  public String getSkuId() {
    return skuId;
  }

}
