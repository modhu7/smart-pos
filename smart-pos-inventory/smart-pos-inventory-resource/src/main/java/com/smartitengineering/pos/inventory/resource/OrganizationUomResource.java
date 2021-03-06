/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.pos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.inventory.api.PersistantUnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
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
@Path("/inv/uoms/name/{uomName}")
public class OrganizationUomResource extends AbstractResource {

  protected final Logger logger = LoggerFactory.getLogger(OrganizationUomResource.class);

  private PersistantUnitOfMeasurement uom;
  static final UriBuilder UOM_URI_BUILDER = UriBuilder.fromResource(OrganizationUomResource.class);
  static final UriBuilder UOM_CONTENT_URI_BUILDER;
  @Context
  private HttpServletRequest servletRequest;

  static {
    UOM_CONTENT_URI_BUILDER = UOM_URI_BUILDER.clone();
    try {
      UOM_CONTENT_URI_BUILDER.path(OrganizationUomResource.class.getMethod("getUom"));
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new InstantiationError();

    }
  }  
  @PathParam("uomName")
  private String uomName;

  public OrganizationUomResource(@PathParam("uomName") String uomName) {
    UomId uomId = new PersistantUnitOfMeasurement.UomIdImpl(uomName);
    logger.info(uomId.toString());
    uom = Services.getInstance().getUomService().getById(uomId);
    if(uom == null)
      logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>UOM NULL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+uom);
    //uom = Services.getInstance().getUomService().getByUomId(uomId);

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    if (uom == null) {
      return Response.status(Status.NOT_FOUND).build();
    }
    Feed userFeed = getUomFeed();
    ResponseBuilder responseBuilder = Response.ok(userFeed);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/content")
  public Response getUom() {
    UnitOfMeasurement nUom = new UnitOfMeasurement();
    nUom.setId(uom.getId().getId());
    nUom.setLongName(uom.getLongName());
    nUom.setSymbol(uom.getSymbol());
    nUom.setUomSystem(uom.getUomSystem());
    nUom.setUomType(uom.getUomType());
    ResponseBuilder responseBuilder = Response.ok(nUom);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();
    
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationUomResource/uomDetailsHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationUomResource/uomDetails.jsp");
    Viewable view = new Viewable("/template/template.jsp", uom);

    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(PersistantUnitOfMeasurement uom) {

    ResponseBuilder responseBuilder = Response.status(Status.SERVICE_UNAVAILABLE);
    try {      
      Services.getInstance().getUomService().update(uom);
      responseBuilder = Response.ok(getUomFeed());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private Feed getUomFeed() throws UriBuilderException, IllegalArgumentException {
    Feed uomFeed = getFeed(uom.getId().getId(), new Date());
    uomFeed.setTitle(uom.getId().getId());

    // add a self link
    uomFeed.addLink(getSelfLink());

    // add a edit link
    Link editLink = abderaFactory.newLink();
    editLink.setHref(uriInfo.getRequestUri().toString());
    editLink.setRel(Link.REL_EDIT);
    editLink.setMimeType(MediaType.APPLICATION_JSON);
    uomFeed.addLink(editLink);

    // add a alternate link
    Link altLink = abderaFactory.newLink();
    altLink.setHref(UOM_CONTENT_URI_BUILDER.clone().build(uom.getId()).toString());
    altLink.setRel(Link.REL_ALTERNATE);
    altLink.setMimeType(MediaType.APPLICATION_JSON);
    uomFeed.addLink(altLink);

    return uomFeed;
  }

  @DELETE
  public Response delete() {    
    Services.getInstance().getUomService().delete(uom);
    ResponseBuilder responseBuilder = Response.ok();
    return responseBuilder.build();
  }

  @POST
  @Path("/delete")
  public Response deletePost() {
    ResponseBuilder responseBuilder = Response.ok();
    try{
      Services.getInstance().getUomService().delete(uom);

    }catch(Exception ex){
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
    }

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
    logger.info(message);

    if (isHtmlPost) {
      PersistantUnitOfMeasurement newUom = getUomFromContent(message);
      try {
        Services.getInstance().getUomService().update(newUom);
        responseBuilder = Response.ok(getUomFeed());
      }
      catch (Exception ex) {
        responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
        logger.error(ex.getMessage());
      }
    }
    return responseBuilder.build();
  }

  private PersistantUnitOfMeasurement getUomFromContent(String message) {

    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      if (keyValuePair.length > 1) {
        keyValueMap.put(keyValuePair[0], keyValuePair[1]);
      }
    }

    PersistantUnitOfMeasurement uom = new PersistantUnitOfMeasurement();

    if(keyValueMap.get("id") != null){
      UomId uomId = new PersistantUnitOfMeasurement.UomIdImpl(keyValueMap.get("id"));
      uom.setId(uomId);
    }

    if(keyValueMap.get("longName") != null){
      uom.setLongName(keyValueMap.get("longName"));
    }

    if(keyValueMap.get("symbol") != null){
      uom.setSymbol(keyValueMap.get("symbol"));
    }
    if(keyValueMap.get("uomType") != null){
      uom.setUomType(keyValueMap.get("uomType"));
    }

    if(keyValueMap.get("uomSystem") != null){
      uom.setUomSystem(keyValueMap.get("uomSystem"));
    }

    return uom;
  }
}
