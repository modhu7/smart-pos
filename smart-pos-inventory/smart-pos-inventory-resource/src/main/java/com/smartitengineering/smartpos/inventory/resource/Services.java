/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.resource;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.smartitengineering.smartpos.inventory.api.service.EntryService;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import com.smartitengineering.util.bean.BeanFactoryRegistrar;
import com.smartitengineering.util.bean.annotations.Aggregator;
import com.smartitengineering.util.bean.annotations.InjectableField;

/**
 *
 * @author russel
 */
@Aggregator(contextName = Services.CONTEXT_NAME)
public class Services {

  public static final String CONTEXT_NAME = "com.smartitengineering.smartpos.inventory.api.service";

  @InjectableField(beanName="apiStoreService")
  private StoreService storeService;

  @InjectableField(beanName="apiProductService")
  private ProductService productService;

  @InjectableField(beanName="apiUomService")
  private UomService uomService;

  @InjectableField(beanName="apiSupplierService")
  private SupplierService supplierService;

  @InjectableField(beanName="apiEntryService")
  private EntryService entryService;
  

  private Services(){    
  }

  public StoreService getStoreService() {
    return storeService;    
  }

  public void setStoreService(StoreService storeService) {
    this.storeService = storeService;
  }

  public ProductService getProductService() {
    return productService;    
  }

  public void setProductService(ProductService productService) {
    this.productService = productService;
  }  

  public SupplierService getSupplierService() {
    return supplierService;    
  }

  public void setSupplierService(SupplierService supplierService) {
    this.supplierService = supplierService;
  }

  public UomService getUomService() {
    return uomService;    
  }

  public void setUomService(UomService uomService) {
    this.uomService = uomService;
  }

  public EntryService getEntryService() {
    return entryService;    
  }

  public void setEntryService(EntryService entryService) {
    this.entryService = entryService;
  }




  
  private static Services services;

  public static Services getInstance() {
    if (services == null) {
      services = new Services();
      BeanFactoryRegistrar.aggregate(services);
    }
    return services;
    
  }
}
