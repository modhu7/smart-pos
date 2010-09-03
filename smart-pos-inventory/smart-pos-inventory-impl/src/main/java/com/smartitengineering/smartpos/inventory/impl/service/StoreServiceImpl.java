/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.inventory.api.Store;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author russel
 */
public class StoreServiceImpl implements StoreService {

  @Override
  public void save(Store store) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(Store store) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Store store) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Store> getAllStores() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Store> getStores(String storeCodeLike, String storeCode, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Store> getByOrganization(String organizationUniqueShortName, String storeCode, boolean isSmallerThan,
                                             int count) {

    List<Store> storeList = new ArrayList<Store>();
    Store store1 = new Store();
    store1.setName("Store 1");
    store1.setCode("S1");
    storeList.add(store1);

    Store store2 = new Store();
    store2.setName("Store 2");
    store2.setCode("S2");
    storeList.add(store2);

    Collection<Store> stores = storeList;
    return stores;
  }

  @Override
  public Store getByStoreCodeAndOrganization(String organizationUniqueShortName, String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Store getByCode(String storeCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
