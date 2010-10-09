/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.guicebinder;

import com.smartitengineering.smartpos.inventory.api.factory.Services;
import junit.framework.TestCase;

/**
 *
 * @author russel
 */
public class InjectionTest extends TestCase{

  @Override
  protected void setUp()throws Exception{
    Initializer.init();
  }

  public void testApi(){
    assertNotNull(Services.getInstance().getEntryService());
    assertNotNull(Services.getInstance().getProductService());
    assertNotNull(Services.getInstance().getStoreService());
    assertNotNull(Services.getInstance().getSupplierService());
    assertNotNull(Services.getInstance().getUomService());
    //com.smartitengineering.util.bean.guice.GoogleGuiceBeanFactory;


//    assertNotNull(Domains.getInstance().getUomId());
//    assertNotNull(Domains.getInstance().getEntryId());
//    assertNotNull(Domains.getInstance().getStoreId());
//    assertNotNull(Domains.getInstance().getProductId());
//    assertNotNull(Domains.getInstance().getSupplierId());

    //BeanFactoryRegistrar
  }
}
