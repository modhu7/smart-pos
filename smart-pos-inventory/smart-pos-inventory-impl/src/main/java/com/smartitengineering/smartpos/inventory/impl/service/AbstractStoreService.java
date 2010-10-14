/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;

/**
 *
 * @author russel
 */
public abstract class AbstractStoreService implements StoreService{
  @Inject
  protected CommonReadDao<PersistantStore, StoreId> commonReadDao;
  @Inject
  protected CommonWriteDao<PersistantStore> commonWriteDao;

  @Override
  public PersistantStore getById(StoreId id){
    if(id == null)
      return null;
    return commonReadDao.getById(id);
  }
}
