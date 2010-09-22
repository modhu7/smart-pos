/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.converter.UOMRowConverter;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author russel
 */
public class UomServiceImpl implements UomService {

  private CommonDao<UnitOfMeasurement, String> commonDao;

  {
    commonDao = new CommonDao<UnitOfMeasurement, String>();
    commonDao.setExecutorService(ProductServiceImpl.getAsyncExecutorService());
    SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
    providerImpl.setMainTableName("uom");
    commonDao.setInfoProvider(providerImpl);
    commonDao.setConverter(new UOMRowConverter());
  }
  

  @Override
  public void save(UnitOfMeasurement uom) {
    commonDao.save(uom);
  }

  @Override
  public void update(UnitOfMeasurement uom) {
    commonDao.update(uom);
  }

  @Override
  public void delete(UnitOfMeasurement uom) {
    commonDao.delete(uom);
  }

  @Override
  public void getAllUoms() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName, String name,
                                                         boolean isSmallerThan,
                                                         int count) {
    //throw new UnsupportedOperationException("Not supported yet.");
    List<UnitOfMeasurement> uomList = new ArrayList<UnitOfMeasurement>();
    UnitOfMeasurement uom1 = new UnitOfMeasurement();
    uom1.setId("UOM 1");

    uomList.add(uom1);

    UnitOfMeasurement uom2 = new UnitOfMeasurement();
    uom2.setId("UOM 2");

    uomList.add(uom2);

    Collection<UnitOfMeasurement> uoms = uomList;
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

  @Override
  public UnitOfMeasurement getById(String uomId) {
    UnitOfMeasurement measurement = commonDao.getById(uomId);
    return measurement;
  }
}
