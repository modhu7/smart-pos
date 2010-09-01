/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.admin.resource;

import com.sun.jersey.api.view.Viewable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author russel
 */
@Path("/")
public class RootResource extends AbstractResource{

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml(){
    ResponseBuilder responseBuilder = Response.ok();


    Viewable view = new Viewable("rootPage.jsp");

    responseBuilder.entity(view);
    return responseBuilder.build();
  }

}
