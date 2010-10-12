/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.impl.service;

import com.smartitengineering.smartpos.admin.api.Organization;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author modhu7
 */
public class OrganizationServiceImpl extends AbstractOrganizationService {

  protected final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

  @Override
  public void save(Organization organization) {
    try {
      commonWriteDao.save(organization);
    }
    catch (Exception ex) {
      logger.error(ex.getMessage());
      ex.printStackTrace();
    }
  }

  @Override
  public void update(Organization organization) {
    commonWriteDao.update(organization);
  }

  @Override
  public void delete(Organization organization) {
    commonWriteDao.delete(organization);
  }

  @Override
  public Collection<Organization> getAll() {
    final List<Organization> list = commonReadDao.getList();
    if(list == null  || list.isEmpty())
      return Collections.emptyList();
    return list;
  }
}
