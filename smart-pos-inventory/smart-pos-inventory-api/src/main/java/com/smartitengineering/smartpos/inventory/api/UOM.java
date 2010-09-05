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
public class UOM extends AbstractPersistentDTO<UOM>{

  private String name;
  private String symbol;
  private String uomType;         // length, weight.. etc etc
  private String uomSystem;       // metric system, SI system, etc
  private Organization organization;

  private Integer organizationID;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getUomSystem() {
    return uomSystem;
  }

  public void setUomSystem(String uomSystem) {
    this.uomSystem = uomSystem;
  }

  public String getUomType() {
    return uomType;
  }

  public void setUomType(String uomType) {
    this.uomType = uomType;
  }

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public Integer getOrganizationID() {
    return organizationID;
  }

  public void setOrganizationID(Integer organizationID) {
    this.organizationID = organizationID;
  }

  public boolean isValid(){
    if(StringUtils.isBlank(name) || StringUtils.isBlank(symbol))
      return false;
    return true;
  }
}
