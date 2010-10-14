/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.inventory.adapter;

import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.util.bean.adapter.AbstractAdapterHelper;

/**
 *
 * @author russel
 */
public class SupplierAdapterHelper extends AbstractAdapterHelper<Supplier, PersistantSupplier>{

  @Override
  protected PersistantSupplier newTInstance() {
    return new PersistantSupplier();
  }

  @Override
  protected void mergeFromF2T(Supplier fromBean, PersistantSupplier toBean) {
    toBean.setId(new PersistantSupplier.SupplierIdImpl(fromBean.getOrgUniqueShortName(), fromBean.getId()));
    toBean.setAddress(fromBean.getAddress());
    toBean.setContactNumber(fromBean.getContactNumber());
    toBean.setEmail(fromBean.getEmail());
    toBean.setName(fromBean.getName());
  }

  @Override
  protected Supplier convertFromT2F(PersistantSupplier toBean) {
    Supplier supplier = new Supplier();
    supplier.setId(toBean.getId().getId());
    supplier.setContactNumber(toBean.getContactNumber());
    supplier.setEmail(toBean.getEmail());
    supplier.setName(toBean.getName());
    supplier.setAddress(toBean.getAddress());
    return supplier;
  }

}
