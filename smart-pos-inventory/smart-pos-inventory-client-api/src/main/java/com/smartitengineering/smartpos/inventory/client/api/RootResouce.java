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

  OrganizationUomsResource getOrganizationUomsResource();

  OrganizationEntriesResource getOrganizationEntriesResource();

  OrganizationProductsResource getOrganizationProductsResource();

  OrganizationStoreResource getOrganizationStoreResource();

  ResourceLink getLoginLink();

}
