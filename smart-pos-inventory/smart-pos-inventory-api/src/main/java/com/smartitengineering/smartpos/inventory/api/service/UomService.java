/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface UomService {

  public UnitOfMeasurement getById(String uomId);

  public void save(UnitOfMeasurement uom);

  public void update(UnitOfMeasurement uom);

  public void delete(UnitOfMeasurement uom);

  public void getAllUoms();

  // non paginated version
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName);

  // paginated version
  public Collection<UnitOfMeasurement> getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count);

  // non paginated version
  public UnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName);

  // paginated version
  public UnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name, boolean isSmallerThan, int count);
}
