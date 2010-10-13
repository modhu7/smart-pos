/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;

/**
 *
 * @author russel
 */
public abstract class AbstractSupplierService implements SupplierService{

  @Inject
  protected CommonReadDao<PersistantSupplier, SupplierId> commonReadDao;
  @Inject
  protected CommonWriteDao<PersistantSupplier> commonWriteDao;

  public PersistantSupplier getById(SupplierId id){
    if(id == null)
      return null;
    return commonReadDao.getById(id);
  }
}
