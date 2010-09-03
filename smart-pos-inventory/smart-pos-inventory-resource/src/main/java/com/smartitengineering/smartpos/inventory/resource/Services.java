/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.service.StoreService;
import com.smartitengineering.smartpos.inventory.impl.service.StoreServiceImpl;
import com.smartitengineering.util.bean.BeanFactoryRegistrar;
import com.smartitengineering.util.bean.annotations.Aggregator;

/**
 *
 * @author russel
 */
@Aggregator(contextName = "com.smartitengineering.smartpos.inventory.api.service")
public class Services {

  private StoreService storeService;
  

  private Services(){    
  }

  public StoreService getStoreService() {
    //return storeService;
    return new StoreServiceImpl();
  }

  public void setStoreService(StoreService storeService) {
    this.storeService = storeService;
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
