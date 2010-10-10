/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.client.impl.resource;

import com.smartitengineering.smartpos.inventory.client.api.resource.UomResource;
import com.smartitengineering.smartpos.inventory.client.api.domain.UnitOfMeasurement;
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
public class UomResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements
    UomResource {

  public UomResourceImpl(Resource referrer, ResourceLink pageLink) {
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
  public UnitOfMeasurement getUnitOfMeasurement() {
    return getUnitOfMeasurement();
  }

  @Override
  public void update() {
    put(MediaType.APPLICATION_JSON,getUnitOfMeasurement(), ClientResponse.Status.OK, ClientResponse.Status.SEE_OTHER, ClientResponse.Status.FOUND);
  }
}