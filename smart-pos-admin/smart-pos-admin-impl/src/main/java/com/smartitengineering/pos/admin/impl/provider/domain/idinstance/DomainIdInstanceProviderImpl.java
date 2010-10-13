/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.admin.impl.provider.domain.idinstance;

import com.smartitengineering.dao.impl.hbase.spi.DomainIdInstanceProvider;
import com.smartitengineering.pos.admin.impl.domain.id.OrganizationIdImpl;

/**
 *
 * @author russel
 */
public class DomainIdInstanceProviderImpl implements DomainIdInstanceProvider{

  @Override
  public <IdType> IdType getInstance(Class<? extends IdType> clazz) {
    Object object;
    object = new OrganizationIdImpl();
    return (IdType)object;
  }

}
