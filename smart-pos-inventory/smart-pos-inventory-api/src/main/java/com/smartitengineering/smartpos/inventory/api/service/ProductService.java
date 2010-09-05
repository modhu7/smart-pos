/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.Product;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface ProductService {

  public void save(Product product);

  public void update(Product product);

  public void delete(Product product);

  public Collection<Product> getAllProducts();

  public Collection<Product> getProducts(String productCodeLike, String productCode, boolean isSmallerThan, int count);

  public Collection<Product> getByOrganization(String organizationUniqueShortName, String productCode, boolean isSmallerThan, int count);

  public Product getByProductCodeAndOrganization(String organizationUniqueShortName, String productCode);

  public Product getByCode(String productCode);

}
