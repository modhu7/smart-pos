/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.client.api;

/**
 *
 * @author saumitra
 */
public interface OrganizationProductsResource {

  public OrganizationProductResource getOrganizationProductResource();

  public OrganizationProductResource create(Product product);

}
