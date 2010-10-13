/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.AsyncExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.converter.ProductRowConverter;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import com.smartitengineering.smartpos.inventory.impl.domainid.ProductIdImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class ProductServiceImpl extends AbstractProductService implements ProductService {

  protected final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

//  private ProductRowConverter productContverter;
//  private CommonDao<PersistantProduct, String> productDao;
//  private static final MixedExecutorServiceImpl executorService = new MixedExecutorServiceImpl();
//
//  static {
//    executorService.setConfiguration(HBaseConfiguration.create());
//  }
//
//  public static AsyncExecutorService getAsyncExecutorService() {
//    return executorService;
//  }

  public ProductServiceImpl() {
//    productContverter = new ProductRowConverter();
//    productDao = new CommonDao<PersistantProduct, String>();
//    productDao.setConverter(productContverter);
//    productDao.setExecutorService(getAsyncExecutorService());
//    SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
//    providerImpl.setMainTableName("product");
//    //Add FilterConfig
//    productDao.setInfoProvider(providerImpl);
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
  public Collection<PersistantProduct> getProducts(String productCodeLike, String productCode, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PersistantProduct> getByOrganization(String organizationUniqueShortName, String productCode,
                                               boolean isSmallerThan, int count) {

    List<PersistantProduct> productList = new ArrayList<PersistantProduct>();
    PersistantProduct product1 = new PersistantProduct();
    product1.setName("Product 1");
    product1.setId(new ProductIdImpl("P1"));
    productList.add(product1);

    PersistantProduct product2 = new PersistantProduct();
    product2.setName("Product 2");
    product2.setId(new ProductIdImpl("P2"));
    productList.add(product2);

    Collection<PersistantProduct> products = productList;
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
