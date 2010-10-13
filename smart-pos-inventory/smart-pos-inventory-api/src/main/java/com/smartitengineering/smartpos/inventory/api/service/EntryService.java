/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.PersistantEntry;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author russel
 */
public interface EntryService {

  public void save(PersistantEntry entry);

  public void update(PersistantEntry product);

  public void delete(PersistantEntry product);

  public void getAllEntries();

  // non paginated version
  public Collection<PersistantEntry> getByOrganization(String organizationUniqueShortName);

  // paginated version (pagination by entry date)
  public Collection<PersistantEntry> getByOrganization(String organizationUniqueShortName, Date entryDate, boolean isSmallerThan,
                                             int count);

  // non paginated version
  public Collection<PersistantEntry> getByOrganizationAndType(String organizationUniqueShortName, PersistantEntry.TransactionType type);

  // paginated version (pagination by entry date)
  public Collection<PersistantEntry> getByOrganizationAndType(String organizationUniqueShortName, PersistantEntry.TransactionType type,
                                                    Date entryDate, boolean isSmallerThan, int count);

  // non paginated version
  public Collection<PersistantEntry> getByOrganizationAndStore(String organizationUniqueShortName, String storeCode);

  // paginated version (pagination by entry date)
  public Collection<PersistantEntry> getByOrganizationAndStore(String organizationUniqueShortName, String storeCode,
                                                     Date entryDate, boolean isSmallerThan, int count);

  // non paginated version
  public Collection<PersistantEntry> getByOrganizationAndProduct(String organizationUniqueShortName, String productCode);

  // paginated version (pagination by entry date)
  public Collection<PersistantEntry> getByOrganizationAndProduct(String organizationUniqueShortName, String productCode,
                                                       Date entryDate, boolean isSmallerThan, int count);

  // non paginated version
  public Collection<PersistantEntry> getByOrganizationAndProductAndStore(String organizationUniqueShortName, String productCode,
                                                               String storeCode);

  // paginated version (pagination by entry date)
  public Collection<PersistantEntry> getByOrganizationAndProductAndStore(String organizationUniqueShortName, String productCode,
                                                               String storeCode, Date entryDate, boolean isSmallerThan,
                                                               int count);

  public PersistantEntry getByOrganizationAndEntryDate(String organizationUniqueShortName, Date entryDate);
}
