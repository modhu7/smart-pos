/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

import java.util.List;

/**
 *
 * @author saumitra
 */
public interface OrganizationStoreResource{

 public OrganizationProductsResource getOrganizationProductsResource();

 public void update();

 public Product getProduct();

 public Store getStore();

}
