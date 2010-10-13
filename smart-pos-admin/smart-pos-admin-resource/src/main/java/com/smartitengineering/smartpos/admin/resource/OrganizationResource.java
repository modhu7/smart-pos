/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.admin.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{shortName}")
public class OrganizationResource extends AbstractResource{

  public static final UriBuilder ORGANIZATION_URI_BUILDER;

  static{
    ORGANIZATION_URI_BUILDER = UriBuilder.fromResource(OrganizationResource.class);
  }

}
