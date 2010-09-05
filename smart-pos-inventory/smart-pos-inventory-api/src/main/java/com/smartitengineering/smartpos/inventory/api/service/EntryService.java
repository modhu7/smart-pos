/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.Entry;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author russel
 */
public interface EntryService {

  public void save(Entry entry);

  public void update(Entry product);

  public void delete(Entry product);

  public void getAllEntries();

  // non paginated version
  public Collection<Entry> getByOrganization(String organizationUniqueShortName);

  // paginated version (pagination by entry date)
  public Collection<Entry> getByOrganization(String organizationUniqueShortName, Date entryDate, boolean isSmallerThan,
                                             int count);

  // non paginated version
  public Collection<Entry> getByOrganizationAndStore(String organizationUniqueShortName, String storeCode);

  // paginated version (pagination by entry date)
  public Collection<Entry> getByOrganizationAndStore(String organizationUniqueShortName, String storeCode,
                                                     Date entryDate, boolean isSmallerThan, int count);

  // non paginated version
  public Collection<Entry> getByOrganizationAndProduct(String organizationUniqueShortName, String productCode);

  // paginated version (pagination by entry date)
  public Collection<Entry> getByOrganizationAndProduct(String organizationUniqueShortName, String productCode,
                                                       Date entryDate, boolean isSmallerThan, int count);

  // non paginated version
  public Collection<Entry> getByOrganizationAndProductAndStore(String organizationUniqueShortName, String productCode,
                                                               String storeCode);

  // paginated version (pagination by entry date)
  public Collection<Entry> getByOrganizationAndProductAndStore(String organizationUniqueShortName, String productCode,
                                                               String storeCode, Date entryDate, boolean isSmallerThan,
                                                               int count);
}
