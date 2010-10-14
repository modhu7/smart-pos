/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.guice;

import com.google.inject.Provider;
import com.smartitengineering.dao.impl.hbase.spi.FilterConfigs;
import com.smartitengineering.dao.impl.hbase.spi.impl.JsonConfigLoader;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import java.io.IOException;

/**
 *
 * @author russel
 */
public class SupplierFilterConfigsProvider implements Provider<FilterConfigs<PersistantSupplier>>{

  @Override
  public FilterConfigs<PersistantSupplier> get() {
    try {
      return JsonConfigLoader.parseJsonAsFilterConfigMap(getClass().getClassLoader().
          getResourceAsStream("com/smartitengineering/pos/impl/store/StoreFilterConfigs.json"));
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
