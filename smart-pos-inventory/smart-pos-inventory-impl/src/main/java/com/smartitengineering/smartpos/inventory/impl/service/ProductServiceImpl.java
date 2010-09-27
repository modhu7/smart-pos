/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.AsyncExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.api.converter.ProductRowConverter;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

  protected final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  private ProductRowConverter productContverter;
  private CommonDao<Product, String> productDao;
  private static final MixedExecutorServiceImpl executorService = new MixedExecutorServiceImpl();

  static {
    executorService.setConfiguration(HBaseConfiguration.create());
  }

  public static AsyncExecutorService getAsyncExecutorService() {
    return executorService;
  }

  public ProductServiceImpl() {
    productContverter = new ProductRowConverter();
    productDao = new CommonDao<Product, String>();
    productDao.setConverter(productContverter);
    productDao.setExecutorService(getAsyncExecutorService());
    SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
    providerImpl.setMainTableName("product");
    //Add FilterConfig 
    productDao.setInfoProvider(providerImpl);
  }

  @Override
  public void save(Product product) {
    productDao.save(product);
  }

  @Override
  public void update(Product product) {
    productDao.update(product);
  }

  @Override
  public void delete(Product product) {
    productDao.delete(product);
  }

  @Override
  public Collection<Product> getAllProducts() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Product> getProducts(String productCodeLike, String productCode, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Product> getByOrganization(String organizationUniqueShortName, String productCode,
                                               boolean isSmallerThan, int count) {

    List<Product> productList = new ArrayList<Product>();
    Product product1 = new Product();
    product1.setName("Product 1");
    product1.setId("P1");
    productList.add(product1);

    Product product2 = new Product();
    product2.setName("Product 2");
    product2.setId("P2");
    productList.add(product2);

    Collection<Product> products = productList;
    return products;
    //throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Product getByProductCodeAndOrganization(String organizationUniqueShortName, String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Product getByCode(String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
