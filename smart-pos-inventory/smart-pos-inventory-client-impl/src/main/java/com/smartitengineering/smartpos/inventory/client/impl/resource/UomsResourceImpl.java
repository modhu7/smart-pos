/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.resource.UomResource;
import com.smartitengineering.smartpos.inventory.client.api.resource.UomsResource;
import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.atom.AtomClientUtil;
import com.smartitengineering.util.rest.client.ClientFactory;
import com.smartitengineering.util.rest.client.ClientUtil;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.config.ClientConfig;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.abdera.model.Feed;
import javax.ws.rs.core.MediaType;
import org.apache.abdera.model.Entry;

/**
 *
 * @author saumitra
 */
public class UomsResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements UomsResource{

  private static final String REL_ORG = "uom";
  private static final String REL_ALT = "alternate";

  public UomsResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
  }  

  @Override
  public UomResource create(UnitOfMeasurement uom) {
    ClientResponse response = post(MediaType.APPLICATION_JSON, uom, ClientResponse.Status.CREATED);
    final ResourceLink orgLink = ClientUtil.createResourceLink(REL_ORG, response.getLocation(),
                                                               MediaType.APPLICATION_ATOM_XML);
    return new UomResourceImpl(this,orgLink);
  }

  @Override
  public List<UomResource> getOrganizationUomResources() {

    List<UomResource> OrganizationUomResources= new ArrayList<UomResource>();
   
    for (Entry entry : getLastReadStateOfEntity().getEntries()){
      OrganizationUomResources.add(new UomResourceImpl(this,AtomClientUtil.convertFromAtomLinkToResourceLink(entry.getLink(REL_ALT))) );
    }
    return OrganizationUomResources;
  }

  @Override
  protected void processClientConfig(ClientConfig clientConfig) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected Resource<? extends Feed> instantiatePageableResource(ResourceLink link) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

 
}
