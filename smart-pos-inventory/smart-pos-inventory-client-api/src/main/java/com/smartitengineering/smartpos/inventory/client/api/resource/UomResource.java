/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
import com.smartitengineering.util.rest.client.WritableResource;
import org.apache.abdera.model.Feed;

/**
 *
 * @author saumitra
 */
public interface UomResource extends WritableResource<Feed>{
  
  public UnitOfMeasurement getUnitOfMeasurement();

   public void update();
   
}
