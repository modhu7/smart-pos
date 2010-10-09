package com.smartitengineering.smartpos.inventory.client.impl;

import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.smartpos.inventory.client.impl.resource.RootResourceImpl;
import com.smartitengineering.smartpos.inventory.guicebinder.Initializer;
import com.smartitengineering.util.bean.guice.GuiceUtil;
import com.smartitengineering.util.rest.client.ApplicationWideClientFactoryImpl;
import java.net.URI;
import java.util.Properties;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    try {
      
//      System.setProperty(ApplicationWideClientFactoryImpl.TRACE, Boolean.TRUE.toString());
//      Properties properties = new Properties();
//      properties.setProperty(GuiceUtil.CONTEXT_NAME_PROP,
//                             "com.smartitengineering.dao.impl.hbase,com.smartitengineering.user.client");
//      properties.setProperty(GuiceUtil.IGNORE_MISSING_DEP_PROP, Boolean.TRUE.toString());
//      //properties.setProperty(GuiceUtil.MODULES_LIST_PROP, ConfigurationModule.class.getName());
//      GuiceUtil.getInstance(properties).register();
//      Initializer.init();


      URI uri = new URI("http://localhost:10090/orgs/sn/SITEL/dashboard");
      RootResource rootResource = RootResourceImpl.getRoot(uri);
      UomsResource uomsResource = rootResource.getOrganizationUomsResource();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
