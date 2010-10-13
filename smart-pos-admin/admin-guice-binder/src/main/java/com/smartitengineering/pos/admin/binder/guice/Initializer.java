/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.admin.binder.guice;

import com.smartitengineering.util.bean.guice.GuiceUtil;

/**
 *
 * @author russel
 */
public class Initializer {

  private Initializer(){
  }

  public static void init(){
    GuiceUtil.getInstance("com/smartitengineering/pos/binder/guice/api-modules.properties").register();
  }

}
