/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.pos.inventory.resource;

import com.sun.jersey.api.view.Viewable;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{orgUniqueShortName}/dashboard")
public class RootResource extends AbstractResource{
  private static final Date INIT_DATE = new Date();

  @PathParam("orgUniqueShortName")
  public String orgUniqueShortName;

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = getFeed("Smart Pos Inventory", INIT_DATE);

    Link loginLink = Abdera.getNewFactory().newLink();    
    loginLink.setHref(OrganizationUomsResource.ORGANIZATION_UOMS_URI_BUILDER.build(orgUniqueShortName).toString());
    loginLink.setRel("uoms");    
    atomFeed.addLink(loginLink);

    Link organizationsLink = Abdera.getNewFactory().newLink();
    organizationsLink.setHref(OrganizationStoresResource.ORGANIZATION_STORES_URI_BUILDER.build(orgUniqueShortName).toString());
    organizationsLink.setRel("stores");
    atomFeed.addLink(organizationsLink);

    Link productsLink = Abdera.getNewFactory().newLink();
    productsLink.setHref(OrganizationProductsResource.ORGANIZATION_PRODUCTS_URI_BUILDER.build(orgUniqueShortName).toString());
    productsLink.setRel("products");
    atomFeed.addLink(productsLink);


    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml()
  {
    ResponseBuilder responseBuilder = Response.ok();


    Viewable view = new Viewable("rootPage.jsp");

    responseBuilder.entity(view);
    return responseBuilder.build();
  }
}
