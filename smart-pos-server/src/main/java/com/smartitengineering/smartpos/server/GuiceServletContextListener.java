/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.server;

import com.smartitengineering.dao.hbase.ddl.HBaseTableGenerator;
import com.smartitengineering.dao.hbase.ddl.config.json.ConfigurationJsonParser;
import com.smartitengineering.smartpos.inventory.guicebinder.Initializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import com.smartitengineering.dao.impl.hbase.HBaseConfigurationFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class GuiceServletContextListener implements ServletContextListener {

  private static Logger logger = LoggerFactory.getLogger(GuiceServletContextListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    Initializer.init();
    Configuration config = HBaseConfigurationFactory.getConfigurationInstance();
    if (config == null) {
      config = HBaseConfigurationFactory.initConfiguration();
    }

    try {
      new HBaseTableGenerator(ConfigurationJsonParser.getConfigurations(getClass().getClassLoader().
          getResourceAsStream("com/smartitengineering/pos/inventory/impl/schema.json")), config, false).generateTables();
    }
    catch (MasterNotRunningException ex) {
      logger.error("Master could not be found!", ex);
    }
    catch (Exception ex) {
      logger.error("Could not create table!", ex);
    }
    ServletContext servletContext = sce.getServletContext();
    servletContext.setAttribute(Initializer.class.getName(), sce);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();
    servletContext.removeAttribute(Initializer.class.getName());
  }
}
