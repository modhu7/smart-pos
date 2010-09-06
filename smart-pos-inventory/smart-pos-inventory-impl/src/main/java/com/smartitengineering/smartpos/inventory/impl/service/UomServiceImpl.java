/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.admin.api.Organization;
import com.smartitengineering.smartpos.inventory.api.UOM;
import com.smartitengineering.smartpos.inventory.api.service.UomService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
  public Collection<UOM> getByOrganization(String organizatinUniqueShortName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<UOM> getByOrganization(String organizatinUniqueShortName, String name, boolean isSmallerThan,
                                           int count) {
    //throw new UnsupportedOperationException("Not supported yet.");
    List<UOM> uomList = new ArrayList<UOM>();
    UOM uom1 = new UOM();
    uom1.setName("UOM 1");
    
    uomList.add(uom1);

    UOM uom2 = new UOM();
    uom2.setName("UOM 2");
    
    uomList.add(uom2);

    Collection<UOM> uoms = uomList;
    return uoms;
  }

  @Override
  public UOM getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public UOM getByOrganizationAndUOM(String organizatinUniqueShortName, String uomName, String name,
                                     boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
