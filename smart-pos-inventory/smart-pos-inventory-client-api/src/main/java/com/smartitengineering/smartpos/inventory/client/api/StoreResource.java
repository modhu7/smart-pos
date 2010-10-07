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
public interface StoreResource{

 public ProductsResource getOrganizationProductsResource();

 public void update();

 public Product getProduct();

 public Store getStore();

}
