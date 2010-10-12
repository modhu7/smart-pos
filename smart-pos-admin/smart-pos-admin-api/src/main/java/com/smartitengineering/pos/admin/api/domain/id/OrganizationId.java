/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.api.domain.id;

import com.smartitengineering.dao.impl.hbase.spi.Externalizable;

/**
 *
 * @author modhu7
 */
public interface OrganizationId extends Externalizable, Comparable<OrganizationId> {

  public String getId();

  public void setId(String id);

  @Override
  public String toString();
}
