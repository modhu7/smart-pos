/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.AsyncExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.smartpos.inventory.api.converter.SupplierRowConverter;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class SupplierServiceImpl extends AbstractSupplierService implements SupplierService{

  protected final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

//  private CommonDao<Supplier, String> supplierDao;
//  private SupplierRowConverter supplierRowConverter;
//  private static final MixedExecutorServiceImpl executorService = new MixedExecutorServiceImpl();

//  static {
//    executorService.setConfiguration(HBaseConfiguration.create());
//  }

//  public static AsyncExecutorService getAsyncExecutorService() {
//    return executorService;
//  }

  public SupplierServiceImpl(){
//    supplierRowConverter = new SupplierRowConverter();
//    supplierDao = new CommonDao<Supplier, String>();
//    supplierDao.setExecutorService(getAsyncExecutorService());
//    SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
//    providerImpl.setMainTableName("supplier");
//    supplierDao.setInfoProvider(providerImpl);
//    supplierDao.setConverter(supplierRowConverter);
  }

  @Override
  public void save(Supplier supplier) {
    commonWriteDao.save(supplier);
  }

  @Override
  public void update(Supplier supplier) {
    commonWriteDao.update(supplier);
  }

  @Override
  public void delete(Supplier supplier) {
    commonWriteDao.delete(supplier);
  }

  @Override
  public void getAllUoms() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Supplier getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Supplier getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Supplier getByOrganizationAndSname(String organizatinUniqueShortName, String supplierName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
