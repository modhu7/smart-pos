/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface StoreService {

  public void save(PersistantStore store);

  public void update(PersistantStore store);

  public void delete(PersistantStore store);

  public Collection<PersistantStore> getAllStores();

  public Collection<PersistantStore> getStores(String storeCodeLike, String storeCode, boolean isSmallerThan, int count);

  public Collection<PersistantStore> getByOrganization(String organizationUniqueShortName, String storeCode, boolean isSmallerThan, int count);

  public PersistantStore getByStoreCodeAndOrganization(String organizationUniqueShortName, String storeCode);

  public PersistantStore getByCode(String storeCode);
  
}
