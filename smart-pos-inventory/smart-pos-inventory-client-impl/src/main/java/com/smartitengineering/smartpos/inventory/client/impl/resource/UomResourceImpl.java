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
public class UomResourceImpl extends AbstractFeedClientResource<Resource<? extends Feed>> implements
    UomResource {

  public static final String REL_UOM = "uom";

  public UomResourceImpl(Resource referrer, ResourceLink pageLink) {
    super(referrer, pageLink);
    final ResourceLink altLink = getRelatedResourceUris().getFirst(Link.REL_ALTERNATE);
    addNestedResource(REL_UOM, new SimpleResourceImpl<com.smartitengineering.smartpos.inventory.client.impl.domain.UnitOfMeasurementImpl>(
        this, altLink.getUri(), altLink.getMimeType(),
        com.smartitengineering.smartpos.inventory.client.impl.domain.UnitOfMeasurementImpl.class,
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
  public UnitOfMeasurement getUnitOfMeasurement() {
    return getUnitOfMeasurement(false);
  }

  protected UnitOfMeasurement getUnitOfMeasurement(boolean reload) {
    Resource<UnitOfMeasurement> uom = super.<UnitOfMeasurement>getNestedResource(REL_UOM);
    if (reload) {
      return uom.get();
    }
    else {
      return uom.getLastReadStateOfEntity();
    }
  }

  public UnitOfMeasurement getUom() {
    return getUom(false);
  }

  protected UnitOfMeasurement getUom(boolean reload) {
    Resource<UnitOfMeasurement> uom = super.<UnitOfMeasurement>getNestedResource(REL_UOM);
    if (reload) {
      return uom.get();
    }
    else {
      return uom.getLastReadStateOfEntity();
    }
  }

  @Override
  public void update() {
    put(MediaType.APPLICATION_JSON, getUom(), ClientResponse.Status.OK, ClientResponse.Status.SEE_OTHER,
        ClientResponse.Status.FOUND);
  }

  @Override
  public void delete() {
    delete(ClientResponse.Status.OK);
  }
}
