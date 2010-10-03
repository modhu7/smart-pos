/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.domainidinstanceprovider;

import com.smartitengineering.dao.impl.hbase.spi.DomainIdInstanceProvider;
import com.smartitengineering.smartpos.inventory.impl.domainid.UomIdImpl;

/**
 *
 * @author russel
 */
public class DomainIdInstanceProviderImpl implements DomainIdInstanceProvider{

  @Override
  public <IdType> IdType getInstance(Class<? extends IdType> clazz) {
    Object object;
    object = new UomIdImpl();
    return (IdType)object;
  }

}
