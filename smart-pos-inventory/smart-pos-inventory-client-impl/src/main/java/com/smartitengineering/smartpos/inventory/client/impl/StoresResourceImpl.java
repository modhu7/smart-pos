/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl;


import com.smartitengineering.smartpos.inventory.client.api.Store;
import com.smartitengineering.smartpos.inventory.client.api.StoreResource;
import com.smartitengineering.smartpos.inventory.client.api.StoresResource;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.atom.AtomClientUtil;
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
 * @author saumitra
 */
public class StoresResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements StoresResource{

  public StoresResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
  }

  private static final String REL_ORG = "store";
  private static final String REL_ALT = "alternate";



  @Override
  protected void processClientConfig(ClientConfig cc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink rl) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<StoreResource> getOrganizationStoreResources() {
    List<StoreResource> StoreResource= new ArrayList<StoreResource>();
    for (Entry entry : getLastReadStateOfEntity().getEntries()){
      StoreResource.add(new StoreResourceImpl(this, AtomClientUtil.convertFromAtomLinkToResourceLink(entry.getLink(REL_ALT))));

    }
    return StoreResource;
  }

  @Override
  public StoreResource create(Store store) {
    
    ClientResponse response = post (MediaType.APPLICATION_JSON, store, ClientResponse.Status.CREATED);
    final ResourceLink orgLink = ClientUtil.createResourceLink(REL_ORG,response.getLocation(),MediaType.APPLICATION_ATOM_XML);
    return new StoreResourceImpl(this,orgLink);
  }

  @Override
  public StoreResource getOrganizationStoreResource() {
    throw new UnsupportedOperationException("Not supported yet.");
  }



}
