/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

/**
 *
 * @author saumitra
 */
public interface ProductsResource {

  public ProductResource getOrganizationProductResource();

  public ProductResource create(Product product);

}
