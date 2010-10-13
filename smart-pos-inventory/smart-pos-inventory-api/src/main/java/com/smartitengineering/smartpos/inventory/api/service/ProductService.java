/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.api.service;

import com.smartitengineering.smartpos.inventory.api.PersistantProduct;
import java.util.Collection;

/**
 *
 * @author russel
 */
public interface ProductService {

  public void save(PersistantProduct product);

  public void update(PersistantProduct product);

  public void delete(PersistantProduct product);

  public Collection<PersistantProduct> getAllProducts();

  public Collection<PersistantProduct> getProducts(String productCodeLike, String productCode, boolean isSmallerThan, int count);

  public Collection<PersistantProduct> getByOrganization(String organizationUniqueShortName, String productCode, boolean isSmallerThan, int count);

  public PersistantProduct getByProductCodeAndOrganization(String organizationUniqueShortName, String productCode);

  public PersistantProduct getByCode(String productCode);

}
