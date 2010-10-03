/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;

/**
 *
 * @author russel
 */
public abstract class AbstractProductService implements ProductService{

  @Inject
  protected CommonReadDao<Product, ProductId> commonReadDao;
  @Inject
  protected CommonWriteDao<Product> commonWriteDao;

  public Product getById(ProductId id){
    if(id == null)
      return null;
    return commonReadDao.getById(id);
  }
}
