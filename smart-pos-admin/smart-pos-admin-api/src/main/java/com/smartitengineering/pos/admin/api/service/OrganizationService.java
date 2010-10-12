/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.admin.api.service;

import com.smartitengineering.smartpos.admin.api.Organization;
import java.util.Collection;

/**
 *
 * @author modhu7
 */
public interface OrganizationService {

  public void save(Organization organization);

  public void update(Organization organization);

  public void delete(Organization organization);

  public Collection<Organization> getAll();
}
