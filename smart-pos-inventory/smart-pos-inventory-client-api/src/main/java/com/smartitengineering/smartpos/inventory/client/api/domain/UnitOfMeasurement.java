/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.domain;

/**
 *
 * @author saumitra
 */
public interface UnitOfMeasurement {  

  public String getId();

  public String getName();

  public String getSymbol();

  public String getUomType();         // length, weight.. etc etc

  public String getUomSystem();       // metric system, SI system, etc  


}
