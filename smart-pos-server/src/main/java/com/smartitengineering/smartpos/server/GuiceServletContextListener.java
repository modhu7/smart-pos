/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.server;

import com.smartitengineering.smartpos.inventory.guicebinder.Initializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author russel
 */
public class GuiceServletContextListener implements ServletContextListener{

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    Initializer.init();
    ServletContext servletContext = sce.getServletContext();
    servletContext.setAttribute(Initializer.class.getName(), sce);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();
    servletContext.removeAttribute(Initializer.class.getName());
  }

}
