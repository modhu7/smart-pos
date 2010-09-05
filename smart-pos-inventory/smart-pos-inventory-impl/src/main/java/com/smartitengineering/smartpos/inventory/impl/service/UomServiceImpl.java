/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.smartpos.inventory.api.UOM;
import com.smartitengineering.smartpos.inventory.api.service.UomService;

/**
 *
 * @author russel
 */
public class UomServiceImpl implements UomService{

  @Override
  public void save(UOM uom) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(UOM uom) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(UOM uom) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void getAllUoms() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Organization getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Organization getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Organization getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Organization getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name,
                                              boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
