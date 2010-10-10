/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

//import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
//import com.smartitengineering.smartpos.inventory.client.api.resource.UomResource;
//import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
//import com.smartitengineering.smartpos.inventory.client.impl.domain.UnitOfMeasurementImpl;
//import com.smartitengineering.util.rest.client.ApplicationWideClientFactoryImpl;
//import java.io.File;
//import junit.framework.TestCase;
//import org.eclipse.jetty.server.Handler;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.webapp.WebAppContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author russel
// */
//public class UomResourceTest extends TestCase{
//
//  private static Server jettyServer;
//  private static final int PORT = 10090;
//  private RootResource rootResource;
//
//  @Override
//  public void setUp() throws Exception{
//    System.out.println("::: Starting server :::");
//    jettyServer = new Server(PORT);
//    final String webapp = "./target/smartpos/";
//    if (!new File(webapp).exists()) {
//      throw new IllegalStateException("WebApp file/dir does not exist!");
//    }
//    Handler webAppHandler = new WebAppContext(webapp, "/");
//    jettyServer.setHandler(webAppHandler);
//    jettyServer.start();
//
//    // set up client
//    ClassPathXmlApplicationContext classPathXmlApplicationContext =
//                                   new ClassPathXmlApplicationContext("config-context.xml");
//    System.setProperty(ApplicationWideClientFactoryImpl.TRACE, Boolean.TRUE.toString());
//  }
//
//  public void testCreate(){
//
//    rootResource = RootResourceImpl.getInstance();
//    assertNotNull(rootResource);
//
//    UomsResource uomsResource = rootResource.getOrganizationUomsResource();
//    assertNotNull(uomsResource);
//
//    UnitOfMeasurementImpl uom = new UnitOfMeasurementImpl();
//    uom.setId("KG");
//    uom.setName("Kilogram");
//    uom.setSymbol("Kg");
//    uom.setUomSystem("SI");
//    uom.setUomType("Weight");
//
//    UomResource uomResource = uomsResource.create(uom);
//
//    assertNotNull(uomResource);
//  }
//
//  @Override
//  public void tearDown() throws Exception{
//    jettyServer.stop();
//  }
//
//}
