/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl;

import com.smartitengineering.smartpos.inventory.client.api.Product;
import com.smartitengineering.smartpos.inventory.client.api.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.api.Store;
import com.smartitengineering.smartpos.inventory.client.api.StoreResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.sun.jersey.api.client.config.ClientConfig;
import org.apache.abdera.model.Feed;

/**
 *
 * @author saumitra
 */
public class StoreResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements StoreResource{

  public StoreResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
  }

  @Override
  protected void processClientConfig(ClientConfig cc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink rl) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public ProductsResource getOrganizationProductsResource() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Product getProduct() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Store getStore() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
