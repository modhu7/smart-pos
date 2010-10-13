/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;

/**
 *
 * @author russel
 */
public abstract class AbstractProductService implements ProductService{

  @Inject
  protected CommonReadDao<PersistantProduct, ProductId> commonReadDao;
  @Inject
  protected CommonWriteDao<PersistantProduct> commonWriteDao;

  public PersistantProduct getById(ProductId id){
    if(id == null)
      return null;
    return commonReadDao.getById(id);
  }
}
