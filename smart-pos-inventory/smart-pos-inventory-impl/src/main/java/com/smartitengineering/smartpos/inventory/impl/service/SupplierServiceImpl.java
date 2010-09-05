/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.smartpos.inventory.api.service.SupplierService;

/**
 *
 * @author russel
 */
public class SupplierServiceImpl implements SupplierService{

  @Override
  public void save(Supplier supplier) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(Supplier supplier) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Supplier supplier) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void getAllUoms() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Supplier getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Supplier getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Supplier getByOrganizationAndSname(String organizatinUniqueShortName, String supplierName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
