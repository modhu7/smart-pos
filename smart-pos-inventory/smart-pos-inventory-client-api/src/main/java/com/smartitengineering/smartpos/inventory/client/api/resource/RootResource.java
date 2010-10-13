/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api.resource;

import com.smartitengineering.util.rest.client.ResourceLink;
/**
 *
 * @author saumitra
 */
public interface RootResource {

  UomsResource getOrganizationUomsResource();

  EntriesResource getEntriesResource();

  ProductsResource getOrganizationProductsResource();  

  StoresResource getStoresResource();

  ResourceLink getLoginLink();

}
