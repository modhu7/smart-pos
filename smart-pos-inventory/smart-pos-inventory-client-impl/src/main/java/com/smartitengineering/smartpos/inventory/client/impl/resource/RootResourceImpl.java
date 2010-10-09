/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.resource.EntriesResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoreResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.smartitengineering.util.rest.client.jersey.cache.CacheableClientConfigProps;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.atom.abdera.impl.provider.entity.FeedProvider;
import org.apache.abdera.model.Feed;

/**
 *
 * @author russel
 */
public class RootResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements RootResource{

  public static final String REL_UOMS = "uoms";
  
  public static RootResource getInstance() {
    return new RootResourceImpl();
  }

  public RootResourceImpl(){
    super(null, BASE_URI);    
  }

  @Override
  protected void processClientConfig(ClientConfig clientConfig) {
//    clientConfig.getProperties().put(CacheableClientConfigProps.USERNAME, LoginCenter.getUsername());
//    clientConfig.getProperties().put(CacheableClientConfigProps.PASSWORD, LoginCenter.getPassword());
    clientConfig.getClasses().add(JacksonJsonProvider.class);
    clientConfig.getClasses().add(FeedProvider.class);
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink link) {
    return null;
  }

  @Override
  public UomsResource getOrganizationUomsResource() {
    return new UomsResourceImpl(this, getRelatedResourceUris().getFirst(REL_UOMS));
  }

  @Override
  public EntriesResource getEntriesResource() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public ProductsResource getOrganizationProductsResource() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public StoreResource getOrganizationStoreResource() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public ResourceLink getLoginLink() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
