/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.admin.api.factory;

import com.smartitengineering.pos.admin.api.service.OrganizationService;
import com.smartitengineering.util.bean.BeanFactoryRegistrar;
import com.smartitengineering.util.bean.annotations.Aggregator;
import com.smartitengineering.util.bean.annotations.InjectableField;

/**
 *
 * @author modhu7
 */
@Aggregator(contextName = Services.CONTEXT_NAME)
public class Services {

  public static final String CONTEXT_NAME = "com.smartitengineering.pos.admin.api.service";

  private Services() {
  }

  @InjectableField
  private OrganizationService organizationService;

  public OrganizationService getOrganizationService() {
    return organizationService;
  }

  public void setOrganizationService(OrganizationService organizationService) {
    this.organizationService = organizationService;
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
