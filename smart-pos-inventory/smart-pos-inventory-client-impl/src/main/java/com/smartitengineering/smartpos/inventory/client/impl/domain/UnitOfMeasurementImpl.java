/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class UnitOfMeasurementImpl implements UnitOfMeasurement{

  private String id;
  private String longName;
  private String uomType;
  private String uomSystem;
  private String symbol;

  public void setId(String id) {
    this.id = id;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public void setUomSystem(String uomSystem) {
    this.uomSystem = uomSystem;
  }

  public void setUomType(String uomType) {
    this.uomType = uomType;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getSymbol() {
    return symbol;
  }

  @Override
  public String getUomType() {
    if(uomType == null){
      uomType = "";
    }
    return uomType;
  }

  @Override
  public String getUomSystem() {
    if(uomSystem == null){
      uomSystem = "";
    }
    return uomSystem;
  }

  @Override
  public void setLongName(String longName) {
    this.longName = longName;
  }

  @Override
  public String getLongName() {
    if(longName == null){
      longName = "";
    }
    return longName;
  }

//  public boolean isValid() {
//    if (StringUtils.isBlank(getId()) || StringUtils.isBlank(symbol)) {
//      return false;
//    }
//    return true;
//  }
}
