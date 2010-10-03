/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;

/**
 *
 * @author russel
 */
public interface SupplierService {

  public void save(Supplier supplier);

  public void update(Supplier supplier);

  public void delete(Supplier supplier);

  public void getAllUoms();

  // non paginated version
  public Supplier getByOrganization(String organizatinUniqueShortName);

  // paginated version
  public Supplier getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count);

  // non paginated version
  public Supplier getByOrganizationAndSname(String organizatinUniqueShortName, String supplierName);

  public Supplier getById(SupplierId supplierId);
  

}
