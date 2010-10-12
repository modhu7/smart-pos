/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.impl.service;

import com.google.inject.Inject;
import com.smartitengineering.dao.common.CommonReadDao;
import com.smartitengineering.dao.common.CommonWriteDao;
import com.smartitengineering.pos.admin.api.domain.id.OrganizationId;
import com.smartitengineering.pos.admin.api.service.OrganizationService;
import com.smartitengineering.smartpos.admin.api.Organization;

/**
 *
 * @author modhu7
 */
public abstract class AbstractOrganizationService implements OrganizationService {

  @Inject
  protected CommonReadDao<Organization, OrganizationId> commonReadDao;
  @Inject
  protected CommonWriteDao<Organization> commonWriteDao;


  public Organization getById(OrganizationId id) {
    if (id == null) {
      return null;
    }
    return commonReadDao.getById(id);
  }
}
