/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.admin.impl.guice;

import com.google.inject.Provider;
import com.smartitengineering.dao.impl.hbase.spi.impl.JsonConfigLoader;
import com.smartitengineering.dao.impl.hbase.spi.impl.SchemaInfoProviderBaseConfig;
import com.smartitengineering.smartpos.admin.api.Organization;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author modhu7
 */
public class OrganizationSchemaBaseConfigProvider implements Provider<SchemaInfoProviderBaseConfig<Organization>>{

  @Override
  public SchemaInfoProviderBaseConfig<Organization> get() {
    try {
      final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(
          "com/smartitengineering/pos/admin/impl/organization/OrganizationSchemaBaseConfig.json");
      return JsonConfigLoader.parseJsonAsBaseConfig(resourceAsStream);
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
