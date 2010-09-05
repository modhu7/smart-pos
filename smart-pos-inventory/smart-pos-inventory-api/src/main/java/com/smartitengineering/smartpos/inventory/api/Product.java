/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractPersistentDTO;
import com.smartitengineering.domain.PersistentDTO;
import com.smartitengineering.smartpos.admin.api.Organization;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class Product extends AbstractPersistentDTO<Product>{

  private String name;
  private String productCode;
  private String description;
  private Organization organization;

  private Integer parentOrganizationID;

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

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }  

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public Integer getParentOrganizationID() {
    return parentOrganizationID;
  }

  public void setParentOrganizationID(Integer parentOrganizationID) {
    this.parentOrganizationID = parentOrganizationID;
  }

  public boolean isValid(){
    if(StringUtils.isBlank(name))
      return false;
    return true;
  }

}
