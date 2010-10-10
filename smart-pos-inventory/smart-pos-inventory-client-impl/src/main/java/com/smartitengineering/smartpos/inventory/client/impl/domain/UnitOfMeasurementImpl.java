/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author russel
 */
public class UnitOfMeasurementImpl implements UnitOfMeasurement{

  private String id;
  //private String name;
  private String uomType;
  private String uomSystem;
  private String symbol;

  public void setId(String id) {
    this.id = id;
  }

//  @JsonIgnore
//  public void setName(String name) {
//    this.name = name;
//  }

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

//  @Override
//  public String getName() {
//    return name;
//  }

  @Override
  public String getSymbol() {
    return symbol;
  }

  @Override
  public String getUomType() {
    return uomType;
  }

  @Override
  public String getUomSystem() {
    return uomSystem;
  }
}
