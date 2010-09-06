/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.smartpos.inventory.api.UOM;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface UomService {

  public void save(UOM uom);

  public void update(UOM uom);

  public void delete(UOM uom);

  public void getAllUoms();

  // non paginated version
  public Collection<UOM> getByOrganization(String organizatinUniqueShortName);

  // paginated version
  public Collection<UOM> getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan, int count);

  // non paginated version
  public UOM getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName);

  // paginated version
  public UOM getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name, boolean isSmallerThan, int count);
}