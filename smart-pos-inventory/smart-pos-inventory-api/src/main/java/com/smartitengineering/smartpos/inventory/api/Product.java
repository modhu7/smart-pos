/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class Product {

  private String id;

  @JsonIgnore
  private String orgUniqueShortName;

  private String name;

  private String description;

  private String skuId;

  private String skuName;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonIgnore
  public String getOrgUniqueShortName() {
    return orgUniqueShortName;
  }

  @JsonIgnore
  public void setOrgUniqueShortName(String orgUniqueShortName) {
    this.orgUniqueShortName = orgUniqueShortName;
  }

  public String getSkuId() {
    return skuId;
  }

  public void setSkuId(String skuId) {
    this.skuId = skuId;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }
}
