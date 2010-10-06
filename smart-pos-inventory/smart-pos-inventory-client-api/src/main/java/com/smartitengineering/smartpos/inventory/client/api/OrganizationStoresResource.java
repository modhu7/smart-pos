/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

import com.smartitengineering.util.rest.client.WritableResource;
import java.util.List;
import org.apache.abdera.model.Feed;

/**
 *
 * @author saumitra
 */
public interface OrganizationStoresResource extends WritableResource<Feed>{

  public List<OrganizationStoreResource> getOrganizationStoreResources();

  public OrganizationStoreResource create(Store store);

  public OrganizationStoreResource getOrganizationStoreResource();

}
