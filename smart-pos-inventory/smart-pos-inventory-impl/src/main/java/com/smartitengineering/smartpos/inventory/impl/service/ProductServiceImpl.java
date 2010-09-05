/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.impl.service;

import com.smartitengineering.smartpos.inventory.api.Product;
import com.smartitengineering.smartpos.inventory.api.service.ProductService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    List<Product> productList = new ArrayList<Product>();
    Product product1 = new Product();
    product1.setName("Product 1");
    product1.setProductCode("P1");
    productList.add(product1);

    Product product2 = new Product();
    product2.setName("Product 2");
    product2.setProductCode("P2");
    productList.add(product2);

    Collection<Product> products = productList;
    return products;
    //throw new UnsupportedOperationException("Not supported yet.");
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
