/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.admin.api;

import com.smartitengineering.domain.AbstractGenericPersistentDTO;
import com.smartitengineering.pos.admin.api.domain.id.OrganizationId;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author russel
 */
public class Organization extends AbstractGenericPersistentDTO<Organization, OrganizationId, Long> {

  private String shortName;

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  @Override
  public boolean isValid() {
    if (StringUtils.isNotBlank(shortName)) {
      return true;
    }
    else {
      return false;
    }
  }
}
