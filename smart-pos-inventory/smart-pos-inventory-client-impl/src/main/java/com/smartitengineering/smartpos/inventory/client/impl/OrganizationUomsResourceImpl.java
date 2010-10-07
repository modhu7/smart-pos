/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.impl;

import com.smartitengineering.smartpos.inventory.client.api.OrganizationUomResource;
import com.smartitengineering.smartpos.inventory.client.api.OrganizationUomsResource;
import com.smartitengineering.smartpos.inventory.client.api.UnitOfMeasurement;
import com.smartitengineering.util.rest.atom.AbstractFeedClientResource;
import com.smartitengineering.util.rest.atom.AtomClientUtil;
import com.smartitengineering.util.rest.client.ClientUtil;
import com.smartitengineering.util.rest.client.Resource;
import com.smartitengineering.util.rest.client.ResourceLink;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import java.util.ArrayList;
import java.util.List;
import org.apache.abdera.model.Feed;
import javax.ws.rs.core.MediaType;
import org.apache.abdera.model.Entry;

/**
 *
 * @author saumitra
 */
public class OrganizationUomsResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements OrganizationUomsResource{

  private static final String REL_ORG = "uom";
  private static final String REL_ALT = "alternate";

  public OrganizationUomsResourceImpl(Resource referrer, ResourceLink pageLink) {
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
  public OrganizationUomResource create(UnitOfMeasurement uom) {
    ClientResponse response = post(MediaType.APPLICATION_JSON, uom, ClientResponse.Status.CREATED);
    final ResourceLink orgLink = ClientUtil.createResourceLink(REL_ORG, response.getLocation(),
                                                               MediaType.APPLICATION_ATOM_XML);
    return new OrganizationUomResourceImpl(this,orgLink);
  }

  @Override
  public List<OrganizationUomResource> getOrganizationUomResources() {

    List<OrganizationUomResource> OrganizationUomResources= new ArrayList<OrganizationUomResource>();
   
    for (Entry entry : getLastReadStateOfEntity().getEntries()){
      OrganizationUomResources.add(new OrganizationUomResourceImpl(this,AtomClientUtil.convertFromAtomLinkToResourceLink(entry.getLink(REL_ALT))) );
    }
    return OrganizationUomResources;
  }
}
