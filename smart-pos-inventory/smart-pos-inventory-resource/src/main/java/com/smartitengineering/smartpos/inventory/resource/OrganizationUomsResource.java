/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartitengineering.smartpos.inventory.resource;

import com.smartitengineering.smartpos.admin.resource.RootResource;
import com.smartitengineering.smartpos.inventory.api.UOM;
import com.sun.jersey.api.view.Viewable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author russel
 */
@Path("/orgs/sn/{uniqueShortName}/inv/uoms")
public class OrganizationUomsResource extends AbstractResource{

  static final UriBuilder ORGANIZATION_UOMS_URI_BUILDER;
  static final UriBuilder ORGANIZATION_UOMS_BEFORE_UOMNAME_URI_BUILDER;
  static final UriBuilder ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER;

  public OrganizationUomsResource(@PathParam("uniqueShortName") String organizationUniqueShortName) {

    this.organizationUniqueShortName = organizationUniqueShortName;
  }

  static {
    ORGANIZATION_UOMS_URI_BUILDER = UriBuilder.fromResource(OrganizationUomsResource.class);

    ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER = UriBuilder.fromResource(OrganizationUomsResource.class);
    try {
      ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER.path(OrganizationUomsResource.class.getMethod("getAfter",
                                                                                                     String.class));
    }
    catch (Exception ex) {
      ex.printStackTrace();
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
  @PathParam("uniqueShortName")
  private String organizationUniqueShortName;
  @Context
  private HttpServletRequest servletRequest;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getHtml() {
    ResponseBuilder responseBuilder = Response.ok();

//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(organizationUniqueShortName,
//                                                                                           null,
//                                                                                           false, count);

//    Organization org = Services.getInstance().getOrganizationService().getOrganizationByUniqueShortName(
//        organizationUniqueShortName);
//    if (org == null) {
//      responseBuilder = Response.status(Status.NOT_FOUND);
//      return responseBuilder.build();
//    }


    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, null, false, count);



    servletRequest.setAttribute("orgInitial", organizationUniqueShortName);
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
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(organizationUniqueShortName,
//                                                                                           null, false, count);
    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, null, false, count);

    Viewable view = new Viewable("uomFrags.jsp", uoms, OrganizationUomsResource.class);
    responseBuilder.entity(view);
    return responseBuilder.build();

  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/before/{beforeUomName}")
  public Response getBefore(@PathParam("beforeUomName") String beforeUomName) {
    return get(organizationUniqueShortName, beforeUomName, true);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/before/{beforeUomName}")
  public Response getBeforeHtml(@PathParam("beforeUomName") String beforeUomName) {
    ResponseBuilder responseBuilder = Response.ok();
//    Collection<User> users = Services.getInstance().getUserService().getUserByOrganization(
//        organizationUniqueShortName, beforeUserName, true, count);
    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, beforeUomName, true, count);

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

    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, beforeUomName, true, count);

    Viewable view = new Viewable("uomFrags.jsp", uoms);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  @Path("/after/{afterUomName}")
  public Response getAfter(@PathParam("afterUomName") String afterUomName) {
    return get(organizationUniqueShortName, afterUomName, false);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/after/{afterUomName}")
  public Response getAfterHtml(@PathParam("afterUomName") String afterUomName) {

    ResponseBuilder responseBuilder = Response.ok();

    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, afterUomName, false, count);
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

    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, afterUomName, false, count);

    Viewable view = new Viewable("uomFrags.jsp", uoms);
    responseBuilder.entity(view);
    return responseBuilder.build();
  }

  @GET
  @Produces(MediaType.APPLICATION_ATOM_XML)
  public Response get() {
    return get(organizationUniqueShortName, null, true);
  }

  private Response get(String uniqueOrganizationName, String uomName, boolean isBefore) {
    ResponseBuilder responseBuilder = Response.ok();
    Feed atomFeed = getFeed(uomName, new Date());

    Link parentLink = abderaFactory.newLink();
    parentLink.setHref(UriBuilder.fromResource(RootResource.class).build().toString());
    parentLink.setRel("parent");
    atomFeed.addLink(parentLink);

    Collection<UOM> uoms = Services.getInstance().getUomService().getByOrganization(
        organizationUniqueShortName, uomName, isBefore, count);

    if (uoms != null && !uoms.isEmpty()) {

      MultivaluedMap<String, String> queryParam = uriInfo.getQueryParameters();
      List<UOM> uomList = new ArrayList<UOM>(uoms);

      // uri builder for next and previous organizations according to count
      final UriBuilder nextUri = ORGANIZATION_UOMS_AFTER_UOMNAME_URI_BUILDER.clone();
      final UriBuilder previousUri = ORGANIZATION_UOMS_BEFORE_UOMNAME_URI_BUILDER.clone();

      // link to the next organizations based on count
      Link nextLink = abderaFactory.newLink();
      nextLink.setRel(Link.REL_NEXT);
      //User lastUser = userList.get(userList.size() - 1);
      UOM lastUom = uomList.get(uomList.size() - 1);


      for (String key : queryParam.keySet()) {
        final Object[] values = queryParam.get(key).toArray();
        nextUri.queryParam(key, values);
        previousUri.queryParam(key, values);
      }
      nextLink.setHref(nextUri.build(organizationUniqueShortName, lastUom.getName()).toString());


      atomFeed.addLink(nextLink);

      /* link to the previous organizations based on count */
      Link prevLink = abderaFactory.newLink();
      prevLink.setRel(Link.REL_PREVIOUS);
      //User firstUser = userList.get(0);
      UOM firstUom = uomList.get(0);

      prevLink.setHref(
          previousUri.build(organizationUniqueShortName, firstUom.getName()).toString());
      atomFeed.addLink(prevLink);

      //for (User user : users) {
      for (UOM uom : uoms) {

        Entry uomEntry = abderaFactory.newEntry();

        uomEntry.setId(uom.getName());
        uomEntry.setTitle(uom.getName());
        uomEntry.setSummary(uom.getName());
        //userEntry.setUpdated(Store.g);

        // setting link to the each individual user
        Link uomLink = abderaFactory.newLink();
        uomLink.setHref(OrganizationUomResource.UOM_URI_BUILDER.clone().build(organizationUniqueShortName, uom.
            getName()).toString());
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
  public Response post(UOM uom) {


    ResponseBuilder responseBuilder;

    try {
      if (uom.getOrganizationID() == null) {
        throw new Exception("No organization found");
      }
      //Services.getInstance().getOrganizationService().populateOrganization(user);
      Services.getInstance().getUomService().save(uom);
      responseBuilder = Response.status(Status.CREATED);
    }
    catch (Exception ex) {
      responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
      ex.printStackTrace();
    }
    return responseBuilder.build();
  }

  private UOM getObjectFromContent(String message) {

    return new UOM();
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

    if (isHtmlPost) {
      UOM uom = getObjectFromContent(message);

        Services.getInstance().getUomService().save(uom);

    }
    return responseBuilder.build();
  }
}
