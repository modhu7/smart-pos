/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;


import com.smartitengineering.user.client.api.OrganizationResource;
import com.smartitengineering.smartpos.inventory.client.api.domain.Store;

/**
 *
 * @author saumitra
 */
public interface StoreResource{

 public ProductsResource getOrganizationProductsResource();

 public void update();

 public ProductsResource getProductResources();

 public Store getStore();

 public OrganizationResource getOrganizationResource();

}
