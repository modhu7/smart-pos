/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.inventory.adapter;

import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import com.smartitengineering.smartpos.inventory.api.Store;
import com.smartitengineering.util.bean.adapter.AbstractAdapterHelper;

/**
 *
 * @author russel
 */
public class StoreAdapter{

  public Store getStoreFromPersistantStore(PersistantStore persistantStore){
    Store store = new Store();
    store.setId(persistantStore.getId().getId());
    store.setName(persistantStore.getName());
    store.setAddress(persistantStore.getAddress());
    store.setOrgUniqueShortName(persistantStore.getId().getOrgUniqeShortName());

    return store;
  }

  public PersistantStore getPeristantStoreFromStore(Store store){
    PersistantStore persistantStore = new PersistantStore();
    persistantStore.setId(new PersistantStore.StoreIdImpl(store.getOrgUniqueShortName(), store.getId()));
    persistantStore.setName(store.getName());
    persistantStore.setAddress(store.getAddress());

    return persistantStore;
  }
}
