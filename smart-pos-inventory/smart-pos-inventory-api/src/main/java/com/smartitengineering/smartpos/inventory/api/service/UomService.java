/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface UomService {

  PersistantUnitOfMeasurement getById(UomId uomId);

  public PersistantUnitOfMeasurement getByUomId(UomId uomId);

  public void save(PersistantUnitOfMeasurement uom);

  public void update(PersistantUnitOfMeasurement uom);

  public void delete(PersistantUnitOfMeasurement uom);

  public Collection<PersistantUnitOfMeasurement> getAllUoms();

  // non paginated version
  public Collection<PersistantUnitOfMeasurement> getByOrganization(String organizatinUniqueShortName);

  // paginated version
  public Collection<PersistantUnitOfMeasurement> getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count);

  // non paginated version
  public PersistantUnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName);

  // paginated version
  public PersistantUnitOfMeasurement getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name, boolean isSmallerThan, int count);
}
