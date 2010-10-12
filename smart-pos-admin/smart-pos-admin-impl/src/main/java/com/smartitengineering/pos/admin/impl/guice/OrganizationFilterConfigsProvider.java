/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.impl.guice;

import com.google.inject.Provider;
import com.smartitengineering.dao.impl.hbase.spi.FilterConfigs;
import com.smartitengineering.dao.impl.hbase.spi.impl.JsonConfigLoader;
import com.smartitengineering.smartpos.admin.api.Organization;
import java.io.IOException;

/**
 *
 * @author modhu7
 */
public class OrganizationFilterConfigsProvider implements Provider<FilterConfigs<Organization>>{

  @Override
  public FilterConfigs<Organization> get() {
    try {
      return JsonConfigLoader.parseJsonAsFilterConfigMap(getClass().getClassLoader().
          getResourceAsStream("com/smartitengineering/pos/admin/impl/organization/UomFilterConfigs.json"));
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
