/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Supplier;
import com.smartitengineering.smartpos.inventory.client.api.resource.SupplierResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.SuppliersResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.client.ClientUtil;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

/**
 *
 * @author russel
 */
public class SuppliersResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements SuppliersResource{

  private static final String REL_SUPPLIER = "supplier";
  private static final String REL_ALT = "alternate";

  public SuppliersResourceImpl(Resource referrer, ResourceLink pageLink){
    super(referrer, pageLink);
  }

  @Override
  protected void processClientConfig(ClientConfig clientConfig) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink link) {
    return new SuppliersResourceImpl(this, link);
  }

  @Override
  public List<SupplierResource> getSupplierResources() {
    List<SupplierResource> supplierResources = new ArrayList<SupplierResource>();

    for(Entry entry: getLastReadStateOfEntity().getEntries()){
      supplierResources.add(new SupplierResourceImpl(this, null));
    }
    return supplierResources;
  }

  @Override
  public SupplierResource create(Supplier supplier) {
    ClientResponse response = post (MediaType.APPLICATION_JSON, supplier, ClientResponse.Status.CREATED);
    final ResourceLink supplierLink = ClientUtil.createResourceLink(REL_SUPPLIER,response.getLocation(),MediaType.APPLICATION_ATOM_XML);
    return new SupplierResourceImpl(this,supplierLink);
  }

}
