/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;


import com.smartitengineering.smartpos.inventory.client.api.domain.Store;
import com.smartitengineering.user.client.api.OrganizationResource;
import com.smartitengineering.util.rest.client.WritableResource;
import org.apache.abdera.model.Feed;

/**
 *
 * @author saumitra
 */
public interface StoreResource extends WritableResource<Feed>{

  public void update();

 public ProductsResource getProductResources();

 public Store getStore();

 public OrganizationResource getOrganizationResource();

 public void delete();

}
