/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.guice;

import com.google.inject.Provider;
import com.smartitengineering.dao.impl.hbase.spi.impl.JsonConfigLoader;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderBaseConfig;
import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author russel
 */
public class SupplierSchemaBaseConfigProvider implements Provider<SchemaInfoProviderBaseConfig<PersistantSupplier>>{

  @Override
  public SchemaInfoProviderBaseConfig<PersistantSupplier> get() {
    try {
      final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(
          "com/smartitengineering/pos/impl/supplier/SupplierSchemaBaseConfig.json");
      return JsonConfigLoader.parseJsonAsBaseConfig(resourceAsStream);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
