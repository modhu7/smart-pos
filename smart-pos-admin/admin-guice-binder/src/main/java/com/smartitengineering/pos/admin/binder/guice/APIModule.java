/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.binder.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.smartitengineering.pos.admin.api.service.OrganizationService;
import com.smartitengineering.pos.admin.impl.service.OrganizationServiceImpl;
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
    bind(OrganizationService.class).annotatedWith(Names.named("apiStoreService")).to(OrganizationServiceImpl.class);
  }
}
