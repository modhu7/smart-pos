/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.inventory.api.Entry;
import com.smartitengineering.smartpos.inventory.api.service.EntryService;
import java.util.Collection;
import java.util.Date;



/**
 *
 * @author russel
 */
public class EntryServiceImpl implements EntryService{

  @Override
  public void save(Entry entry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(Entry product) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Entry product) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void getAllEntries() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganization(String organizationUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganization(String organizationUniqueShortName, Date entryDate, boolean isSmallerThan,
                                             int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndStore(String organizationUniqueShortName, String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndStore(String organizationUniqueShortName, String storeCode,
                                                     Date entryDate, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndProduct(String organizationUniqueShortName, String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndProduct(String organizationUniqueShortName, String productCode,
                                                       Date entryDate, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndProductAndStore(String organizationUniqueShortName, String productCode,
                                                               String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndProductAndStore(String organizationUniqueShortName, String productCode,
                                                               String storeCode, Date entryDate, boolean isSmallerThan,
                                                               int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
