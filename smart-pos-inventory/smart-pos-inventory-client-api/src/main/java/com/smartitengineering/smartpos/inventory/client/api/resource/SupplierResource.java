/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;

import com.smartitengineering.smartpos.inventory.client.api.domain.Supplier;
import com.smartitengineering.util.rest.client.WritableResource;
import org.apache.abdera.model.Feed;

/**
 *
 * @author russel
 */
public interface SupplierResource extends WritableResource<Feed>{

  public void update();

  public void delete();

  public Supplier getSupplier();

}
