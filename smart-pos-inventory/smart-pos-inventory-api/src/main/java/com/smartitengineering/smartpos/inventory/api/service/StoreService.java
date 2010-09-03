/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.Store;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface StoreService {

  public void save(Store store);

  public void update(Store store);

  public void delete(Store store);

  public Collection<Store> getAllStores();

  public Collection<Store> getStores(String storeCodeLike, String storeCode, boolean isSmallerThan, int count);

  public Collection<Store> getByOrganization(String organizationUniqueShortName, String storeCode, boolean isSmallerThan, int count);

  public Store getByStoreCodeAndOrganization(String organizationUniqueShortName, String storeCode);

  public Store getByCode(String storeCode);
  
}
