/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.resource.EntriesResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.ProductsResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.RootResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoreResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.StoresResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.atom.AtomClientUtil;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.smartitengineering.util.rest.client.jersey.cache.CacheableClientConfigProps;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.atom.abdera.impl.provider.entity.FeedProvider;
import java.net.URI;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
public class RootResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements RootResource{

  public static Logger logger = LoggerFactory.getLogger(RootResourceImpl.class);

  public static final String REL_UOMS = "uoms";
  public static final String REL_STORES = "stores";
  private Link UOMS_LINK;
  
  public static RootResource getRoot(URI uri) {
    try {
      RootResource resource = new RootResourceImpl(uri);
      return resource;
    }
    catch (RuntimeException ex) {
      logger.error(ex.getMessage(), ex);
      throw ex;
    }
  }    

  private RootResourceImpl(URI uri) throws IllegalArgumentException, UniformInterfaceException{
    super(null, uri, false, null);
    
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
    System.out.println(get().getLink(REL_UOMS).getHref().toString());
    return new UomsResourceImpl(this, AtomClientUtil.convertFromAtomLinkToResourceLink(get().getLink(REL_UOMS)));
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
  public StoresResource getStoresResource() {
    return new StoresResourceImpl(this, AtomClientUtil.convertFromAtomLinkToResourceLink(get().getLink(REL_STORES)));
  }



  @Override
  public ResourceLink getLoginLink() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
