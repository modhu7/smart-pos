/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface UomService {

  UnitOfMeasurement getById(UomId uomId);

  public UnitOfMeasurement getByUomId(UomId uomId);

  public void save(UnitOfMeasurement uom);

  public void update(UnitOfMeasurement uom);

  public void delete(UnitOfMeasurement uom);

  public Collection<UnitOfMeasurement> getAllUoms();

  // non paginated version
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName);

  // paginated version
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count);

  // non paginated version
  public UnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName);

  // paginated version
  public UnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name, boolean isSmallerThan, int count);
}
