/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.service.EntryService;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import com.smartitengineering.smartpos.inventory.impl.service.ProductServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.StoreServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.SupplierServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.UomServiceImpl;
import com.smartitengineering.util.bean.BeanFactoryRegistrar;
import com.smartitengineering.util.bean.annotations.Aggregator;
import com.smartitengineering.util.bean.annotations.InjectableField;

/**
 *
 * @author russel
 */
@Aggregator(contextName = "com.smartitengineering.smartpos.inventory.api.service")
public class Services {

  @InjectableField
  private StoreService storeService;
  @InjectableField
  private ProductService productService;
  @InjectableField
  private UomService uomService;
  @InjectableField
  private SupplierService supplierService;
  @InjectableField
  private EntryService entryService;
  

  private Services(){    
  }

  public StoreService getStoreService() {
    //return storeService;
    return new StoreServiceImpl();
  }

  public void setStoreService(StoreService storeService) {
    this.storeService = storeService;
  }

  public ProductService getProductService() {
    //return productService;
    return new ProductServiceImpl();
  }

  public void setProductService(ProductService productService) {
    this.productService = productService;
  }  

  public SupplierService getSupplierService() {
    //return supplierService;
    return new SupplierServiceImpl();
  }

  public void setSupplierService(SupplierService supplierService) {
    this.supplierService = supplierService;
  }

  public UomService getUomService() {
    //return uomService;
    return new UomServiceImpl();
  }

  public void setUomService(UomService uomService) {
    this.uomService = uomService;
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
