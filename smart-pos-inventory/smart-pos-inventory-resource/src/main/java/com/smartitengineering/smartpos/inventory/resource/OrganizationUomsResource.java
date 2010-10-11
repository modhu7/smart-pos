/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.inventory.api.factory.Services;
import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement;
import com.smartitengineering.smartpos.inventory.api.domainid.UomId;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russel
 */
@Path("/inv/uoms")
public class OrganizationUomsResource extends AbstractResource {

  protected final Logger logger = LoggerFactory.getLogger(OrganizationUomsResource.class);
  static final UriBuilder ORGANIZATION_UOMS_URI_BUILDER;
  static final UriBuilder ORGANIZATION_UOMS_BEFORE_UOMNAME_URI_BUILDER;
  static final UriBuilder ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER;

  public OrganizationUomsResource() {    
  }

  static {
    ORGANIZATION_UOMS_URI_BUILDER = UriBuilder.fromResource(OrganizationUomsResource.class);

    ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER = UriBuilder.fromResource(OrganizationUomsResource.class);
    try {
      ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER.path(OrganizationUomsResource.class.getMethod("getAfter",
                                                                                                String.class));
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

    ORGANIZATION_UOMS_BEFORE_UOMNAME_URI_BUILDER = UriBuilder.fromResource(OrganizationUomsResource.class);
    try {
      ORGANIZATION_UOMS_BEFORE_UOMNAME_URI_BUILDER.path(OrganizationUomsResource.class.getMethod("getBefore",
                                                                                                 String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  @DefaultValue("10")
  @QueryParam("count")
  private Integer count;  
  @Context
  private HttpServletRequest servletRequest;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();


    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();
    System.out.println(uoms.getClass().getName());
    
    servletRequest.setAttribute("templateHeadContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationUomsResource/uomListHeader.jsp");
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationUomsResource/uomList.jsp");

    Viewable view = new Viewable("/template/template.jsp", uoms);


    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/frags")
  public Response getHtmlFrags() {
    ResponseBuilder responseBuilder = Response.ok();
    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();

    Viewable view = new Viewable("uomFrags.jsp", uoms, OrganizationUomsResource.class);
    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/before/{beforeUomName}")
  public Response getBefore(@PathParam("beforeUomName") String beforeUomName) {
    return get( beforeUomName, true);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeUomName}")
  public Response getBeforeHtml(@PathParam("beforeUomName") String beforeUomName) {
    ResponseBuilder responseBuilder = Response.ok();
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);
    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();

    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationUomsResource/uomList.jsp");
    Viewable view = new Viewable("/template/template.jsp", uoms);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeUomName}/frags")
  public Response getBeforeHtmlFrags(@PathParam("beforeUomName") String beforeUomName) {
    ResponseBuilder responseBuilder = Response.ok();
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);

    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();

    Viewable view = new Viewable("uomFrags.jsp", uoms);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/after/{afterUomName}")
  public Response getAfter(@PathParam("afterUomName") String afterUomName) {
    return get(afterUomName, false);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterUomName}")
  public Response getAfterHtml(@PathParam("afterUomName") String afterUomName) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();
    servletRequest.setAttribute("templateContent",
                                "/com/smartitengineering/smartpos/inventory/resource/OrganizationUomsResource/uomList.jsp");
    Viewable view = new Viewable("/template/template.jsp", uoms);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterUomName}/frags")
  public Response getAfterHtmlFrags(@PathParam("afterUomName") String afterUomName) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();

    Viewable view = new Viewable("uomFrags.jsp", uoms);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    return get(null, true);
  }

  private Response get(String uomName, boolean isBefore) {
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = getFeed(uomName, new Date());

    Link parentLink = abderaFactory.newLink();
    parentLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    parentLink.setRel("parent");
    atomFeed.addLink(parentLink);

    Collection<UnitOfMeasurement> uoms = Services.getInstance().getUomService().getAllUoms();

    if (uoms != null && !uoms.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<UnitOfMeasurement> uomList = new ArrayList<UnitOfMeasurement>(uoms);

      // uri builder for next and previous organizations according to count
      final UriBuilder nextUri = ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER.clone();
      final UriBuilder previousUri = ORGANIZATION_UOMS_BEFORE_UOMNAME_URI_BUILDER.clone();

      // link to the next organizations based on count
      Link nextLink = abderaFactory.newLink();
      nextLink.setRel(Link.REL_NEXT);
      //User lastUser = userList.get(userList.size() - 1);
      UnitOfMeasurement lastUom = uomList.get(uomList.size() - 1);


      for (String key : queryParam.keySet()) {
        final Object[] values = queryParam.get(key).toArray();
        nextUri.queryParam(key, values);
        previousUri.queryParam(key, values);
      }
      nextLink.setHref(nextUri.build(lastUom.getId().getId()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);
      //User firstUser = userList.get(0);
      UnitOfMeasurement firstUom = uomList.get(0);

      prevLink.setHref(
          previousUri.build(firstUom.getId().getId()).toString());
      atomFeed.addLink(prevLink);

      //for (User user : users) {
      for (UnitOfMeasurement uom : uoms) {

        Entry uomEntry = abderaFactory.newEntry();

        uomEntry.setId(uom.getId().getId());
        uomEntry.setTitle(uom.getId().getId());
        uomEntry.setSummary(uom.getId().getId());
        //userEntry.setUpdated(Store.g);

        // setting link to the each individual user
        Link uomLink = abderaFactory.newLink();
        uomLink.setHref(OrganizationUomResource.UOM_URI_BUILDER.clone().build(uom.getId().getId()).
            toString());
        uomLink.setRel(Link.REL_ALTERNATE);
        uomLink.setMimeType(MediaType.APPLICATION_ATOM_XML);

        uomEntry.addLink(uomLink);

        atomFeed.addEntry(uomEntry);
      }
    }
    responseBuilder.entity(atomFeed);
    return responseBuilder.build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(UnitOfMeasurement uom) {


    ResponseBuilder responseBuilder;

    try {
      // set id with organization name
      basicPost(uom);
      responseBuilder = Response.status(Status.CREATED);
      responseBuilder.location(uriInfo.getBaseUriBuilder().path(OrganizationUomResource.UOM_URI_BUILDER.clone().build(
          uom.getId().getId()).toString()).build());
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      logger.error(ex.getMessage());
    }
    return responseBuilder.build();
  }

  private UnitOfMeasurement getObjectFromContent(String message) {
    Map<String, String> keyValueMap = new HashMap<String, String>();

    String[] keyValuePairs = message.split("&");

    for (int i = 0; i < keyValuePairs.length; i++) {

      String[] keyValuePair = keyValuePairs[i].split("=");
      keyValueMap.put(keyValuePair[0], keyValuePair[1]);
    }

    UnitOfMeasurement uom = new UnitOfMeasurement();

    if (keyValueMap.get("id") != null) {
      UomId uomId = new UnitOfMeasurement.UomIdImpl();
      uomId.setId(keyValueMap.get("id"));
      uom.setId(uomId);
    }

    if(keyValueMap.get("longName") != null){
      uom.setLongName(keyValueMap.get("longName"));
    }

    if (keyValueMap.get("symbol") != null) {
      uom.setSymbol(keyValueMap.get("symbol"));
    }
    if (keyValueMap.get("uomType") != null) {
      uom.setUomType(keyValueMap.get("uomType"));
    }

    if (keyValueMap.get("uomSystem") != null) {
      uom.setUomSystem(keyValueMap.get("uomSystem"));
    }

    return uom;
  }

  @POST
  public Response post(
      @HeaderParam("Content-type") String contentType, String message) {
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
      UnitOfMeasurement uom = getObjectFromContent(message);

      basicPost(uom);

    }
    return responseBuilder.build();
  }

  private void basicPost(UnitOfMeasurement uom){
    UomId uomId = new UnitOfMeasurement.UomIdImpl(uom.getId().getId());
    uom.setId(uomId);
    logger.info(uom.getId().getId());
    Services.getInstance().getUomService().save(uom);
  }
}
