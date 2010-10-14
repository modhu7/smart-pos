/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.common.queryparam.MatchMode;
import com.smartitengineering.dao.common.queryparam.QueryParameter;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class ProductServiceImpl extends AbstractProductService implements ProductService {

  protected final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  public ProductServiceImpl() {
  }

  @Override
  public void save(PersistantProduct product) {
    commonWriteDao.save(product);
  }

  @Override
  public void update(PersistantProduct product) {
    commonWriteDao.update(product);
  }

  @Override
  public void delete(PersistantProduct product) {
    commonWriteDao.delete(product);
  }

  @Override
  public Collection<PersistantProduct> getAllProducts() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PersistantProduct> getProducts(String productCodeLike, String productCode, boolean isSmallerThan,
                                                   int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PersistantProduct> getByOrganization(String organizationUniqueShortName, String productCode,
                                                         boolean isSmallerThan, int count) {

    QueryParameter qp = QueryParameterFactory.getStringLikePropertyParam("id", organizationUniqueShortName,
                                                                         MatchMode.START);

    Collection<PersistantProduct> products = commonReadDao.getList(qp);
    return products;
    //throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PersistantProduct getByProductCodeAndOrganization(String organizationUniqueShortName, String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PersistantProduct getByCode(String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
