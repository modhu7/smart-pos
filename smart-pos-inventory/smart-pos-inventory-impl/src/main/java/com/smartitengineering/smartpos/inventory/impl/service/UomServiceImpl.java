/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.common.queryparam.MatchMode;
import com.smartitengineering.dao.common.queryparam.QueryParameter;
import com.smartitengineering.dao.common.queryparam.QueryParameterFactory;
import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.converter.UOMRowConverter;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class UomServiceImpl extends AbstractUomService implements UomService {

  protected final Logger logger = LoggerFactory.getLogger(UomServiceImpl.class);

  //private CommonDao<UnitOfMeasurement, String> commonDao;

  public UomServiceImpl(){
    //commonDao = new CommonDao<UnitOfMeasurement, String>();
    //commonDao.setExecutorService(ProductServiceImpl.getAsyncExecutorService());
    //SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
    //providerImpl.setMainTableName("uom");
    //commonDao.setInfoProvider(providerImpl);
    //commonDao.setConverter(new UOMRowConverter());
  }
  

  @Override
  public void save(UnitOfMeasurement uom) {
    try{
      commonWriteDao.save(uom);
    }catch(Exception ex){
      logger.error(ex.getMessage());
    }
  }

  @Override
  public void update(UnitOfMeasurement uom) {
    commonWriteDao.update(uom);
  }

  @Override
  public void delete(UnitOfMeasurement uom) {
    commonWriteDao.delete(uom);
  }

  @Override
  public Collection<UnitOfMeasurement> getAllUoms() {
    final List<UnitOfMeasurement> list = commonReadDao.getList();
    if(list == null  || list.isEmpty())
      return Collections.emptyList();
    return list;
  }

  @Override
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<UnitOfMeasurement> getByOrganization(String organizationUniqueShortName, String name,
                                                         boolean isSmallerThan,
                                                         int count) {

    QueryParameter qp = QueryParameterFactory.getStringLikePropertyParam("id", organizationUniqueShortName, MatchMode.START);
    List<UnitOfMeasurement> uoms = commonReadDao.getList();

    //throw new UnsupportedOperationException("Not supported yet.");
    return uoms;
  }

  @Override
  public UnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public UnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name,
                                                   boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }  
}
