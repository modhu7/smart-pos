/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api;

/**
 *
 * @author russel
 */
public class UnitOfMeasurement {

  private String id;

  private String longName;

  private String symbol;

  private String uomType;

  private String uomSystem;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
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
}
