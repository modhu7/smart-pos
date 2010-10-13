/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

/**
 *
 * @author saumitra
 */
public interface UnitOfMeasurement{

  public String getId();

  public void setId(String id);

  public String getSymbol();

  public void setSymbol(String symbol);

  public String getUomType();         // length, weight.. etc etc

  public void setUomType(String type);

  public String getUomSystem();       // metric system, SI system, etc

  public void setUomSystem(String system);

  public void setLongName(String longName);

  public String getLongName();


}


