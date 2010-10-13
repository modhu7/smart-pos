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
public class StoreAdapterHelper extends AbstractAdapterHelper<Store, PersistantStore>{

  @Override
  protected PersistantStore newTInstance() {
    return new PersistantStore();
  }

  @Override
  protected void mergeFromF2T(Store fromBean, PersistantStore toBean) {
    toBean.setId(new PersistantStore.StoreIdImpl(fromBean.getOrgUniqueShortName(), fromBean.getId()));
    toBean.setName(fromBean.getName());
    toBean.setAddress(fromBean.getAddress());
  }

  @Override
  protected Store convertFromT2F(PersistantStore toBean) {
    Store store = new Store();

    store.setId(toBean.getId().getId());
    store.setOrgUniqueShortName(toBean.getId().getOrgUniqeShortName());
    store.setName(toBean.getName());
    store.setAddress(toBean.getAddress());
    
    return store;
  }

}
