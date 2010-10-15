/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.impl.domainidinstanceprovider;

import com.smartitengineering.dao.impl.hbase.spi.DomainIdInstanceProvider;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.ProductId;
import com.smartitengineering.smartpos.inventory.api.domainid.StoreId;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;

/**
 *
 * @author russel
 */
public class DomainIdInstanceProviderImpl implements DomainIdInstanceProvider {

  @Override
  public <IdType> IdType getInstance(Class<? extends IdType> clazz) {
    Object object = null;
    if (UomId.class.isAssignableFrom(clazz)) {
      object = new PersistantUnitOfMeasurement.UomIdImpl();
    }
    if(StoreId.class.isAssignableFrom(clazz)){
      object = new PersistantStore.StoreIdImpl();
    }
    if(ProductId.class.isAssignableFrom(clazz)){
      object = new PersistantProduct.ProductIdImpl();
    }
    if(SupplierId.class.isAssignableFrom(clazz)){
      object = new PersistantSupplier.SupplierIdImpl();
    }
    return (IdType) object;
  }
}
