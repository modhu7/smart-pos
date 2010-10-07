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
public interface StoresResource extends WritableResource<Feed>{

  public List<StoreResource> getOrganizationStoreResources();

  public StoreResource create(Store store);

  public StoreResource getOrganizationStoreResource();

}
