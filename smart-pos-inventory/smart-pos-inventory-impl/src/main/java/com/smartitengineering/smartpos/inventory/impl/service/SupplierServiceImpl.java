/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class SupplierServiceImpl extends AbstractSupplierService implements SupplierService{

  protected final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

  public SupplierServiceImpl(){
  }

  @Override
  public void save(PersistantSupplier supplier) {
    commonWriteDao.save(supplier);
  }

  @Override
  public void update(PersistantSupplier supplier) {
    commonWriteDao.update(supplier);
  }

  @Override
  public void delete(PersistantSupplier supplier) {
    commonWriteDao.delete(supplier);
  }

  @Override
  public void getAllUoms() {
    throw new UnsupportedOperationException("Not supported yet.");
  }  

  @Override
  public PersistantSupplier getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public PersistantSupplier getByOrganizationAndSname(String organizatinUniqueShortName, String supplierName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<PersistantSupplier> getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
