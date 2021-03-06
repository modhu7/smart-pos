/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.domainid;

import com.smartitengineering.dao.impl.hbase.spi.Externalizable;

/**
 *
 * @author russel
 */
public interface SupplierId extends Externalizable, Comparable<SupplierId>{
  public String getId();

  public void setId(String id);

  public String getCompositId();

  public String getOrgUniqueShortName();

  /**
   * Override the toString so that it could be used to compare to ids of this instance. It should represent the state
   * of the Id.
   * @return String representation, i.e. state, of the id
   */
  @Override
  public String toString();

}
