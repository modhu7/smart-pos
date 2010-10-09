/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;


import com.smartitengineering.user.client.api.OrganizationResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.api.domain.Store;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoreResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import javax.ws.rs.core.MediaType;
import org.apache.abdera.model.Feed;

/**
 *
 * @author saumitra
 */
public class StoreResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements StoreResource{

  public static final String REL_STORES = "stores";

  public StoreResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
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
    put(MediaType.APPLICATION_JSON,getStore(),ClientResponse.Status.OK,ClientResponse.Status.SEE_OTHER,ClientResponse.Status.FOUND);
  }
  
  @Override
  public Store getStore() {
    return getStore(false);
  }
  protected Store getStore(boolean reload){
    Resource<Store> store = super.<Store>getNestedResource(REL_STORES);
    if(reload){
      return store.get();
    }
    else
    {
      return store.getLastReadStateOfEntity();
    }
  }

  @Override
  public ProductsResource getProductResources() {
    return new ProductsResourceImpl(this,getRelatedResourceUris().getFirst(REL_STORES));
  }

  @Override
  public OrganizationResource getOrganizationResource() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
