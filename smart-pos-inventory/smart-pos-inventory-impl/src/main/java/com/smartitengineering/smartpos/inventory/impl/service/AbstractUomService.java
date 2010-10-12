/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import com.smartitengineering.smartpos.inventory.api.service.UomService;

/**
 *
 * @author russel
 */
public abstract class AbstractUomService implements UomService{

  @Inject
  protected CommonReadDao<UnitOfMeasurement, UomId> commonReadDao;
  @Inject
  protected CommonWriteDao<UnitOfMeasurement> commonWriteDao;

  @Override
  public UnitOfMeasurement getById(UomId id){
    if(id == null)
      return null;
    return commonReadDao.getById(id);
  }
}
