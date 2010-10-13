/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.domainid;

import com.smartitengineering.dao.impl.hbase.spi.Externalizable;
import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author russel
 */
@JsonDeserialize(as = PersistantUnitOfMeasurement.UomIdImpl.class)
public interface UomId extends Externalizable, Comparable<UomId>{

  public String getId();

  public void setId(String id);

  public String getCustomId();

  /**
   * Override the toString so that it could be used to compare to ids of this instance. It should represent the state
   * of the Id.
   * @return String representation, i.e. state, of the id
   */
  @Override
  public String toString();
}
