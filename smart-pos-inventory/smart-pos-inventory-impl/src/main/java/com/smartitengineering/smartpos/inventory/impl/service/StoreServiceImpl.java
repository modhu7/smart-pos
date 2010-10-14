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
import com.smartitengineering.smartpos.inventory.api.PersistantStore;
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



  
  public StoreServiceImpl(){
  }

  @Override
  public void save(PersistantStore store) {
    commonWriteDao.save(store);
  }

  @Override
  public void update(PersistantStore store) {
    commonWriteDao.update(store);
  }

  @Override
  public void delete(PersistantStore store) {
    commonWriteDao.delete(store);
  }

  @Override
  public Collection<PersistantStore> getAllStores() {
    return commonReadDao.getAll();
  }

  @Override
  public Collection<PersistantStore> getStores(String storeCodeLike, String storeCode, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PersistantStore> getByOrganization(String organizationUniqueShortName, String storeCode, boolean isSmallerThan,
                                             int count) {

    QueryParameter qp = QueryParameterFactory.getStringLikePropertyParam("id", organizationUniqueShortName, MatchMode.START);

    Collection<PersistantStore> stores = commonReadDao.getList(qp);
    return stores;
  }

  @Override
  public PersistantStore getByStoreCodeAndOrganization(String organizationUniqueShortName, String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PersistantStore getByCode(String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
