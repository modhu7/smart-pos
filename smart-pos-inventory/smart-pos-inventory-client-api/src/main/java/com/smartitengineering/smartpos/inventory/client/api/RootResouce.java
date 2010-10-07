/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

import com.smartitengineering.util.rest.client.ResourceLink;
/**
 *
 * @author saumitra
 */
public interface RootResouce {

  UomsResource getOrganizationUomsResource();

  EntriesResource getEntriesResource();

  ProductsResource getOrganizationProductsResource();

  StoreResource getOrganizationStoreResource();

  ResourceLink getLoginLink();

}
