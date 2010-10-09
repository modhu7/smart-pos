/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.dao.impl.hbase.CommonDao;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderImpl;
import com.smartitengineering.domain.PersistentDTO;
import com.smartitengineering.smartpos.inventory.api.Entry;
import com.smartitengineering.smartpos.inventory.api.Entry.TransactionType;
import com.smartitengineering.smartpos.inventory.api.service.EntryService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 *
 * @author russel
 */
public class EntryServiceImpl extends AbstractEntryService implements EntryService{

  protected final Logger logger = LoggerFactory.getLogger(EntryServiceImpl.class);
//  private CommonDao<Entry, String> commonDao;
//
//  {
//    commonDao = new CommonDao<Entry, String>();
//    commonDao.setExecutorService(ProductServiceImpl.getAsyncExecutorService());
//    SchemaInfoProviderImpl providerImpl = new SchemaInfoProviderImpl();
//    providerImpl.setMainTableName("uom");
//    commonDao.setInfoProvider(providerImpl);
//    //commonDao.setConverter(new UOMRowConverter());
//  }
  
  @Override
  public void save(Entry entry) {
    commonWriteDao.save(entry);
  }

  @Override
  public void update(Entry entry) {
    commonWriteDao.update(entry);
  }

  @Override
  public void delete(Entry entry) {
    commonWriteDao.delete(entry);
  }

  @Override
  public void getAllEntries() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganization(String organizationUniqueShortName) {

    List<Entry> entryList = new ArrayList<Entry>();
    Entry entry1 = new Entry();
    entry1.setEntryDate(new Date());    
    entryList.add(entry1);

    Entry entry2 = new Entry();
    entry2.setEntryDate(new Date());    
    entryList.add(entry2);

    Collection<Entry> entries = entryList;
    return entries;
  }

  @Override
  public Collection<Entry> getByOrganization(String organizationUniqueShortName, Date entryDate, boolean isSmallerThan,
                                             int count) {
    //throw new UnsupportedOperationException("Not supported yet.");
    List<Entry> entryList = new ArrayList<Entry>();
    Entry entry1 = new Entry();
    entry1.setEntryDate(new Date());  
    entryList.add(entry1);

    Entry entry2 = new Entry();
    entry2.setEntryDate(new Date());    
    entryList.add(entry2);

    Collection<Entry> entries = entryList;
    return entries;
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

  
  public Collection<Entry> getByOrganizationAndEntry(String organizationUniqueShortName, String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  
  public Collection<Entry> getByOrganizationAndEntry(String organizationUniqueShortName, String productCode,
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

  @Override
  public Entry getByOrganizationAndEntryDate(String organizationUniqueShortName, Date entryDate) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndType(String organizationUniqueShortName, TransactionType type) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Entry> getByOrganizationAndType(String organizationUniqueShortName, TransactionType type,
                                                    Date entryDate, boolean isSmallerThan, int count) {
    //throw new UnsupportedOperationException("Not supported yet.");
    List<Entry> entryList = new ArrayList<Entry>();
    Entry entry1 = new Entry();
    entry1.setQuantity(new Double(10));
    entry1.setProductId("SITEL:1");
    entry1.setStoreId("SITEL:1");
    
    entryList.add(entry1);
    
    Entry entry2 = new Entry();
    entry2.setQuantity(new Double(20));
    entry1.setProductId("SITEL:2");
    entry1.setStoreId("SITEL:2");
    
    entryList.add(entry2);
    
    return entryList;
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

}
