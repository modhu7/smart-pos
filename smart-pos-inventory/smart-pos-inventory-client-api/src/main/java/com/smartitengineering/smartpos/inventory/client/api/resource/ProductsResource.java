/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Product;
import java.util.List;

/**
 *
 * @author saumitra
 */
public interface ProductsResource {

  public List<ProductResource> getOrganizationProductResource();

  public ProductResource create(Product product);

}
