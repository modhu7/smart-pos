/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Supplier;
import com.smartitengineering.smartpos.inventory.client.api.resource.SupplierResource;
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
 * @author russel
 */
public class SupplierResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements SupplierResource{

  public static final String REL_SUPPLIER = "supplier";
  public SupplierResourceImpl(Resource referrer, ResourceLink pageLink){
    
    super(referrer, pageLink);
    final ResourceLink altLink = getRelatedResourceUris().getFirst(Link.REL_ALTERNATE);
    addNestedResource(REL_SUPPLIER, new SimpleResourceImpl<com.smartitengineering.smartpos.inventory.client.impl.domain.SupplierImpl>(
        this, altLink.getUri(), altLink.getMimeType(),
        com.smartitengineering.smartpos.inventory.client.impl.domain.SupplierImpl.class,
        null, false, null, null));
  }

  @Override
  protected void processClientConfig(ClientConfig clientConfig) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink link) {
    return null;
  }

  @Override
  public void update() {
    put(MediaType.APPLICATION_JSON,getSupplier(),ClientResponse.Status.OK,ClientResponse.Status.SEE_OTHER,ClientResponse.Status.FOUND);
  }

  @Override
  public void delete() {
    delete(ClientResponse.Status.OK);
  }

  @Override
  public Supplier getSupplier() {
    return getSupplier(false);
  }

  protected Supplier getSupplier(boolean reload){
    Resource<Supplier> supplier = super.<Supplier>getNestedResource(REL_SUPPLIER);
    if(reload){
      return supplier.get();
    }
    else
    {
      return supplier.getLastReadStateOfEntity();
    }
  }

}
