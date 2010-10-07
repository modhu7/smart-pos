/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class Product extends AbstractGenericPersistentDTO<Product, ProductId, Long>{

  private String name;
  private String description;
  private Integer organizationId;
  private String skuId;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Integer parentOrganizationID) {
    this.organizationId = parentOrganizationID;
  }

  public String getSkuId() {
    return skuId;
  }

  public void setSkuId(String skuID) {
    this.skuId = skuID;
  }

  public boolean isValid(){
    if(StringUtils.isBlank(name))
      return false;
    return true;
  }

}
