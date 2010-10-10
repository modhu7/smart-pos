/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.inventory.api.Store;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/stores/code/{storeCode}")
public class OrganizationStoreResource extends AbstractResource {

  protected final Logger logger = LoggerFactory.getLogger(OrganizationStoreResource.class);

  private Store store;
  static final UriBuilder STORE_URI_BUILDER = UriBuilder.fromResource(OrganizationStoreResource.class);
  static final UriBuilder STORE_CONTENT_URI_BUILDER;
  @Context
  private HttpServletRequest servletRequest;

  static {
    STORE_CONTENT_URI_BUILDER = STORE_URI_BUILDER.clone();
    try {
      STORE_CONTENT_URI_BUILDER.path(OrganizationStoreResource.class.getMethod("getStore"));
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new InstantiationError();

    }
  }
  @PathParam("organizationShortName")
  private String organizationUniqueShortName;
  @PathParam("storeCode")
  private String storeCode;

  public OrganizationStoreResource(@PathParam("organizationShortName") String organizationShortName, @PathParam(
      "storeCode") String storeCode) {
    store = Services.getInstance().getStoreService().getByStoreCodeAndOrganization(organizationShortName, storeCode);    

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    Feed userFeed = getStoreFeed();
    ResponseBuilder responseBuilder = Response.ok(userFeed);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/content")
  public Response getStore() {
    ResponseBuilder responseBuilder = Response.ok(store);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();

    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoreResource/storeDetailsHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationStoreResource/storeDetails.jsp");
    Viewable view = new Viewable("/template/template.jsp", store);

    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(Store store) {

    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    try {
      
      if (store.getOrganizationID() == null) {
        throw new Exception("No organization found");
      }
      
      //Services.getInstance().getOrganizationService().populateOrganization(newUserPerson.getUser());
      Services.getInstance().getStoreService().update(store);
      responseBuilder = Response.ok(getStoreFeed());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private Feed getStoreFeed() throws UriBuilderException, IllegalArgumentException {
    Feed storeFeed = getFeed(store.getCode(), new Date());
    storeFeed.setTitle(store.getName());

    // add a self link
    storeFeed.addLink(getSelfLink());

    // add a edit link
    Link editLink = abderaFactory.newLink();
    editLink.setHref(uriInfo.getRequestUri().toString());
    editLink.setRel(Link.REL_EDIT);
    editLink.setMimeType(MediaType.APPLICATION_JSON);
    storeFeed.addLink(editLink);

    // add a alternate link
    Link altLink = abderaFactory.newLink();
    altLink.setHref(STORE_CONTENT_URI_BUILDER.clone().build(store.getOrganization().getUniqueShortName(),
                                                           store.getCode()).toString());
    altLink.setRel(Link.REL_ALTERNATE);
    altLink.setMimeType(MediaType.APPLICATION_JSON);
    storeFeed.addLink(altLink);

    return storeFeed;
  }

  @DELETE
  public Response delete() {
    Services.getInstance().getStoreService().delete(store);
    ResponseBuilder responseBuilder = Response.ok();
    return responseBuilder.build();
  }

  @POST
  @Path("/delete")
  public Response deletePost() {
    Services.getInstance().getStoreService().delete(store);
    ResponseBuilder responseBuilder = Response.ok();
    return responseBuilder.build();
  }

  @POST
  @Path("/update")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response updatePost(@HeaderParam("Content-type") String contentType, String message) {
    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);

    if (StringUtils.isBlank(message)) {
      responseBuilder = Response.status(Status.BAD_REQUEST);
      responseBuilder.build();
    }

    final boolean isHtmlPost;
    if (StringUtils.isBlank(contentType)) {
      contentType = MediaType.APPLICATION_OCTET_STREAM;
      isHtmlPost = false;
    }
    else if (contentType.equals(MediaType.APPLICATION_FORM_URLENCODED)) {
      contentType = MediaType.APPLICATION_OCTET_STREAM;
      isHtmlPost = true;
      try {
        //Will search for the first '=' if not found will take the whole string
        final int startIndex = 0;//message.indexOf("=") + 1;
        //Consider the first '=' as the start of a value point and take rest as value
        final String realMsg = message.substring(startIndex);
        //Decode the message to ignore the form encodings and make them human readable
        message = URLDecoder.decode(realMsg, "UTF-8");
      }
      catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();

      }
    }
    else {
      contentType = contentType;
      isHtmlPost = false;
    }

    if (isHtmlPost) {
      Store newStore = getStoreFromContent(message);
      try {
        Services.getInstance().getStoreService().update(newStore);
        responseBuilder = Response.ok(getStoreFeed());
      }
      catch (Exception ex) {
        responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      }
    }
    return responseBuilder.build();
  }

  private Store getStoreFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      if (keyValuePair.length > 1) {
        keyValueMap.put(keyValuePair[0], keyValuePair[1]);
      }
    }

    Store newStore = new Store();
   
    return newStore;
  }
}
