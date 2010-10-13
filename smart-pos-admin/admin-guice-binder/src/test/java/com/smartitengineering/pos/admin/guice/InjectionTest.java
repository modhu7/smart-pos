/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.admin.guice;


import com.smartitengineering.pos.admin.api.factory.Services;
import com.smartitengineering.pos.admin.binder.guice.Initializer;


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
    assertNotNull(Services.getInstance().getOrganizationService());
  }
}
