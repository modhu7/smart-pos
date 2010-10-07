/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Product;

/**
 *
 * @author saumitra
 */
public interface ProductsResource {

  public ProductResource getOrganizationProductResource();

  public ProductResource create(Product product);

}
