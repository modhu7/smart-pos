/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.guice;

import com.google.inject.Provider;
import com.smartitengineering.dao.impl.hbase.spi.impl.JsonConfigLoader;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderBaseConfig;
import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author russel
 */
public class ProductSchemaBaseConfigProvider implements Provider<SchemaInfoProviderBaseConfig<PersistantProduct>>{

  @Override
  public SchemaInfoProviderBaseConfig<PersistantProduct> get() {
    try {
      final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(
          "com/smartitengineering/pos/impl/product/ProductSchemaBaseConfig.json");
      return JsonConfigLoader.parseJsonAsBaseConfig(resourceAsStream);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
