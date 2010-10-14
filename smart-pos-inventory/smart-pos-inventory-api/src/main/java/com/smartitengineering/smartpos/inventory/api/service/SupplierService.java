/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.PersistantSupplier;
import com.smartitengineering.smartpos.inventory.api.Supplier;
import com.smartitengineering.smartpos.inventory.api.domainid.SupplierId;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface SupplierService {

  public void save(PersistantSupplier supplier);

  public void update(PersistantSupplier supplier);

  public void delete(PersistantSupplier supplier);

  public void getAllUoms();

  // non paginated version
  public Collection<PersistantSupplier> getByOrganization(String organizatinUniqueShortName);

  // paginated version
  public PersistantSupplier getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan,
                                              int count);

  // non paginated version
  public PersistantSupplier getByOrganizationAndSname(String organizatinUniqueShortName, String supplierName);

  public PersistantSupplier getById(SupplierId supplierId);
}
