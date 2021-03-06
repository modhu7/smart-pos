/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Product;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.smartitengineering.util.rest.client.SimpleResourceImpl;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import javax.ws.rs.core.MediaType;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

/**
 *
 * @author saumitra
 */
public class ProductResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements ProductResource{

  private static final String REL_PRODUCT = "product";

  public ProductResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
    final ResourceLink altLink = getRelatedResourceUris().getFirst(Link.REL_ALTERNATE);
    addNestedResource(REL_PRODUCT, new SimpleResourceImpl<com.smartitengineering.smartpos.inventory.client.impl.domain.ProductImpl>(
        this, altLink.getUri(), altLink.getMimeType(),
        com.smartitengineering.smartpos.inventory.client.impl.domain.ProductImpl.class,
        null, false, null, null));
  }

  @Override
  protected void processClientConfig(ClientConfig cc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink rl) {
    return null;
  }

  @Override
  public void update() {
    put(MediaType.APPLICATION_JSON,getProduct(),ClientResponse.Status.OK,ClientResponse.Status.SEE_OTHER,ClientResponse.Status.FOUND);
  }

  @Override
  public Product getProduct() {
    return getProduct(false);
  }
  protected Product getProduct(boolean reload){
    Resource<Product> product = super.<Product>getNestedResource(REL_PRODUCT);
    if(reload){
      return product.get();
    }
    else{
      return product.getLastReadStateOfEntity();
    }
  }

  @Override
  public void delete() {
    delete(ClientResponse.Status.OK);
  }

}
