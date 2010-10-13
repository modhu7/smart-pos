/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.guice;

import com.google.inject.Provider;
import com.smartitengineering.dao.impl.hbase.spi.FilterConfigs;
import com.smartitengineering.dao.impl.hbase.spi.impl.JsonConfigLoader;
import com.smartitengineering.smartpos.inventory.api.PersistantStore;
import java.io.IOException;

/**
 *
 * @author russel
 */
public class StoreFilterConfigsProvider implements Provider<FilterConfigs<PersistantStore>>{

  @Override
  public FilterConfigs<PersistantStore> get() {
    try {
      return JsonConfigLoader.parseJsonAsFilterConfigMap(getClass().getClassLoader().
          getResourceAsStream("com/smartitengineering/pos/impl/store/StoreFilterConfigs.json"));
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
