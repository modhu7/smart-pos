/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import java.util.Collection;

/**
 *
 * @author russel
 */
public class ProductServiceImpl implements ProductService{

  @Override
  public void save(Product product) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(Product product) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Product product) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Product> getAllProducts() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Product> getProducts(String productCodeLike, String productCode, boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Collection<Product> getByOrganization(String organizationUniqueShortName, String productCode,
                                               boolean isSmallerThan, int count) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Product getByProductCodeAndOrganization(String organizationUniqueShortName, String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Product getByCode(String productCode) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
