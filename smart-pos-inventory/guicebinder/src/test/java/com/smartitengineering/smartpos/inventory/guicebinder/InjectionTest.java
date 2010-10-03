/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.guicebinder;

import com.smartitengineering.smartpos.inventory.resource.Services;
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
    //assertNotNull(Services.getInstance().getEntryService());
    //assertNotNull(Services.getInstance().getProductService());
//    assertNotNull(Services.getInstance().getStoreService());
//    assertNotNull(Services.getInstance().getSupplierService());
    assertNotNull(Services.getInstance().getUomService());    
  }
}
