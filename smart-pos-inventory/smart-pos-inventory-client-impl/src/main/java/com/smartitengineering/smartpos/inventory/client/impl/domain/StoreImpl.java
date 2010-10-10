/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.domain;

import com.smartitengineering.smartpos.inventory.client.api.domain.Store;
import com.smartitengineering.user.client.api.Address;
import com.smartitengineering.user.client.api.Organization;

/**
 *
 * @author saumitra
 */
public class StoreImpl implements Store{

  private String name;
  private String code;
  private Integer orgid;
  private String[] products;
  private Organization org;
  private Address address;


  @Override
  public String getName() {
    return name;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOrg(Organization org) {
    this.org = org;
  }

  public void setOrgid(Integer orgid) {
    this.orgid = orgid;
  }

  public void setProducts(String[] products) {
    this.products = products;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public Organization getOrganization() {
    return org;
  }

  @Override
  public Address getAddress() {
    return address;
  }

  @Override
  public Integer getOrganizationID() {
    return orgid;
  }

  @Override
  public String[] getProductIds() {
    return products;
  }

}
