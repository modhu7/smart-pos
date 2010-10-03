/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.guicebinder;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import com.smartitengineering.smartpos.inventory.api.service.EntryService;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import com.smartitengineering.smartpos.inventory.api.service.StoreService;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import com.smartitengineering.smartpos.inventory.impl.service.EntryServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.ProductServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.StoreServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.SupplierServiceImpl;
import com.smartitengineering.smartpos.inventory.impl.service.UomServiceImpl;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class APIModule extends AbstractModule {

  private final Properties properties;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  public APIModule(Properties properties) {
    if (properties != null) {
      this.properties = properties;
    }
    else {
      this.properties = new Properties();
    }
  }

  @Override
  protected void configure() {
    bind(StoreService.class).annotatedWith(Names.named("apiStoreService")).to(StoreServiceImpl.class);
    bind(ProductService.class).annotatedWith(Names.named("apiProductService")).to(ProductServiceImpl.class);
    bind(UomService.class).annotatedWith(Names.named("apiUomService")).to(UomServiceImpl.class);
    bind(SupplierService.class).annotatedWith(Names.named("apiSupplierService")).to(SupplierServiceImpl.class);
    bind(EntryService.class).annotatedWith(Names.named("apiEntryService")).to(EntryServiceImpl.class);
  }
}
