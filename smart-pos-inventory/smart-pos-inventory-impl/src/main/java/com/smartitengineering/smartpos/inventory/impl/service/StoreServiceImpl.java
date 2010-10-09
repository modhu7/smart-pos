/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.common.queryparam.MatchMode;
import com.smartitengineering.dao.common.queryparam.QueryParameter;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.AsyncExecutorService;
import com.smartitengineering.dao.impl.hbase.spi.impl.MixedExecutorServiceImpl;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.Store;
import com.smartitengineering.smartpos.inventory.api.converter.StoreRowConverter;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;
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
public class StoreServiceImpl extends AbstractStoreService implements StoreService {

  protected final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

//  private CommonDao<Store, String> storeDao;
//  private StoreRowConverter storeRowConverter;
//
//  private static final MixedExecutorServiceImpl executorService = new MixedExecutorServiceImpl();
//
//  static {
//    executorService.setConfiguration(HBaseConfiguration.create());
//  }
//
//  public static AsyncExecutorService getAsyncExecutorService() {
//    return executorService;
//  }

  
  public StoreServiceImpl(){
//    storeRowConverter = new StoreRowConverter();
//    storeDao = new CommonDao<Store, String>();
//    storeDao.setExecutorService(StoreServiceImpl.getAsyncExecutorService());
//    SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
//    providerImpl.setMainTableName("store");
//    storeDao.setInfoProvider(providerImpl);
//    storeDao.setConverter(storeRowConverter);
  }

  @Override
  public void save(Store store) {
    commonWriteDao.save(store);
  }

  @Override
  public void update(Store store) {
    commonWriteDao.update(store);
  }

  @Override
  public void delete(Store store) {
    commonWriteDao.delete(store);
  }

  @Override
  public Collection<Store> getAllStores() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Store> getStores(String storeCodeLike, String storeCode, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Store> getByOrganization(String organizationUniqueShortName, String storeCode, boolean isSmallerThan,
                                             int count) {

    QueryParameter qp = QueryParameterFactory.getStringLikePropertyParam("code", storeCode, MatchMode.START);

    Collection<Store> stores = commonReadDao.getList(qp);

//    List<Store> storeList = new ArrayList<Store>();
//    Store store1 = new Store();
//    store1.setName("Store 1");
//    store1.setCode("S1");
//    storeList.add(store1);
//
//    Store store2 = new Store();
//    store2.setName("Store 2");
//    store2.setCode("S2");
//    storeList.add(store2);
//
//
//
//    Collection<Store> stores = storeList;
    return stores;
  }

  @Override
  public Store getByStoreCodeAndOrganization(String organizationUniqueShortName, String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Store getByCode(String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
