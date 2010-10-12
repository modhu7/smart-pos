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

  private String relativeUri;

  public String getRelativeUri() {
    return relativeUri;
  }

  public void setRelativeUri(String relativeUri) {
    this.relativeUri = relativeUri;
  }

  @Override
  public boolean isValid() {
    if (StringUtils.isNotBlank(relativeUri)) {
      return true;
    }
    else {
      return false;
    }
  }
}
