/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class UnitOfMeasurement extends AbstractGenericPersistentDTO<UnitOfMeasurement, String, Long> {

  private String name;
  private String symbol;
  private String uomType;         // length, weight.. etc etc
  private String uomSystem;       // metric system, SI system, etc
  private Integer organizationId;

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

  public Integer getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Integer organizationID) {
    this.organizationId = organizationID;
  }

  public boolean isValid() {
    if (StringUtils.isBlank(name) || StringUtils.isBlank(symbol)) {
      return false;
    }
    return true;
  }
}
